/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecey.model;

import com.hecey.controller.Conf;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.ToIntFunction;


/**
 *
 * @author kat
 */
public class ArrayArticles implements Collection {

    private static ArrayArticles instance = null;
    ArrayList<Item> entriesCollection;
    private static final String NEW_LINE = Conf.NEW_LINE;
    public static int MAX_NUMBER_OF_ENTRIES = Conf.MAX_NUMBER_OF_ENTRIES;
    private int i;

    protected ArrayArticles() {
        entriesCollection = new ArrayList<Item>();
    }

    public static ArrayArticles getInstance() {
        if (instance == null) {
            instance = new ArrayArticles();
        }
        return instance;
    }

    public void addEntry(Item entry) {

        if (entriesCollection.size() < MAX_NUMBER_OF_ENTRIES) {
            entriesCollection.add(entry);
        }

    }

    public void displayEntries() {
        int size = entriesCollection.size();
        System.out.format("%" + Conf.orderHeaderLenght + "s | "
                + "%" + Conf.orderHeaderLenght + "s | "
                + "%-" + Conf.titleHeaderLenght + "s | "
                + "%" + Conf.pointsHeaderLenght + "s | "
                + "%" + Conf.commentsHeaderLenght + "s | "
                + NEW_LINE,
                Conf.numberHeaderText,
                Conf.orderHeaderText,
                Conf.titleHeaderText,
                Conf.pointsHeaderText,
                Conf.commentsHeaderText);
        i = 0;
        entriesCollection.forEach((entry) -> {

            i++;
            System.out.format("%" + Conf.numberHeaderLenght + "d | "
                    + "%" + Conf.orderHeaderLenght + "d | "
                    + "%-" + Conf.titleHeaderLenght + "s | "
                    + "%" + Conf.pointsHeaderLenght + "d | "
                    + "%" + Conf.commentsHeaderLenght + "d | "
                    + NEW_LINE,
                    i,
                    entry.getOrderNumber(),
                    entry.getTitle(),
                    entry.getPoints(), entry.getCommentsNumber());
        });

        System.out.println(NEW_LINE + size + " records presented..." + NEW_LINE + NEW_LINE);
    }

    public <T> ArrayArticles ordeyByNumberOf(ToIntFunction<? super Item> keyExtractor) {

        entriesCollection.sort((Comparator<? super Item>) Comparator.comparingInt(keyExtractor));

        return this;
    }

    public ArrayArticles ordeyByComments() {

        entriesCollection.sort(Comparator.comparingInt(Item::getCommentsNumber));

        return this;
    }

    public ArrayArticles ordeyByPoints() {

        entriesCollection.sort(Comparator.comparingInt(Item::getPoints));

        return this;
    }

    public ArrayArticles FilterByTitleMoreThan(int number) {

        entriesCollection.removeIf(a -> countWords(a.getTitle()) <= number);

        return this;
    }

    public ArrayArticles FilterByTitleLessThanOrEqual(int number) {

        entriesCollection.removeIf(a -> countWords(a.getTitle()) > number);

        return this;
    }

    public boolean hasEntries() {
        if (entriesCollection == null) {
            return false;
        }
        if (entriesCollection.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayArticles removeAllEntries() {
        if (entriesCollection != null) {
            entriesCollection.clear();
        }

        return this;

    }

    private int countWords(String string) {
        String trimmed = string.trim();

        return trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
    }

    @Override
    public int size() {
        return entriesCollection.size();

    }
}
