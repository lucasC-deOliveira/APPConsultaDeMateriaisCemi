package com.example.controledematerial.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controledematerial.MainActivity;
import com.example.controledematerial.R;
import com.example.controledematerial.model.Patrimonio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ConsultaPatrimonioFragment extends Fragment {

    private Button btFinalizarConsulta;
    private EditText textNPatrimonio,textDescricaoconsulta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consulta_patrimonio, container, false);
        btFinalizarConsulta = view.findViewById(R.id.buttonFinalizarConsulta);
        textNPatrimonio = view.findViewById(R.id.editTextNumeroPatrimonio);
        textDescricaoconsulta = view.findViewById(R.id.editTextDescricaoPatrimonioConsulta);


        btFinalizarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroPatrimonio = textNPatrimonio.getText().toString().trim();
                String descricao= textDescricaoconsulta.getText().toString().trim();
                Patrimonio patrimonio = new Patrimonio();
                patrimonio.setNumero(numeroPatrimonio);
                patrimonio.setDescricao(descricao);
                pesquisarPatrimonio(patrimonio);


            }
        });





        return view;
    }


    private void pesquisarPatrimonio(Patrimonio patrimonioArg) {
        Query query;
        query = MainActivity.getMyRef().child("Patrimonio").orderByChild("numero").equalTo(patrimonioArg.getNumero());




        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean result = snapshot.hasChildren();
                Patrimonio patrimonioPesquisa;
                if (!result) {
                    Query query2 = MainActivity.getMyRef().child("Patrimonio").orderByChild("descricao").equalTo(patrimonioArg.getDescricao());
                    query2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean result = snapshot.hasChildren();
                            if(!result){
                                Toast.makeText(getActivity(), "Patrimônio não foi encontrado", Toast.LENGTH_LONG).show();
                            }

                            else{
                                Toast.makeText(getActivity(), "Patrimônio encontrado", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });





                } else {
                    Toast.makeText(getActivity(), "Patrimônio encontrado", Toast.LENGTH_LONG).show();
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });


    }
}