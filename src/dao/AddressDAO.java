/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.*;
import orm.Address;

/**
 *
 * @author laurijko
 */
public class AddressDAO extends DAO {
    
    public AddressDAO(EntityManager em) {
        super(em);
    }
    
    public List<Object[]> getAddressList(){
        Query q = em.createQuery("SELECT a.postalCode, a.streetAddress FROM Address a");
        List<Object[]> list = q.getResultList();
        return list;
    }
    
    public Address getAddressByStreetAddress(String streetAddress){
        return em.find(Address.class, streetAddress);
    }
    
    public void addAddress(Address address){
        em.persist(address);
    }
}
