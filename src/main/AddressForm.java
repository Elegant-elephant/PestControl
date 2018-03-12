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
/**
 *
 * @author laurijko
 */
public class AddressForm implements FormIF<Address>{
    private Scanner lukija;
    private AddressDAO addressDAO;
    
    public AddressForm(EntityManager em){
        this.addressDAO = new AddressDAO(em);
        lukija = new Scanner(System.in);
    }
    
    @Override
    public Address create(){
        return null;
    }
    
    
    @Override
    public void delete(Address address){
        
    }
    
    @Override
    public List<Address> search(){
        
        return null;
    }
    
    @Override
    public Address update(Address address){
        
        return address;
    }
}
