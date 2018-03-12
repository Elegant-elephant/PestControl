/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author laurijko
 */
public class Main2 {
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pestcontrol");
    static EntityManager em = emf.createEntityManager();
    
    static Scanner lukija = new Scanner(System.in);
    
    static CustomerForm customerForm = new CustomerForm(em);
    static CustomerVisitForm customerVisitForm = new CustomerVisitForm(em);
    static PostalForm postalForm = new PostalForm(em);
    static AddressForm addressForm = new AddressForm(em);
    static PestForm pestForm = new PestForm(em);
    
    public static void main(String[] args) {
        String[] mainMenu = {"Asiakas", "Asiakaskäynti", "Osoite", "Tuholainen", "Lopeta"};
        String[] tableOperations = {"Luo uusi", "Etsi", "Peruuta"};
        String[] entityOperations = {"Muokkaa", "Poista", "Peruuta"};
        
        int valinta = 0;
        do {
            valinta = printMenu(mainMenu);
            
            FormIF form = null;
            
            switch(valinta){
                case 1: //Asiakas
                    form = customerForm;
                    break;
                case 2: //Asiakaskäynti
                    form = customerVisitForm;
                    break;
                case 3: //Osoite
                    form = addressForm;
                    break;
                case 4: //Tuholainen
                    form = pestForm;
                    break;
                case 5: //Lopeta
                    break;
            }
            
            if(form != null){
                int operation = printMenu(tableOperations);
                switch(operation){
                    case 1: //Luo uusi
                        em.getTransaction().begin();
                        Object o = form.create();
                        if (o != null) {
                            em.getTransaction().commit();
                        } else {
                            em.getTransaction().rollback();
                        }
                        break;
                    case 2: //Etsi
                        List<Object> obs = form.search();
                        String string = "";
                        System.out.println("================= Tulokset =================");
                        for (Object ob : obs) {
                            if (!string.equals("")) string += "\n";
                            string += ob;
                        }
                        System.out.println(string);
                        System.out.println("============================================");
                        break;                        
                }
                
                
            }
            
            
        }while(valinta != mainMenu.length);
        // Lopeta
        em.close();
        emf.close();
    }
    
    static int printMenu(String[] menu){
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
