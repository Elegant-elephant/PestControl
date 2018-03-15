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
    private PestForm pForm;

    public CustomerVisitForm(EntityManager em) {
        lukija = new Scanner(System.in);
        cvDAO = new CustomerVisitDAO(em);
        cForm = new CustomerForm(em);
        aForm = new AddressForm(em);
        dForm = new DateForm();
        pForm = new PestForm(em);
    }
    
    @Override
    public CustomerVisit create(){
        CustomerVisit cv = new CustomerVisit();
        
        String[] options = {"Valitse olemassa oleva asiakas", "Luo uusi asiakas", "Peruuta"};
        int selection = Menu.printSelectMenu(options);
        
        switch (selection) {
            case 1:
                List<Customer> customers = cForm.getList();
                String[] selectOptions = new String[customers.size() + 1];
                System.out.println(customers.size());
                for (int i = 0; i < customers.size(); i++) {
                    selectOptions[i] = customers.get(i).toString();
                }
                selectOptions[selectOptions.length - 1] = "Peruuta";
                selection = Menu.printSelectMenu(selectOptions);
                if (selection < (selectOptions.length - 1)) cv.setCustomer(customers.get(selection - 1));
                else return null;
                break;
            case 2:
                Customer c = cForm.create();
                if (c == null) return null;
                cv.setCustomer(c);
                break;
        }
        
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
    public List<CustomerVisit> getList(){
        return cvDAO.getVisits();
    }
    
    @Override
    public CustomerVisit update(CustomerVisit customerVisit){
        String[] options = {"Päiväys", "Asiakas", "Osoite", "Tuholaiset", "Peruuta"};
        int selection = Menu.printSelectMenu(options);
        switch(selection){
            case 1: //Etunimi
                System.out.println("Edellinen päiväys: " + customerVisit.getDatetime());
                customerVisit.setDatetime(dForm.create());
            case 2: //Sukunimi
                System.out.println("Edellinen asiakas: " + customerVisit.getCustomer());
                customerVisit.setCustomer(cForm.create());
                break;
            case 3: //Laskutusosoite
                System.out.println("Edellinen osoite: " + customerVisit.getAddress());
                customerVisit.setAddress(aForm.create());
                break;
            case 4:
                customerVisit.setPests(pForm.update(customerVisit.getPests()));
                break;
            case 5:
                return null;
        }
        cvDAO.addCustomerVisit(customerVisit);
        return customerVisit;
    }
}
