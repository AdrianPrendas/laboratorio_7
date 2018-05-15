
package com.example.kevca.labproducto_front.Conn;

import com.example.kevca.labproducto_front.Class.Jsonable;
import com.example.kevca.labproducto_front.Class.Producto;
import com.example.kevca.labproducto_front.Class.Tipo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author _Adrián_Prendas_
 */
public class ProductoBL implements BaseBL<String,Producto>{
    Gson gson = null;
    private static ProductoBL instance;
    public static ProductoBL getInstance(){
        if(instance == null){
            instance = new ProductoBL();
        }
        return instance;
    }
    ProductoBL(){
        RuntimeTypeAdapterFactory<Jsonable> rta = RuntimeTypeAdapterFactory.of(Jsonable.class, "_class")
                    .registerSubtype(Tipo.class, "Tipo")
                    .registerSubtype(Producto.class, "Producto");

        this.gson = new GsonBuilder().registerTypeAdapterFactory(rta).setDateFormat("dd/MM/yyyy").create();
    }

    @Override
    public boolean create(Producto obj) {
        String json;
        json = gson.toJson(obj);
        json = Proxy.request("action=saveProduct&product="+json);
        Exception e = gson.fromJson(json, Exception.class);
        if(e.getMessage().equals("Exito"))
            return true;
        else 
            return false;
    }

    @Override
    public List<Producto> read(String key) {
        String json = Proxy.request("action=findByName&name="+key);
        ArrayList<Producto> lp = new ArrayList();
        JsonElement elements = gson.fromJson(json, JsonElement.class);
        JsonArray arr = elements.getAsJsonArray();
        for(int i=0;i<arr.size();i++){
            Producto p = gson.fromJson(arr.get(i).toString(), Producto.class);
            lp.add(p);
        }
        return lp;
    }

    public Producto read(Integer key) {
        String json = Proxy.request("action=findById&nid="+key.toString());
        Producto p = gson.fromJson(json, Producto.class);
        return p;
    }

    @Override
    public List<Producto> readAll() {
        String json = Proxy.request("action=findAll");
        ArrayList<Producto> lp = new ArrayList();
        JsonElement elements = gson.fromJson(json, JsonElement.class);
        JsonArray arr = elements.getAsJsonArray();
        for(int i=0;i<arr.size();i++){
            Producto p = gson.fromJson(arr.get(i).toString(), Producto.class);
            lp.add(p);
        }
        return lp;
    }

    @Override
    public boolean update(Producto obj) {
        String json;
        json = gson.toJson(obj);
        json = Proxy.request("action=update&product="+json);
        Exception e = gson.fromJson(json, Exception.class);
        if(e.getMessage().equals("Exito"))
            return true;
        else 
            return false;
    }

    @Override
    public boolean delete(String key) {
        String json = Proxy.request("action=delete&id="+key);
        Exception e = gson.fromJson(json, Exception.class);
        if(e.getMessage().equals("Exito"))
            return true;
        else 
            return false;
    }
    
}
