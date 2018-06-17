/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author doppe_000
 */
public class UnosInsulina {

    private int unosId;
    private LocalDateTime datum;
    private int kolicina;
    private Korisnik korisnik;
    private String insulin;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "Datum i vreme: " + datum.format(formatter) + " Insulin:" + getInsulin() + " Kolicina Insulina:" + getKolicina();
    }

    public UnosInsulina(int unosId, LocalDateTime datum, int kolicina, Korisnik korisnik, String insulin) {
        this.unosId = unosId;
        this.datum = datum;
        this.kolicina = kolicina;
        this.korisnik = korisnik;
        this.insulin = insulin;
    }

    
    public UnosInsulina() {

    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public String getInsulin() {
        return insulin;
    }

    public int getUnosId() {
        return unosId;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setUnosId(int unosId) {
        this.unosId = unosId;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public void setInsulin(String insulin) {
        this.insulin = insulin;
    }
}
