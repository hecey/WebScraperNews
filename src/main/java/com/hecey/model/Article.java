/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecey.model;



/**
 *
 * @author katrina
 */
public  class Article implements Item{

    private String title;
    private  int orderNumber;
    private int points;
    private int commentsNumber;

    public String getTitle() {
        return title;
    }

    public int getPoints() {
        return points;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setCommentsNumber(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    

}
