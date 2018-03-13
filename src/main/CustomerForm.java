/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;
import dao.CustomerDAO;
import java.util.List;
import orm.Customer;
import javax.persistence.EntityManager;
import static main.Main.lukija;
import orm.Address;

/**
 *
 * @author laurijko
 */
public class CustomerForm implements FormIF<Customer> {
    
    private Scanner lukija;
    private CustomerDAO customerDAO;
    private AddressForm addressForm;

    public CustomerForm(EntityManager em) {
        lukija = new Scanner(System.in);
        customerDAO = new CustomerDAO(em);
        addressForm = new AddressForm(em);
    }
    
    @Override
    public Customer create(){
        Customer customer = new Customer();
        
        System.out.println("Anna etunimi");
        customer.setFirstname(lukija.nextLine());
        
        System.out.println("Anna sukunimi");
        customer.setLastname(lukija.nextLine());
        
        Address billing = addressForm.create();
        customer.setBillingAddress(billing);
        
        customerDAO.addCustomer(customer);
        return customer;
    }
    
    @Override
    public void delete(Customer customer){
        customerDAO.deleteCustomer(customer);
    }
    
    @Override
    public List<Customer> search(){
        return customerDAO.getCustomerList();
    }
    
    @Override
    public Customer update(Customer customer){
        String[] fields = {"Etunimi", "Sukunimi", "Laskutusosoite"};
        String input = "";
        int valinta = 0;
        do {
            for (int i = 0; i < fields.length; i++) {
                System.out.println((i+1) + ". " + fields[i]);
            }
            try {
                input = lukija.nextLine();
                valinta = Integer.parseInt(input);
            }catch(Exception e){
                //Ei numero syöte
            }
            
            switch(valinta){
                case 1: //Etunimi
                    System.out.println("Anna uusi etunimi");
                    customer.setFirstname(lukija.nextLine());
                    break;
                case 2: //Sukunimi
                    System.out.println("Anna uusi sukunimi");
                    customer.setLastname(lukija.nextLine());
                    break;
                case 3: //Laskutusosoite
                    customer.setBillingAddress(addressForm.create());
                    break;
            }
            
        } while(valinta < 1 || valinta > fields.length);
        customerDAO.addCustomer(customer);
        return customer;
    }
}
