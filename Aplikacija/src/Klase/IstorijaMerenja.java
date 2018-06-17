/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author doppe_000
 */
public class IstorijaMerenja {

   
    private int merenjeId;
    private Korisnik korisnik;
    private LocalDateTime datum;
    private Double vrednost;
    private String tipUnosa;
    private String tipInsulina;

    public IstorijaMerenja(int merenjeId, Korisnik korisnik, LocalDateTime datum, Double vrednost, String tipUnosa, String tipInsulina) {
        this.merenjeId = merenjeId;
        this.korisnik = korisnik;
        this.datum = datum;
        this.vrednost = vrednost;
        this.tipInsulina=tipInsulina;
        this.tipUnosa=tipUnosa;
    }

     /**
     * @return the tipInsulina
     */
    public String getTipInsulina() {
        return tipInsulina;
    }

    /**
     * @param tipInsulina the tipInsulina to set
     */
    public void setTipInsulina(String tipInsulina) {
        this.tipInsulina = tipInsulina;
    }

    
    public int getMerenjeId() {
        return merenjeId;
    }

    public void setMerenjeId(int merenjeId) {
        this.merenjeId = merenjeId;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public Double getVrednost() {
        return vrednost;
    }

    public void setVrednost(Double vrednost) {
        this.vrednost = vrednost;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        if(tipUnosa.equals("Glikemija"))  return "Datum: " + datum.format(formatter) +" "+getTipUnosa()+":" + getVrednost().toString() + "";
        else return "Datum: " + datum.format(formatter) +" "+getTipUnosa()+":" + getVrednost().toString() + ", "+getTipInsulina();
    }

    /**
     * @return the tipUnosa
     */
    public String getTipUnosa() {
        return tipUnosa;
    }

    /**
     * @param tipUnosa the tipUnosa to set
     */
    public void setTipUnosa(String tipUnosa) {
        this.tipUnosa = tipUnosa;
    }
}
