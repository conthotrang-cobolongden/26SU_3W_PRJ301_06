/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author tungln
 */
public interface IDAO<T, K> {
    
    public boolean add(T t);
    
    public boolean remove(T t);
    
    public boolean update(T t);
    
    public ArrayList<T> listAll();
    
    public T searchByID(K id);
}
