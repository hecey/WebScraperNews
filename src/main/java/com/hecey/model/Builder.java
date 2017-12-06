/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecey.model;

import java.util.function.Consumer;

/**
 *
 * @author katrina
 */
public class Builder<T> {
    private T instance;
    private boolean ifCond = true; // default
    public Builder(Class<T> clazz){
       try {
           instance = clazz.newInstance();
       } catch (InstantiationException | IllegalAccessException e) {
           e.printStackTrace();
       } 
    }
    
    public Builder<T> setter(Consumer<T> setter){
       if(ifCond)
           setter.accept(instance);
       return this;
    }
    
    public T get(){
       return instance;
    }
    
    public static <T> Builder<T> build(Class<T> clazz) {
       return new Builder<>(clazz);
    }

   
    
   
    
    
 }