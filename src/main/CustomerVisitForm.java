/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import orm.CustomerVisit;
import dao.CustomerVisitDAO;
import javax.persistence.EntityManager;
import java.util.Scanner;
/**
 *
 * @author laurijko
 */
public class CustomerVisitForm {
    private CustomerVisitDAO customerVisistDAO;
    private Scanner lukija;

    public CustomerVisitForm(EntityManager em) {
        customerVisistDAO = new CustomerVisitDAO(em);
        lukija = new Scanner(System.in);
    }
    
    protected CustomerVisit createCustomerVisit(){
        
        return null;
    }
    
    protected void deleteCustomerVisit(CustomerVisit customerVisit){
        
    }
    
    protected CustomerVisit[] searchCustomerVisit(){
        
        return null;
    }
    
    protected CustomerVisit updateCustomerVisit(CustomerVisit customerVisit){
        
        return customerVisit;
    }
}
