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
        postalDAO = new PostalDAO(em);
    }    
    
    @Override
    public Postal create(){
        return null;
    }
    
    @Override
    public void delete(Postal postal){
        
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
