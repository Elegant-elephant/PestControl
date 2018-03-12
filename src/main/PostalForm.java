/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import orm.Postal;
import dao.PostalDAO;
import java.util.Scanner;
import javax.persistence.EntityManager;
/**
 *
 * @author laurijko
 */
public class PostalForm {
    
    private PostalDAO postalDAO;
    private Scanner lukija;

    public PostalForm(EntityManager em) {
        postalDAO = new PostalDAO(em);
    }    
    
    protected Postal createPostal(){
        return null;
    }
    
    protected void deletePostal(Postal postal){
        
    }
    
    protected Postal[] searchPostal(String[] params){
        return null;
    }
    
    protected Postal updatePostal(Postal postal){
        return postal;
    }
}
