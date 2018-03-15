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
        addressDAO = new AddressDAO(em);
        lukija = new Scanner(System.in);
        postalForm = new PostalForm(em);
    }
    
    @Override
    public Address create() {
        System.out.println("Anna osoite");
        String streetAddress = lukija.nextLine();
        Postal postal = postalForm.create();
        Address address = addressDAO.getAddressByStreetAddressAndPostalCode(streetAddress, postal);
        
        if (address == null) {
            address = new Address();
            address.setStreetAddress(streetAddress);
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
    public List<Address> getList(){
        return addressDAO.getAddressList();
    }
    
    @Override
    public Address update(Address address){
        String[] fields = {"Osoite", "Postinumero", "Peruuta"};
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
                case 1: //Osoite
                    System.out.println("Anna osoite");
                    address.setStreetAddress(lukija.nextLine());
                    // TODO: Katso onko tietokannassa vastaavaa tietuetta ja muuta tähän viittaavat tietueet viittaamaan olemassa olevaa addressia.
                    break;
                case 2: //Postinumero
                    postalForm.update(address.getPostalCode());
                    break;
                case 3:
                    return null;
            }
            
        } while(valinta < 1 || valinta > fields.length);
        addressDAO.addAddress(address);
        return address;
    }
}
