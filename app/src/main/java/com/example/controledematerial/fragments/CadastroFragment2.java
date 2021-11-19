package com.example.controledematerial.fragments;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controledematerial.MainActivity;
import com.example.controledematerial.R;
import com.example.controledematerial.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.Objects;


public class CadastroFragment2 extends Fragment {

    private Usuario usuarioObj;
    private EditText textNumeroCa, textEndereco, textEmail;
    private Button btCadastrar;
    private LoginFragment loginFragment;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_cadastro2, container, false);

        textNumeroCa = view.findViewById(R.id.editTextNumeroCarCadastro);
        textEmail = view.findViewById(R.id.editTextEmailCadastro);
        textEndereco = view.findViewById(R.id.editTextEnderecoCadastro);


        btCadastrar = view.findViewById(R.id.buttonCadastrar);

        Bundle recuperarDados = getArguments();
        usuarioObj= (Usuario) recuperarDados.getSerializable("cadastro");
        Log.i("Resultado", usuarioObj.toString());

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroCa = textNumeroCa.getText().toString().trim();
                String endereco = textEndereco.getText().toString().trim();
                String email = textEmail.getText().toString().trim();
                String UID = java.util.UUID.randomUUID().toString();

                usuarioObj.setUID(UID);
                usuarioObj.setNumeroCartao(numeroCa);
                usuarioObj.setEndereco(endereco);
                usuarioObj.setEmail(email);
                Log.i("Resultado", usuarioObj.toString());


                MainActivity.getUsuario().createUserWithEmailAndPassword(usuarioObj.getEmail(),usuarioObj.getSenha()).addOnCompleteListener((Activity) container.getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            MainActivity.getMyRef().child("Usuario").child(usuarioObj.getUID()).setValue(usuarioObj);
                            
                            MainActivity.getUsuario().getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("result", "Email sent.");
                                            }
                                        }
                                    });
                            Toast.makeText(getActivity(),"Cadastro Realizado",Toast.LENGTH_LONG).show();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            loginFragment = new LoginFragment();
                            transaction.replace(R.id.framelayout, loginFragment).
                                    setReorderingAllowed(true)
                                    .addToBackStack("")
                                    .commit();
                        }

                        else{
                            Toast.makeText(getActivity(),"Cadastro Não Realizado",Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }
        });




        return view;
    }




}