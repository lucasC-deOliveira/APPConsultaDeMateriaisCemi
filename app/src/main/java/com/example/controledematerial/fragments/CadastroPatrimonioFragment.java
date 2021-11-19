package com.example.controledematerial.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controledematerial.MainActivity;
import com.example.controledematerial.R;
import com.example.controledematerial.model.Patrimonio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CadastroPatrimonioFragment extends Fragment {
    private EditText textNomePatrimonio, textDescricaoPatrimonio;
    private Button btcadastro;
    private TextView viewNumeroPatrimonio;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_patrimonio, container, false);

        textNomePatrimonio = view.findViewById(R.id.editTextNomePatrimonioCadastro);
        textDescricaoPatrimonio = view.findViewById(R.id.editTextDescricaoPatrimonioCadastro);
        btcadastro = view.findViewById(R.id.buttonFinalizarCadastro);
        viewNumeroPatrimonio = view.findViewById(R.id.textViewNumeroPatrimonio);

        Random generator = new Random();
        int numeroRandom = generator.nextInt(1000000000);

        viewNumeroPatrimonio.setText(String.valueOf(numeroRandom));


        btcadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UID = java.util.UUID.randomUUID().toString();
                String nome = textNomePatrimonio.getText().toString().trim();
                String descricao = textDescricaoPatrimonio.getText().toString().trim();
                String numero = String.valueOf(numeroRandom);

                if(nome.equals("")|| descricao.equals("")){
                    Toast.makeText(getActivity(), "Preencha todos os campos para cadastrar o patrimônio", Toast.LENGTH_SHORT).show();
                }

                else {

                Patrimonio patrimonio= new Patrimonio(UID,numero,nome,descricao);

                DatabaseReference myRef = MainActivity.getMyRef();

                myRef.child("Patrimonio").child(UID).setValue(patrimonio);
                Toast.makeText(getActivity(),"Sucesso ao cadastrar", Toast.LENGTH_SHORT).show();

                FragmentTransaction transaction= getActivity().getSupportFragmentManager().beginTransaction();
                MenuFragment menuFragment = (MenuFragment) MainActivity.getMenuFragment();
                transaction.replace(R.id.framelayout, menuFragment).commit();}





            }
        });


        return view;
    }

}