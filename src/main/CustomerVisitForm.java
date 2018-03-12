/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import orm.CustomerVisit;
import dao.CustomerVisitDAO;
import java.util.List;
import javax.persistence.EntityManager;
import java.util.Scanner;
/**
 *
 * @author laurijko
 */
public class CustomerVisitForm implements FormIF<CustomerVisit> {
    private CustomerVisitDAO customerVisistDAO;
    private Scanner lukija;

    public CustomerVisitForm(EntityManager em) {
        customerVisistDAO = new CustomerVisitDAO(em);
        lukija = new Scanner(System.in);
    }
    
    @Override
    public CustomerVisit create(){
        
        return null;
    }
    
    @Override
    public void delete(CustomerVisit customerVisit){
        
    }
    
    @Override
    public List<CustomerVisit> search(){
        
        return null;
    }
    
    @Override
    public CustomerVisit update(CustomerVisit customerVisit){
        
        return customerVisit;
    }
}
