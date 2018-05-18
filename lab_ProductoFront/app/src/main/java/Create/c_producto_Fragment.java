package Create;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevca.labproducto_front.Class.Producto;
import com.example.kevca.labproducto_front.Conn.ProductoBL;
import com.example.kevca.labproducto_front.Fragments.ProductoFragment;
import com.example.kevca.labproducto_front.R;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link c_producto_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link c_producto_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class c_producto_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int codigoProducto;
    private EditText c_producto_nombre;
    private EditText c_producto_codigo;
    private EditText c_producto_precio;
    private TextView title_c_producto;
    private Button c_producto_btnGuardar;
    private Producto producto;
    private Spinner spinner;
    private CheckBox checkBox;

    private OnFragmentInteractionListener mListener;

    public c_producto_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment c_producto_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static c_producto_Fragment newInstance(String param1, String param2) {
        c_producto_Fragment fragment = new c_producto_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static c_producto_Fragment newInstance(int codigODelProducto) {
        c_producto_Fragment c_producto_Fragment = new c_producto_Fragment();
        Bundle args = new Bundle();
        args.putInt("codigoProducto", codigODelProducto);
        c_producto_Fragment.setArguments(args);

        return c_producto_Fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            codigoProducto = getArguments().getInt("codigoProducto", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_c_producto_, container, false);
        title_c_producto= (TextView) vista.findViewById(R.id.title_c_producto);
        c_producto_codigo= (EditText) vista.findViewById(R.id.c_producto_codigo);
        c_producto_nombre= (EditText) vista.findViewById(R.id.c_producto_nombre);
        c_producto_precio= (EditText) vista.findViewById(R.id.c_producto_precio);
        checkBox = (CheckBox) vista.findViewById(R.id.importado);
        c_producto_btnGuardar = (Button) vista.findViewById(R.id.c_producto_btnGuardar);
        spinner = (Spinner) vista.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterArray = ArrayAdapter.createFromResource(getContext(),R.array.tipos,android.R.layout.simple_spinner_item);
        adapterArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterArray);

        if (codigoProducto==0){
            title_c_producto.setText("Crear Nuevo Producto");
        }else{
            producto = (Producto) ProductoBL.getInstance().read(codigoProducto);
            if (producto!=null){
                updateProducto(producto);
            }else {
                Toast.makeText(getContext(),"No se encuentra el Producto a modificar",Toast.LENGTH_LONG).show();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction().replace(android.R.id.content,new ProductoFragment()).commit();
            }

        }
        c_producto_btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camposLlenos()){
                    producto=new Producto(Integer.parseInt(c_producto_codigo.getText().toString()),c_producto_nombre.getText().toString(),Float.valueOf(c_producto_precio.getText().toString()),checkBox.hasSelection(),spinner.getSelectedItem().toString());
                    String salidaTOAST="";
                    Boolean productoReturn=false;
                    //Se crea o modifica
                    if(codigoProducto==0){
                        salidaTOAST="Se agrega el Producto: '";
                        productoReturn= ProductoBL.getInstance().create(producto);
                    }
                    else {
                        salidaTOAST="Se modifica el producto: '";
                        productoReturn=ProductoBL.getInstance().update(producto);
                    }
                    if(productoReturn!=false){
                        Toast.makeText(getContext(),salidaTOAST +producto.getNombre()+"' Codigo: "+producto.getCodigo(),Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(),"No se agrega el Producto",Toast.LENGTH_SHORT).show();
                    }
                    FragmentManager manager=getFragmentManager();
                    manager.beginTransaction().replace(android.R.id.content,new ProductoFragment()).addToBackStack("bcaf").commit();
                }
                else Toast.makeText(getContext(),"Inserte informacion en todos los campos",Toast.LENGTH_SHORT).show();

            }
        });

        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void updateProducto(Producto producto){
        title_c_producto.setText("Modificar Producto");
        c_producto_codigo.setText(String.valueOf(producto.getCodigo()));
        c_producto_codigo.setEnabled(false);
        c_producto_nombre.setText(producto.getNombre());
        c_producto_precio.setText(String.valueOf(producto.getPrecio()));
        checkBox.setChecked(producto.getImportado());
        spinner.setSelection(getIndex(spinner, String.valueOf(producto.getTipo())));
        c_producto_btnGuardar.setText("Modificar");
    }
    private boolean camposLlenos(){
        if(c_producto_codigo.getText().toString().trim().equals("") || c_producto_nombre.getText().toString().trim().equals("") || c_producto_precio.getText().toString().trim().equals("")){
        return false;
        }
        return true;
        }
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
}
