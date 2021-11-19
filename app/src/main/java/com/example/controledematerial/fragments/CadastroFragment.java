package com.example.controledematerial.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controledematerial.R;
import com.example.controledematerial.model.Usuario;

public class CadastroFragment extends Fragment {
    private EditText textNome, textCpf, textUsuario, textSenha;
    private Button btProximo;
    private CadastroFragment2 cadastroFragment2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_cadastro, container, false);

        textNome = view.findViewById(R.id.editTextNomeCadastro);
        textCpf = view.findViewById(R.id.editTextCpfCadastro);
        textUsuario = view.findViewById(R.id.editTextUsuarioCadastro);
        textSenha = view.findViewById(R.id.editTextSenhaCadastro);
        btProximo = view.findViewById(R.id.buttonCadastroProximo);

        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = textNome.getText().toString().trim();
                String cpf = textCpf.getText().toString().trim();
                String usuario = textUsuario.getText().toString().trim();
                String senha = textSenha.getText().toString().trim();

                if(nome.equals("")||cpf.equals("")||senha.equals("")||usuario.equals("")){
                    Toast.makeText(getActivity(),"Preecha todos os campos", Toast.LENGTH_SHORT).show();
                }
                else {

                    Usuario usuarioObj = new Usuario();
                    usuarioObj.setNome(nome);
                    usuarioObj.setCPF(cpf);
                    usuarioObj.setUsuario(usuario);
                    usuarioObj.setSenha(senha);


                    Bundle cadastroBundle = new Bundle();
                    cadastroBundle.putSerializable("cadastro", usuarioObj);
                    // getParentFragmentManager().setFragmentResult("usuario", cadastroBundle);

                    cadastroFragment2 = new CadastroFragment2();
                    cadastroFragment2.setArguments(cadastroBundle);

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.framelayout, cadastroFragment2)
                            .setReorderingAllowed(true)
                            .addToBackStack("")
                            .commit();
                }




            }
        });




        return view;
    }
}