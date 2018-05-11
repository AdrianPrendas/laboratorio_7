package com.example.kevca.labproducto_front.Conn;

import java.util.List;

/**
 *
 * @author _Adrián_Prendas_
 */
public interface BaseBL<K,T> {
    public boolean create(T obj);
    public List<T> read(K key);
    public List<T>readAll();
    public boolean update(T obj);
    public boolean delete(K key);
}
