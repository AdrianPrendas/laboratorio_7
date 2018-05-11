package com.example.kevca.labproducto_front;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kevca.labproducto_front.Fragments.ProductoFragment;

import Create.c_producto_Fragment;

public class MainActivity extends AppCompatActivity implements ProductoFragment.OnFragmentInteractionListener, c_producto_Fragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Fragment miFragment = null;
    //boolean fragmentSeleccionado=false;
    //fragmentSeleccionado=true;
    //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,miFragment).addToBackStack("volver").commit();
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
