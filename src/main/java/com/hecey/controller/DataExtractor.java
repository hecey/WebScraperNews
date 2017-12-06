/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecey.controller;

import com.hecey.model.Builder;
import com.hecey.model.Collection;
import com.hecey.model.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kat
 */
public class DataExtractor {

    private static String title = "";
    private static int points = 0;
    private static int orderNumber = 0;
    private static int commentsNumber = 0;

    public static String getUrlCodeAsString(URL urlObj) {
        //URL urlObj=null;
        URLConnection urlCon = null;
        BufferedReader in = null;
        String outputText = "";

        try {
            urlCon = urlObj.openConnection();
        } catch (IOException ex) {
            outputText = "ERROR: There was an error connecting to the URL";
        }

        try {
            in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                outputText += line;
            }
            in.close();
        } catch (IOException ex) {
            outputText = "ERROR: There was an error reading from the URL";
        }

        return outputText;
    }

    public static boolean hasExtractedCode(String html) {
        if (html.startsWith("ERROR: ")) {
            System.out.println(html);
            return false;
        }
        return true;
    }

    private static int extractNumberFromText(String text) {

        int spaceIndex;

        spaceIndex = (text.contains(" "))
                ? text.indexOf(" ")
                : (text.contains(".")) ? text.indexOf(".") : text.length();

        String numberExtracted = text.substring(0, spaceIndex);

        return (NumberUtils.isCreatable(numberExtracted)) ? Integer.valueOf(numberExtracted) : 0;

    }

    public static Collection extractData(String html, int numberOfEntriesToObtain,
            Class<? extends Item> item, Collection collection) {
        Document doc = Jsoup.parse(html);
        Elements entriesExtracted = doc.select(Conf.PARAMETER_ITEMS);
        Element element;

        Iterator iterator = entriesExtracted.iterator();

        while (iterator.hasNext() && collection.size() < numberOfEntriesToObtain) {

            element = (Element) iterator.next();

            //add to collection
            if (element.text().equals("") && !title.equals("") && orderNumber != 0) {

                collection.addEntry(
                        Builder.build(item)
                                .setter(p -> p.setTitle(title))
                                .setter(p -> p.setOrderNumber(orderNumber))
                                .setter(p -> p.setPoints(points))
                                .setter(p -> p.setCommentsNumber(commentsNumber))
                                .get());
                clearPropertiesForNewEntry();

            } else {

                orderNumber = getNumberFromElement(element, Conf.PARAMETER_ORDER, orderNumber);
                title = getTextFromElement(element, Conf.PARAMETER_TITLE, title);
                points = getNumberFromElement(element, Conf.PARAMETER_POINTS, points);
                commentsNumber = getNumberFromElement(element, Conf.PARAMETER_COMMENTS, commentsNumber);
            }

        }

        return collection;
    }

    private static void clearPropertiesForNewEntry() {
        title = "";
        points = 0;
        orderNumber = 0;
        commentsNumber = 0;
    }

    public static Collection scrapeURL(String url, Collection collection) {

        String html = DataExtractor.getUrlCodeAsString(stringToURL(Conf.HTTPS_STRING + url));

        return (DataExtractor.hasExtractedCode(html))
                ? DataExtractor.extractData(html, Conf.numberOfEntries, Conf.item, collection)
                : null;
    }

    private static boolean checkIsNotNull(Element text) {
        return (text != null);
    }

    private static String getElementString(Element element, String searchParameter) {
        return element.selectFirst(searchParameter).text();
    }

    private static int getNumberFromElement(Element element, String parameter, int value) {
        return checkIsNotNull(element.selectFirst(parameter))
                ? extractNumberFromText(getElementString(element, parameter))
                : value;
    }

    private static String getTextFromElement(Element element, String parameter, String value) {
        return checkIsNotNull(element.selectFirst(parameter))
                ? getElementString(element, parameter)
                : value;
    }

    public static URL stringToURL(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {

            return null;
        }
    }
}
