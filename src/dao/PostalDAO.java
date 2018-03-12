/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import javax.persistence.EntityManager;
import orm.Postal;

/**
 *
 * @author laurijko
 */
public class PostalDAO extends DAO{

    public PostalDAO(EntityManager em) {
        super(em);
    }
    
    public Postal getPostalByPostalcode(String code){
        return em.find(Postal.class, code);
    }
    
    public void addPostal(Postal postal){
        em.persist(postal);
    }
    
    public void delete(Postal postal){
        em.remove(postal);
    }
}
