package com.example.controledematerial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.controledematerial.fragments.LoginFragment;
import com.example.controledematerial.fragments.MenuFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Fragment loginFragment;
    private  static Fragment menuFragment;
    private static FirebaseDatabase dataBase;
    private static DatabaseReference myRef ;
    private  static FirebaseAuth usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarFirebase();
        usuario= FirebaseAuth.getInstance();
        usuario.signOut();


        menuFragment = new MenuFragment();

        if(usuario.getCurrentUser()==null){
            loginFragment = new LoginFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.framelayout, loginFragment);
            transaction.commit();
        }

        else{
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.framelayout, menuFragment);
        transaction.commit();}

    }

    public static FirebaseDatabase getDatabase() {
        return dataBase;
    }

    public static DatabaseReference getMyRef() {
        return myRef;
    }

    public static Fragment getMenuFragment() {
        return menuFragment;
    }

    public static FirebaseAuth getUsuario() {
        return usuario;
    }

    private void iniciarFirebase(){
        dataBase = FirebaseDatabase.getInstance();
        myRef = dataBase.getReference();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
        super.onBackPressed();

        }
    }



}