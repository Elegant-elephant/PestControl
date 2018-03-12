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
        System.out.println("Anna postikoodi");
        String postalCode = lukija.nextLine();
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
