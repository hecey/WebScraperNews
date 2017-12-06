/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hecey.webscraper;

import com.hecey.controller.Conf;



/**
 *
 * @author katrina
 */
public class WebScraper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu.showAndValidateMenu(Conf.entriesCollection);

    }

}
