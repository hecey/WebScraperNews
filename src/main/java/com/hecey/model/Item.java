/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecey.model;

/**
 *
 * @author kat
 */
public interface Item {

    public int getOrderNumber();

    public String getTitle();

    public int getPoints();

    public int getCommentsNumber();

    public void setTitle(String title);

    public void setOrderNumber(int orderNumber);

    public void setPoints(int points);

    public void setCommentsNumber(int commentsNumber);
}
