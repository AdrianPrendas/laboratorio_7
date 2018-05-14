package com.example.kevca.labproducto_front;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import com.example.kevca.labproducto_front.Fragments.ProductoFragment;

import Create.c_producto_Fragment;

public class MainActivity extends AppCompatActivity implements ProductoFragment.OnFragmentInteractionListener, c_producto_Fragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ProductoFragment productoFragment = (ProductoFragment)
                getSupportFragmentManager().findFragmentById(R.id.frame_container);
        //above part is to determine which fragment is in your frame_container
        setFragment(new ProductoFragment());
    }
    //Fragment miFragment = null;
    //boolean fragmentSeleccionado=false;
    //fragmentSeleccionado=true;
    //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,miFragment).addToBackStack("volver").commit();



    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
