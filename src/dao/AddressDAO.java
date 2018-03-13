/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.*;
import orm.Address;
import orm.Postal;

/**
 *
 * @author laurijko
 */
public class AddressDAO extends DAO {
    
    public AddressDAO(EntityManager em) {
        super(em);
    }
    
    public List<Address> getAddressList(){
        Query q = em.createQuery("SELECT a FROM Address a");
        List<Address> list = q.getResultList();
        return list;
    }
    
    public List<Address> getAddressesByStreetAddress(String streetAddress){
        String s = "SELECT a FROM Address a WHERE a.streetAddress = :sa";
        Query q = em.createQuery(s);
        q.setParameter("sa", streetAddress);
        return q.getResultList();
    }
    
    public Address getAddressByStreetAddressAndPostalCode(String sa, Postal pc) {
        String s = "SELECT a from Address a WHERE a.streetAddress = :sa AND a.postalCode = :pc";
        Query q = em.createQuery(s);
        q.setParameter("sa", sa);
        q.setParameter("pc", pc);
        List<Address> adds = q.getResultList();
        if (adds.isEmpty()) return null;
        return adds.get(0);
    }
    
    public void addAddress(Address address){
        em.persist(address);
    }
    
    public void delete(Address address){
        em.remove(address);
    }
}
