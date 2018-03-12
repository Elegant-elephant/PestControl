/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static main.Main.lukija;

/**
 *
 * @author royja
 */
public class DateForm implements FormIF<Date> {

    @Override
    public Date create() {
        System.out.println("Syötä käyntiaika muodossa pp.kk.vvvv tt.mm:");
        String input = lukija.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh.mm");
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(input);
            System.out.println("Onnitui");
        } catch (Exception e) {
            System.out.println("Ei onnistunu");
        }
        return parsedDate;
    }

    @Override
    public void delete(Date entity) {}

    @Override
    public List<Date> search() {return new ArrayList<Date>();}

    @Override
    public Date update(Date date) {
        return date;
    }
    
}
