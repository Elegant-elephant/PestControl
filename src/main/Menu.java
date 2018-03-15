/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;

/**
 *
 * @author royja
 */
public class Menu {
    static Scanner lukija = new Scanner(System.in); // TODO: Rename var
    static int printSelectMenu(Object[] menu){
        if(menu.length == 0)
            return -1;
        String input = "";
        int valinta = 0;
        do {
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i+1) + ". " + menu[i]);
            }
            try {
                input = lukija.nextLine();
                valinta = Integer.parseInt(input);
            }catch(Exception e){
                //Ei numero syöte
            }
            
        } while(valinta < 1 || valinta > menu.length);
        return valinta;
    }
}
