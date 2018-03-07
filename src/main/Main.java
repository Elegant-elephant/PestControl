package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.*;
import dao.*;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pestcontrol");
    static EntityManager em = emf.createEntityManager();
    static Scanner lukija = new Scanner(System.in);
    static CustomerDAO customerDAO = new CustomerDAO(em);
    public static void main(String[] args) {
        
        List<String> valinnat = Arrays.asList("Lisää", "Poista", "Lopeta");
        String valinta = "";
        do {
            for (int i = 0; i < valinnat.size(); i++) {
                System.out.println((i+1) + ". " + valinnat.get(i));
            }
            
            valinta = lukija.nextLine();
        } while (!valinta.equals("" + (valinnat.size())));
        
        em.close();
        emf.close();
    }
    
    //Tekee uuden customerVisitin.
    //Ehdotaa nykystä tai uutta asiakasta.
    static void newCustomerVisit(){
        Customer customer = null;
        CustomerVisit customerVisit;
        
        System.out.println("Anna asiaks");
        List<String> valinnat = Arrays.asList("Olemassa oleva", "Uusi", "Lopeta");
        String valinta = "";
        do {
            for (int i = 0; i < valinnat.size(); i++) {
                System.out.println((i+1) + ". " + valinnat.get(i));
            }
            
            valinta = lukija.nextLine();
            switch(valinta) {
                case "1":
                    customer = selectCustomer();
                    break;
                case "2":
                    customer = newCustomer();
                    break;
            }
        } while (!valinta.equals("" + (valinnat.size())));
        
        if(customer != null){
            //tee customerVisit
        }
    }
    
    //Tulostaa olemassa olevat asiakkaat ja antaa käyttäjän valita yhden. Palautaa null jos input tyhjä.
    static Customer selectCustomer(){
        String input = "";
        List<Object[]> customerList = customerDAO.getCustomerList();
        int id;
        Customer c;
        for(Object[] row: customerList){
            System.out.println(row[0]+": "+row[1]+" "+row[2]);
        }
        do{
            input = lukija.nextLine();
            try{
                id = Integer.parseInt(input);
                c = customerDAO.getCustomerById(id);
                if(c != null){
                    return c;
                } else {
                    System.out.println("Virheellinen id");
                }
                
            } catch(Exception e){
                if(!input.equals("")){
                   System.out.println("Virheeliinen valinta.");
                }
            }
        }while(!input.equals(""));
        return null;
    }
    
    //Tee uusi asiakas. Billing address luonti ongelma.
    static Customer newCustomer() {
        Customer customer = new Customer();
        String input = "";
        System.out.println("Anna etunimi");
        customer.setFirstname(lukija.nextLine());
        System.out.println("Anna sukunimi");
        customer.setLastname(lukija.nextLine());
        System.out.println("");
        
        return null;
    }
}
