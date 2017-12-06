/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecey.model;

import com.hecey.model.Item;
import java.util.function.ToIntFunction;

/**
 *
 * @author katrina
 */
public interface Collection {

    

    public void addEntry(Item entry);

    public void displayEntries();

    public <T> Collection ordeyByNumberOf(ToIntFunction<? super Item> keyExtractor);

    public Collection ordeyByComments();

    public Collection ordeyByPoints();

    public Collection FilterByTitleMoreThan(int number);

    public Collection FilterByTitleLessThanOrEqual(int number);

    public boolean hasEntries();

    public Collection removeAllEntries();

    //public Collection getInstance();

    public int size();

}
