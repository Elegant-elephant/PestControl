package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.*;
import dao.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pestcontrol");
    static EntityManager em = emf.createEntityManager();
    static Scanner lukija = new Scanner(System.in);
    static CustomerDAO customerDAO = new CustomerDAO(em);
    static CustomerVisitDAO customerVisitDAO = new CustomerVisitDAO(em);
    static PostalDAO postalDAO = new PostalDAO(em);
    static AddressDAO addressDAO = new AddressDAO(em);
    public static void main(String[] args) {
        
        List<String> valinnat = Arrays.asList("Lisää asiakaskäynti", "Poista asiakaskäynti", "Lopeta");
        String valinta = "";
        do {
            for (int i = 0; i < valinnat.size(); i++) {
                System.out.println((i+1) + ". " + valinnat.get(i));
            }
            
            valinta = lukija.nextLine();
            
            switch(valinta){
                case ("1"):
                    newCustomerVisit();
                    break;
                case ("2"):
                    deleteCustomerVisit();
                    break;
            }
            
        } while (!valinta.equals("" + (valinnat.size())));
        
        em.close();
        emf.close();
    }
    
    //Tekee uuden customerVisitin.
    //Ehdotaa nykystä tai uutta asiakasta.
    static void newCustomerVisit(){
        em.getTransaction().begin();
        Customer customer = null;
        CustomerVisit customerVisit = new CustomerVisit();
        
        System.out.println("Valitse asiakas: ");
        List<String> valinnat = Arrays.asList("Olemassa oleva", "Uusi", "Keskeytä");
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
        } while (!valinta.equals("" + (valinnat.size())) && customer == null);
        
        if(customer != null){
            customerVisit.setCustomer(customer);
            Address address = prepareAddress("Anna käyntiosoite");
            customerVisit.setAddress(address);
            // TODO: Lisää tarkistus että ajan syöttö onnistuu
            Date date = prepareDate("Syötä aika");
            customerVisit.setDatetime(date);
            customerVisit.setPests(new HashSet<Pest>());
            customerVisitDAO.addCustomerVisit(customerVisit);
            
            
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        
    }
    
    //Tulostaa olemassa olevat asiakkaat ja antaa käyttäjän valita yhden. Palautaa null jos input tyhjä.
    static Customer selectCustomer(){
        String input = "";
        List<Object[]> customerList = customerDAO.getCustomerList();
        if(customerList.size() == 0){
            System.out.println("Asiakkaita ei ole.");
            return null;
        }
        int id;
        Customer c;
        
        for(Object[] row: customerList){
            System.out.println(row[0]+": "+row[1]+" "+row[2]);
        }
        do{
            System.out.println("Anna lisättävän asiakkaan id: ");
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
    
    //Tee uusi asiakas.
    static Customer newCustomer() {
        Customer customer = new Customer();
        String input = "";
        System.out.println("Anna etunimi");
        customer.setFirstname(lukija.nextLine());
        System.out.println("Anna sukunimi");
        customer.setLastname(lukija.nextLine());
        Address billing = prepareAddress("Anna laskutusosoite");
        customer.setBillingAddress(billing);
        customerDAO.addCustomer(customer);
        return customer;
    }
    
    static Address prepareAddress(String message){
        System.out.println(message);
        String input = lukija.nextLine();
        Address address = addressDAO.getAddressByStreetAddress(input);
        if (address == null) {
            address = new Address();
            address.setStreetAddress(input);
            Postal postal = preparePostal("Anna postinumero", "Anna postitoimipaikka");
            address.setPostalCode(postal);
            addressDAO.addAddress(address);
        }
        return address;
    }
    
    static Postal preparePostal(String messagePostalCode, String messagePostitoimipaikka) {
        System.out.println(messagePostalCode);
        String postalCode = lukija.nextLine();
        Postal postal = postalDAO.getPostalByPostalcode(postalCode);
        if (postal == null) {
            postal = new Postal();
            postal.setPostalCode(postalCode);
            System.out.println(messagePostitoimipaikka);
            String postitoimipaikka = lukija.nextLine();
            postal.setPostRegion(postitoimipaikka);
            postalDAO.addPostal(postal);
            
        }
        return postal;
    }
    
    static Date prepareDate(String message) {
        System.out.println(message + " (pp.kk.vvvv tt.mm)");
        String input = lukija.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh.mm");
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(input);
            System.out.println("Onnitui");
        } catch (Exception e) {
            System.out.println("Ei onnistunu");
        }
        return parsedDate;
    }
    
    static void deleteCustomerVisit(){
        em.getTransaction().begin();
        String input = "";
        List<CustomerVisit> customerVisitList = customerVisitDAO.getVisits();
        if(customerVisitList.size() == 0){
            System.out.println("Asiakaskäyntejä ei ole.");
            em.getTransaction().rollback();
            return;
        }
        int id;
        CustomerVisit cv;
        
        for(CustomerVisit row: customerVisitList){
            Customer c = row.getCustomer();
            String nimi = c.getLastname()+ " " +c.getFirstname();
            System.out.println(row.getId() + ": " + nimi + row.getDatetime());
        }
        do{
            System.out.println("Anna poistettavan asiakaskäynnin id: ");
            input = lukija.nextLine();
            try{
                id = Integer.parseInt(input);
                cv = customerVisitDAO.getVisitById(id);
                if(cv != null){
                    customerVisitDAO.delete(cv);
                    em.getTransaction().commit();
                    return;
                } else {
                    System.out.println("Virheellinen id");
                }
                
            } catch(Exception e){
                if(!input.equals("")){
                   System.out.println("Virheeliinen valinta.");
                }
            }
        }while(!input.equals(""));
        em.getTransaction().rollback();

    }
}
