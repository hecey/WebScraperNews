/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecey.webscraper;



import com.hecey.controller.Conf;
import com.hecey.controller.DataExtractor;
import com.hecey.model.Collection;
import java.util.Scanner;

/**
 *
 * @author kat
 */
public class Menu {

    private static int choice = 0;
    
    private static int filterTitleParameter = Conf.filterTitleParameter;
    private static String url = Conf.url;

    public static int getOption() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Extract Data from URL again");
        System.out.println("2 - Ordered by amount of comments");
        System.out.println("3 - Ordered by points");
        System.out.format("4 - Filter all previous entries with More Than %s Words in the title"
                + Conf.NEW_LINE, Conf.filterTitleParameter);
        System.out.format("5 - Filter all previous entries with less than or equal to %s words"
                + Conf.NEW_LINE, Conf.filterTitleParameter);
        System.out.println("6 - Quit");
        do {
            while (!input.hasNextInt()) {
                System.out.println("That's not a number!");
                input.next(); // this is important!
            }
            selection = input.nextInt();
        } while (selection <= 0);
        return selection;

    }

    public static void showAndValidateMenu(Collection collection) {
        do {
            choice = Menu.getOption();

            switch (choice) {
                case 1:
                    System.out.println("Wait please.... obtaining data...");

                    collection.removeAllEntries();
                    collection = DataExtractor.scrapeURL(url, collection);

                    System.out.println("Data Extracted" + Conf.NEW_LINE);

                    collection.displayEntries();
                    break;
                case 2:
                    if (collection != null) {
                        collection
                                .ordeyByComments()
                                .displayEntries();
                    }
                    break;
                case 3:
                    if (collection != null) {
                        collection
                                .ordeyByNumberOf(Conf.comparatorKeyPoints)
                                .displayEntries();
                    }
                    break;
                case 4:
                    if (collection != null) {
                        collection
                                .FilterByTitleMoreThan(filterTitleParameter)
                                .displayEntries();
                    }
                    break;
                case 5:
                    if (collection != null) {
                        collection
                                .FilterByTitleLessThanOrEqual(filterTitleParameter)
                                .displayEntries();
                        break;
                    }
                case 6:
                    System.out.println("End");
                    break;
                default:
                // The user input an unexpected choice.
            }
        } while (choice != 6);
    }

}
