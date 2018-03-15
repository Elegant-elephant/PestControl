/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;

/**
 *
 * @author laurijko
 */
public interface FormIF<T> {
    abstract T create();
    abstract void delete(T entity);
    abstract List<T> getList();
    abstract T update(T entity);
}
