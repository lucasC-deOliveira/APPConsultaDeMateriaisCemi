package com.example.controledematerial.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


public class LoginFragment extends Fragment {
    private Button btCadastrar, btLogin;
    private CadastroFragment cadastroFragment;
    private MenuFragment menuFragment;
    private EditText textEmail, textSenha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btCadastrar = view.findViewById(R.id.buttonFazerCadastroLogin);
        btLogin = view.findViewById(R.id.buttonLogin);
        textEmail = view.findViewById(R.id.editTextLogin);
        textSenha = view.findViewById(R.id.editTextSenhaLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String email = textEmail.getText().toString().trim();
            String senha = textSenha.getText().toString().trim();

            MainActivity.getUsuario().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getActivity(),"Login Realizado!",Toast.LENGTH_LONG).show();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        menuFragment = new MenuFragment();
                        transaction.replace(R.id.framelayout, menuFragment)
                                .setReorderingAllowed(true)
                                .commit();



                    }
                    else{
                        Toast.makeText(getActivity(),"Login Não Realizado, Verifique os campos!",Toast.LENGTH_LONG).show();
                    }
                }
            });
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastroFragment = new CadastroFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout,cadastroFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("")
                        .commit();


            }
        });

        return  view;
    }
}