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
public class Main {
    
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
                switch(operation){ //Table operations
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
                        int index = 0;
                        List<Object> obs = form.search();
                        String string = "";
                        
                        if (obs.size() == 0) break;
                        String[] options = new String[obs.size() + 1];
                        for (int i = 0; i < obs.size(); i++) {
                            options[i] = obs.get(i).toString();
                        }
                        options[options.length - 1] = "Peruuta";
                        System.out.println("================= Tulokset =================");
                        index = printMenu(options);
                        System.out.println("============================================");
                        if (index == options.length) break;
                        
                        Object entity = obs.get(index-1);
                        operation = printMenu(entityOperations);
                        switch(operation){ //Entity operations
                            case 1://Muokkaa
                                System.out.println("Muokataan " + entity);
                                em.getTransaction().begin();
                                form.update(entity);
                                em.getTransaction().commit();
                                break;
                            case 2://Poista
                                System.out.println("Poistetaan " + entity);
                                em.getTransaction().begin();
                                try {
                                    form.delete(entity);
                                    em.getTransaction().commit();
                                } catch (Exception e) {
                                    System.out.println("Ei voida poistaa");
                                    em.getTransaction().rollback();
                                }
                                
                                break;
                        }
                        break;                        
                }
                
                
            }
            
            
        }while(valinta != mainMenu.length);
        // Lopeta
        em.close();
        emf.close();
    }
    
    static int printMenu(Object[] menu){
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
