/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.AddressDAO;
import orm.Address;
import java.util.Scanner;
import javax.persistence.EntityManager;
/**
 *
 * @author laurijko
 */
public class AddressForm {
    private Scanner lukija;
    private AddressDAO addressDAO;
    
    public AddressForm(EntityManager em){
        this.addressDAO = new AddressDAO(em);
        lukija = new Scanner(System.in);
    }
    
    protected Address createAddress(){
        
        return null;
    }
    
    protected void deleteAddress(Address address){
        
    }
    
    protected Address[] searchAddress(){
        
        return null;
    }
    
    protected Address updateAddress(Address address){
        
        return address;
    }
}
