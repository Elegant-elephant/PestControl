/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.AddressDAO;
import java.util.List;
import orm.Address;
import java.util.Scanner;
import javax.persistence.EntityManager;
import orm.Postal;
/**
 *
 * @author laurijko
 */
public class AddressForm implements FormIF<Address>{
    private Scanner lukija;
    private AddressDAO addressDAO;
    private PostalForm postalForm;
    
    public AddressForm(EntityManager em){
        this.addressDAO = new AddressDAO(em);
        lukija = new Scanner(System.in);
        postalForm = new PostalForm(em);
    }
    
    @Override
    public Address create(){
        System.out.println("Anna osoite");
        String input = lukija.nextLine();
        Address address = addressDAO.getAddressByStreetAddress(input);
        if (address == null) {
            address = new Address();
            address.setStreetAddress(input);
            Postal postal = postalForm.create();
            address.setPostalCode(postal);
            addressDAO.addAddress(address);
        }
        return address;
    }
    
    
    @Override
    public void delete(Address address){
        addressDAO.delete(address);
    }
    
    @Override
    public List<Address> search(){
        return addressDAO.getAddressList();
    }
    
    @Override
    public Address update(Address address){
        
        return address;
    }
}
