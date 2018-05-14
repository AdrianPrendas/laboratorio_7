package com.example.kevca.labproducto_front.Conn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Proxy {

    public static String server = "http://1c7c211a.ngrok.io/Web/Application";

    public static String request(String mem) {
        StringBuilder str = new StringBuilder();
        try {
            // Se abre la conexión
            URL url = new URL(server + "?" + mem);
            URLConnection conexion = url.openConnection();
            conexion.connect();

            // Lectura
            InputStream is = conexion.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            char[] buffer = new char[1000];
            int leido;
            while ((leido = br.read(buffer)) > 0) {
                str.append(new String(buffer, 0, leido));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
