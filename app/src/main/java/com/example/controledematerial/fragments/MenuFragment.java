package com.example.controledematerial.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.controledematerial.R;


public class MenuFragment extends Fragment {
    private Button btCadastrarPatrimonio, btConsultarPatrimonio;
    private Fragment cadastroPatrimonioFragment, consultaPatrimonioFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_menu, container, false);

        btCadastrarPatrimonio = view.findViewById(R.id.buttonMenuCadastrarPatrimonio);
        btConsultarPatrimonio = view.findViewById(R.id.buttonMenuConsultarPatrimonio);

        btCadastrarPatrimonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                cadastroPatrimonioFragment = new CadastroPatrimonioFragment();

                transaction.replace(R.id.framelayout, cadastroPatrimonioFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("")
                        .commit();
            }
        });

        btConsultarPatrimonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                consultaPatrimonioFragment = new ConsultaPatrimonioFragment();

                transaction.replace(R.id.framelayout, consultaPatrimonioFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("")
                        .commit();
            }
        });


        return view;
    }
}