/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecey.controller;

import com.hecey.model.ArrayArticles;
import com.hecey.model.Article;
import com.hecey.model.Collection;
import com.hecey.model.Item;



import java.util.function.ToIntFunction;

/**
 *
 * @author katrina
 */
public class Conf {

    public static int numberOfEntries = 30;
    public static int filterTitleParameter = 5;
    public static String url = "news.ycombinator.com/";

    public static int MAX_NUMBER_OF_ENTRIES = 30;

    public static String numberHeaderText = "N#";
    public static String titleHeaderText = "Title";
    public static String orderHeaderText = "O#";
    public static String pointsHeaderText = "Points";
    public static String commentsHeaderText = "Comments";

    public static int numberHeaderLenght = 5;
    public static int titleHeaderLenght = 80;
    public static int orderHeaderLenght = 5;
    public static int pointsHeaderLenght = 6;
    public static int commentsHeaderLenght = 8;

    public static final String NEW_LINE = System.getProperty("line.separator");

    public static ToIntFunction<? super Item> comparatorKeyComments = Item::getCommentsNumber;
    public static ToIntFunction<? super Item> comparatorKeyPoints = Item::getPoints;
    
    public static Class<? extends Item> item= Article.class;
    public static Collection entriesCollection = ArrayArticles.getInstance();
    
    public static final String PARAMETER_ITEMS= "table.itemlist tr";
    public static final String PARAMETER_ORDER="td.title";
    public static final String PARAMETER_TITLE="a.storylink";
    public static final String PARAMETER_POINTS="span.score";
    public static final String PARAMETER_COMMENTS="a:contains(comments)";
    public static final String HTTPS_STRING="https://";

}
