package com.example.kevca.labproducto_front.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kevca.labproducto_front.Class.Producto;
import com.example.kevca.labproducto_front.R;

import java.util.ArrayList;

/**
 * Created by kevca on 5/10/2018.
 */

public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ProductoViewHolder> implements View.OnClickListener {
    ArrayList<Producto> listaProductos;
    private View.OnClickListener listener;

    public AdapterProducto(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_producto,null,false);
        view.setOnClickListener(this);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterProducto.ProductoViewHolder holder, int position) {
        holder.tvNombreA.setText(listaProductos.get(position).getNombre());
        holder.tvCodigoA.setText(String.valueOf(listaProductos.get(position).getCodigo()));
        holder.tvPrecioA.setText(String.valueOf(listaProductos.get(position).getPrecio()));
        if(listaProductos.get(position).getImportado()){
            holder.tvImportado.setText("Si");
        }else{
            holder.tvImportado.setText("No");
        }
        holder.tvTipoA.setText(listaProductos.get(position).getTipo());
        holder.tvPorcentajeA.setText(String.valueOf(listaProductos.get(position).getPorcentaje()));
        holder.tvImpuestoA.setText(String.valueOf(listaProductos.get(position).getImpuesto()));
        holder.tvPrecioFinalA.setText(String.valueOf(listaProductos.get(position).getPrecioFinal()));
        holder.itemView.setTag(listaProductos.get(position).getCodigo());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombreA, tvCodigoA,tvPrecioA,tvImportado, tvTipoA,tvPorcentajeA,tvImpuestoA,tvPrecioFinalA;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            tvNombreA= (TextView) itemView.findViewById(R.id.tvNombreA);
            tvCodigoA= (TextView) itemView.findViewById(R.id.tvCodigoA);
            tvPrecioA= (TextView) itemView.findViewById(R.id.tvPrecioA);
            tvImportado= (TextView) itemView.findViewById(R.id.tvImportado);
            tvTipoA= (TextView) itemView.findViewById(R.id.tvTipoA);
            tvPorcentajeA= (TextView) itemView.findViewById(R.id.tvPorcentajeA);
            tvImpuestoA= (TextView) itemView.findViewById(R.id.tvImpuestoA);
            tvPrecioFinalA= (TextView) itemView.findViewById(R.id.tvPrecioFinalA);
        }
    }
    public void filterList(ArrayList<Producto> listaProductosBusqueda){
        listaProductos =listaProductosBusqueda;
        notifyDataSetChanged();
    }
}
