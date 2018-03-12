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

/**
 *
 * @author laurijko
 */
public class CustomerForm implements FormIF<Customer> {
    
    private Scanner lukija;
    private CustomerDAO customerDAO;

    public CustomerForm(EntityManager em) {
        customerDAO = new CustomerDAO(em);
        lukija = new Scanner(System.in);
    }
    
    @Override
    public Customer create(){
        
        return null;
    }
    
    @Override
    public void delete(Customer customer){
        
    }
    
    @Override
    public List<Customer> search(){
        
        return null;
    }
    
    @Override
    public Customer update(Customer customer){
        
        return customer;
    }
}
