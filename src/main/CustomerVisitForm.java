/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import orm.CustomerVisit;
import dao.CustomerVisitDAO;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import java.util.Scanner;
import static main.Main.em;
import orm.Address;
import orm.Customer;
/**
 *
 * @author laurijko
 */
public class CustomerVisitForm implements FormIF<CustomerVisit> {
    private Scanner lukija;
    private CustomerVisitDAO cvDAO;
    private CustomerForm cForm;
    private AddressForm aForm;
    private DateForm dForm;

    public CustomerVisitForm(EntityManager em) {
        lukija = new Scanner(System.in);
        cvDAO = new CustomerVisitDAO(em);
        cForm = new CustomerForm(em);
        aForm = new AddressForm(em);
        dForm = new DateForm();
    }
    
    @Override
    public CustomerVisit create(){
        CustomerVisit cv = new CustomerVisit();
        
        Customer c = cForm.create();
        if (c == null) return null;
        cv.setCustomer(c);
        
        Address a = aForm.create();
        if (a == null) return null;
        cv.setAddress(a);
        
        Date d = dForm.create();
        if (d == null) return null;
        cv.setDatetime(d);
        
        cvDAO.addCustomerVisit(cv);
        
        return cv;
    }
    
    @Override
    public void delete(CustomerVisit customerVisit){
        cvDAO.delete(customerVisit);
    }
    
    @Override
    public List<CustomerVisit> search(){
        return cvDAO.getVisits();
    }
    
    @Override
    public CustomerVisit update(CustomerVisit customerVisit){
        return customerVisit;
    }
}
