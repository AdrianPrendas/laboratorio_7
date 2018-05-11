package Create;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kevca.labproducto_front.Class.Producto;
import com.example.kevca.labproducto_front.R;

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
    private EditText c_producto_cedula;
    private EditText c_producto_telefono;
    private EditText c_producto_mail;
    private EditText c_producto_fecha;
    private EditText c_producto_carrera;
    private EditText c_producto_contrasena;
    private TextView c_producto_titulo;
    private Button c_producto_btnGuardar;
    //private static AlumnoBL alumnobl = AlumnoBL.Companion.getInstance();
    private Producto producto;

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
    public static c_producto_Fragment newInstance(int someInt) {
        c_producto_Fragment c_producto_Fragment = new c_producto_Fragment();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        c_producto_Fragment.setArguments(args);

        return c_producto_Fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c_producto_, container, false);
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
}
