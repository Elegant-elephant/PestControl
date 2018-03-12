/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import orm.Postal;
import dao.PostalDAO;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
/**
 *
 * @author laurijko
 */
public class PostalForm implements FormIF<Postal> {
    
    private PostalDAO postalDAO;
    private Scanner lukija;

    public PostalForm(EntityManager em) {
        lukija = new Scanner(System.in);
        postalDAO = new PostalDAO(em);
    }    
    
    @Override
    public Postal create(){
        String postalCode = "";
        boolean valid = false;
        
        do {
            System.out.println("Anna postikoodi (5 numeroinen)");
            postalCode = lukija.nextLine();
            if (postalCode.length() != 5) {
                System.out.println("Syöte ei ole 5 merkkinen. Yritä uudelleen.");
            } else {
                int index = 0;
                boolean charsValid = true;
                do {
                    try {
                        Integer.parseInt("" + postalCode.charAt(index));
                    } catch (Exception e) {
                        charsValid = false;
                        break;
                    }
                    index++;
                } while (index > postalCode.length());
                if (charsValid)
                    valid = true;
                else
                    System.out.println((index + 1) + ". merkki (" + postalCode.charAt(index) + ") ei ole numero. Yritä uudelleen");
            }
        } while (!valid);
        
        Postal postal = postalDAO.getPostalByPostalcode(postalCode);
        if (postal == null) {
            postal = new Postal();
            postal.setPostalCode(postalCode);
            System.out.println("Anna postitoimipaikka");
            String postitoimipaikka = lukija.nextLine();
            postal.setPostRegion(postitoimipaikka);
            postalDAO.addPostal(postal);
        }
        return postal;
    }
    
    @Override
    public void delete(Postal postal){
        postalDAO.delete(postal);
    }
    
    @Override
    public List<Postal> search(){
        return null;
    }
    
    @Override
    public Postal update(Postal postal){
        return postal;
    }
}
