/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author doppe_000
 */
public class Korisnik {

    private int korisnikId;
    private String ime;
    private String prezime;
    private String jmbg;
    private String lbo;
    private String tel;
    private String mobil;
    private LocalDate datumRodjenja;
    private Double visina;
    private Double tezina;
    private String napomena;
    private String pol;

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }
    private String mail;
    private String lozinka;

    public Korisnik() {

    }

    public Korisnik(int korisnikId, String ime, String prezime, String jmbg, String lbo, String tel, String mobil, LocalDate datumRodjenja, Double visina, Double tezina, String napomena, String pol, String mail, String lozinka) {
        this.korisnikId = korisnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.lbo = lbo;
        this.tel = tel;
        this.mobil = mobil;
        this.datumRodjenja = datumRodjenja;
        this.visina = visina;
        this.tezina = tezina;
        this.napomena = napomena;
        this.pol = pol;
        this.mail = mail;
        this.lozinka = lozinka;
     
    }

    @Override
    public String toString() {
        return "Korisnik{" + "korisnikId=" + korisnikId + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", lbo=" + lbo + ", tel=" + tel + ", mobil=" + mobil + ", datumRodjenja=" + datumRodjenja + ", visina=" + visina + ", tezina=" + tezina + ", napomena=" + napomena + ", pol=" + pol + ", mail=" + mail + ", lozinka=" + lozinka +'}';
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public String getLbo() {
        return lbo;
    }

    public String getTel() {
        return tel;
    }

    public String getMobil() {
        return mobil;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public Double getVisina() {
        return visina;
    }

    public Double getTezina() {
        return tezina;
    }

    public String getMail() {
        return mail;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setLbo(String lbo) {
        this.lbo = lbo;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public void setVisina(Double visina) {
        this.visina = visina;
    }

    public void setTezina(Double tezina) {
        this.tezina = tezina;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    /**
     *
     * @param datumRodjenja
     */
    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

}
