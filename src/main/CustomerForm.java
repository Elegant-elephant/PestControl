/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;
import dao.CustomerDAO;
import orm.Customer;
import javax.persistence.EntityManager;

/**
 *
 * @author laurijko
 */
public class CustomerForm {
    
    private Scanner lukija;
    private CustomerDAO customerDAO;

    public CustomerForm(EntityManager em) {
        customerDAO = new CustomerDAO(em);
        lukija = new Scanner(System.in);
    }
    
    protected Customer createCustomer(){
        
        return null;
    }
    
    protected void deleteCustomer(Customer customer){
        
    }
    
    protected Customer[] searchCustomer(){
        
        return null;
    }
    
    protected Customer updateCustomer(Customer customer){
        
        return customer;
    }
}
