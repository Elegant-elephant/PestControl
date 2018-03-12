/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import orm.Pest;

/**
 *
 * @author royja
 */
public class PestDAO extends DAO {

    public PestDAO(EntityManager em) {
        super(em);
    }
    
    public List<Pest> getPestList(){
        Query q = em.createQuery("SELECT p FROM Pest p");
        List<Pest> list = q.getResultList();
        return list;
    }
    
    public Pest getPestById(int id){
        return em.find(Pest.class, id);
    }
    
    public List<Pest> getPestByName(String name) {
        String lause = "SELECT p FROM Pest p WHERE p.name = :name";
        Query kysely = em.createQuery(lause);
        kysely.setParameter("name", name);        
        return kysely.getResultList();
    }
    
    public void addPest(Pest pest){
        em.persist(pest);
    }
    
    public void deletePest(Pest pest) {
        em.remove(pest);
    }
}
