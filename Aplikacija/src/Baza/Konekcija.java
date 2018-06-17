/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baza;

import Klase.UnosInsulina;
import Klase.IstorijaMerenja;
import Klase.Korisnik;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doppe_000
 */
public class Konekcija {

    private static java.sql.Connection con = null;
    private static final String url = "jdbc:mysql://localhost/cs102";
    private static final String username = "root";
    private static final String password = "";

    // metode za korsnika
    public static int VratiSifruKorisnika() throws ParseException {
        int sifra = 0;
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT max(korisnik_ID) FROM Korisnik";
            try (Statement st = (Statement) con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    sifra = rs.getInt(1);
                }
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
        }
        return sifra + 1;
    }

    public static int sacuvajKorisnika(Korisnik k) {

        try {
            k.setKorisnikId(VratiSifruKorisnika());
        } catch (ParseException e) {
            return 0;
        }

        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO korisnik (korisnik_ID,ime_korisnik,prezime_korisnik,mail,lozinka) VALUES(?, ?, ?, ?,?) ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, k.getKorisnikId());
            stmt.setString(2, k.getIme());
            stmt.setString(3, k.getPrezime());
            stmt.setString(4, k.getMail());
            stmt.setString(5, k.getLozinka());

            stmt.execute();
            con.close();
            return 1;
        } catch (SQLException ex) {

            return 0;
        }
    }

    public static int izmeniKorisnika(Korisnik k) {
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "UPDATE korisnik SET ime_korisnik = ?, prezime_korisnik = ?, Jmbg=?, lbo=?, telefon_korisnik=?, mob_korisnik=?, datum_du=?, visina_korisnik=?, tezina_korisnik=?, pol_korisnik=?, napomena=?, mail=?, lozinka=?    WHERE korisnik_Id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(14, k.getKorisnikId());
            stmt.setString(1, k.getIme());
            stmt.setString(2, k.getPrezime());
            stmt.setString(3, k.getJmbg());
            stmt.setString(4, k.getLbo());
            stmt.setString(5, k.getTel());
            stmt.setString(6, k.getMobil());
            stmt.setDate(7, Date.valueOf(k.getDatumRodjenja()));
            stmt.setDouble(8, k.getVisina());
            stmt.setDouble(9, k.getTezina());
            stmt.setString(10, k.getPol());
            stmt.setString(11, k.getNapomena());
            stmt.setString(12, k.getMail());
            stmt.setString(13, k.getLozinka());
            stmt.execute();
            return 1;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public static void obrisiKorisnika(Korisnik k) {
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM Korisnik WHERE jmbg = ?";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.execute();
            System.out.println("Uspesno brisanje iz baze.");
        } catch (SQLException ex) {
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Korisnik> PrikaziKorisnika() throws ParseException {
        List<Korisnik> lista = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM Korisnik";
            try (Statement st = (Statement) con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Korisnik k = new Korisnik();
                    int korisnikId = rs.getInt("korisnikId");
                    String jmbg = rs.getString("jmbg");
                    String ime = rs.getString("ime");
                    String prezime = rs.getString("prezime");
                    String lbo = rs.getString("lbo");

                    String tel = rs.getString("tel");
                    String mob = rs.getString("mobil");
                    k.setDatumRodjenja(rs.getDate("datum_du").toLocalDate());
                    Double visina = rs.getDouble("visina_korisnik");
                    Double tezina = rs.getDouble("tezina_korisnik");
                    String pol = rs.getString("pol_korisnik");
                    String napomena = rs.getString("napomena");
                    String mail = rs.getString("mail");
                    String lozinka = rs.getString("lozinka");

                    k.setIme(ime);
                    k.setKorisnikId(korisnikId);
                    k.setJmbg(jmbg);
                    k.setVisina(visina);
                    k.setTezina(tezina);
                    k.setPrezime(prezime);
                    k.setTel(tel);
                    k.setMobil(mail);
                    k.setNapomena(napomena);
                    k.setPol(pol);
                    k.setMobil(mail);
                    k.setLbo(lbo);

                }
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
        }
        return lista;
    }

    public static Korisnik Login(String query) throws ParseException {

        try {
            con = DriverManager.getConnection(url, username, password);
            try (Statement st = (Statement) con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Korisnik k = new Korisnik();
                    try {
                        k.setKorisnikId(rs.getInt("korisnik_id"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setJmbg(rs.getString("jmbg"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setIme(rs.getString("ime_korisnik"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setPrezime(rs.getString("prezime_korisnik"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setLbo(rs.getString("lbo"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setTel(rs.getString("telefon_korisnik"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setMobil(rs.getString("mob_korisnik"));
                    } catch (Exception e) {
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    try {
                        k.setDatumRodjenja(LocalDate.parse(rs.getString("datum_du"), formatter));
                    } catch (Exception e) {

                    }

                    try {
                        k.setVisina(rs.getDouble("visina_korisnik"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setTezina(rs.getDouble("tezina_korisnik"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setPol(rs.getString("pol_korisnik"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setNapomena(rs.getString("napomena"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setMail(rs.getString("mail"));
                    } catch (Exception e) {
                    }

                    try {
                        k.setLozinka(rs.getString("lozinka"));
                    } catch (Exception e) {
                    }

                    con.close();

                    return k;
                }
            }
            con.close();

        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
        }
        return null;
    }

    //istorijamerenja
    public static int VratiSifruIstorijeMerenja() throws ParseException {
        int sifra = 0;
        try {

            String query = "SELECT max(istorija_merenja_id) FROM istorija_merenja";
            try (Statement st = (Statement) con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    sifra = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
        }
        return sifra + 1;
    }

    public static int sacuvajIstorijuMerenja(List<IstorijaMerenja> lista) {
        try {
            con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);
            for (int i = 0; i < lista.size(); i++) {

                int sifra= VratiSifruIstorijeMerenja();
                String query = "INSERT INTO istorija_merenja (istorija_merenja_id, korisnik_id, datum_i_vreme_im, vrednost, tip_insulina, tip_unosa) VALUES( ?, ?, ?, ?, ?, ?) ";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, sifra);
                stmt.setInt(2, lista.get(i).getKorisnik().getKorisnikId());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                stmt.setString(3, lista.get(i).getDatum().format(formatter));
                stmt.setDouble(4, lista.get(i).getVrednost());   
                stmt.setString(5, lista.get(i).getTipInsulina());             
                stmt.setString(6, lista.get(i).getTipUnosa());
                stmt.execute();
            }
            con.commit();
            con.close();
            return 1;
        } catch (Exception ex) {
            return 0;
        }

    }

    public static void izmeniIstorijuMerenja(IstorijaMerenja im) {
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "UPDATE IstorijaMerenja SET datum_i_vreme_im= ?, vrednost = ? WHERE istorija_merenja_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(3, im.getMerenjeId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setString(1, im.getDatum().format(formatter));
            stmt.setDouble(2, im.getVrednost());
            stmt.execute();
            System.out.println("Uspesno azuriranje baze.");
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void obrisiIstorijuMerenja(IstorijaMerenja im) {
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM IstorijaMerenja WHERE istorija_merenja_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, im.getMerenjeId());
            stmt.execute();
            System.out.println("Uspesno brisanje iz baze.");
        } catch (SQLException ex) {
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<IstorijaMerenja> PrikaziIstorijuMerenja(Korisnik k) throws ParseException {
        List<IstorijaMerenja> listaIstorijeMerenja = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM IstorijaMerenja where Korisik_ID=" + k.getKorisnikId();
            try (Statement st = (Statement) con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                   
                     int id= rs.getInt("istorija_merenja_id");
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                   LocalDateTime datum= LocalDateTime.parse(rs.getString("datum_i_vreme_im"), formatter);
                    double vrednost=rs.getDouble("vrednost");
                    String tipIns=rs.getString("tip_insulina");
                    String tipUnosa=rs.getString("tip_unosa");
                    
                    listaIstorijeMerenja.add(new IstorijaMerenja(id, k, datum, vrednost,tipIns,tipUnosa));
                }
            }
            con.close();

        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
        }
        return listaIstorijeMerenja;
    }

    //dnevnikMerenjaIInsulina
     public static List<IstorijaMerenja> PrikaziDnevnik(Korisnik k, LocalDateTime datumOD, LocalDateTime datumDO) throws ParseException {
        List<IstorijaMerenja> listaIstorijeMerenja = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM Istorija_Merenja where Korisnik_ID=" + k.getKorisnikId()+" order by datum_i_vreme_im desc";
            try (Statement st = (Statement) con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                   
                     int id= rs.getInt("istorija_merenja_id");
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String datumTest=rs.getString("datum_i_vreme_im");
                   LocalDateTime datum= LocalDateTime.parse(datumTest.substring(0, datumTest.length()-2), formatter);
                    double vrednost=rs.getDouble("vrednost");
                    String tipIns=rs.getString("tip_insulina");
                    String tipUnosa=rs.getString("tip_unosa");
                    
                  if(datum.isAfter(datumOD)&&datum.isBefore(datumDO))   listaIstorijeMerenja.add(new IstorijaMerenja(id, k, datum, vrednost,tipIns,tipUnosa));
                }
            }
            con.close();

        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
        }
        return listaIstorijeMerenja;
    }
    //
    public static int VratiSifruDnevnogUnosa() throws ParseException {
        int sifra = 0;
        try {

            String query = "SELECT max(id_dnevni_unso) FROM dnevni_unos";
            try (Statement st = (Statement) con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    sifra = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
        }
        return sifra + 1;
    }

    public static int sacuvajUnosInsulina(List<UnosInsulina> lista) {
        try {
            con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);
            for (int i = 0; i < lista.size(); i++) {

                int sifra=VratiSifruDnevnogUnosa();
                String query = "INSERT INTO dnevni_unos (id_dnevni_unos,korisnik_id, insulin, datum_du, kolicina_jedinica ) VALUES( ?, ?, ?, ?, ?) ";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, sifra);
                stmt.setInt(2, lista.get(i).getKorisnik().getKorisnikId());
                stmt.setString(3, lista.get(i).getInsulin());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                stmt.setString(4, lista.get(i).getDatum().format(formatter));
                stmt.setInt(5, lista.get(i).getKolicina());
                stmt.execute();
            }
            con.commit();
            con.close();
            return 1;
        } catch (Exception ex) {
            return 0;
        }

    }

    public static void izmeniDnevniUnos(UnosInsulina ui) {
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "UPDATE dnevni_unos SET datum_du= ?, kolicina_jedinica = ?, insulin = ? WHERE id_dnevni_unos = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(4, ui.getUnosId());
            stmt.setString(3, ui.getInsulin());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setString(1, ui.getDatum().format(formatter));
            stmt.setInt(2, ui.getKolicina());
            stmt.execute();
            System.out.println("Uspesno azuriranje baze.");
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void obrisiDnevniUnos(UnosInsulina ui) {
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM dnevni_unos WHERE id_dnevni_unos = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, ui.getUnosId());
            stmt.execute();
            System.out.println("Uspesno brisanje iz baze.");
        } catch (SQLException ex) {
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<UnosInsulina> PrikaziIstorijuUnosaInsulina(Korisnik k) throws ParseException {
        List<UnosInsulina> listaUnosInsulina = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM dnevni_unos where Korisnik_id=" + k.getKorisnikId();
            try (Statement st = (Statement) con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    UnosInsulina ui = new UnosInsulina();
                    ui.setUnosId(rs.getInt("id_dnevni_unos"));
                    ui.setKorisnik(k);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    ui.setDatum(LocalDateTime.parse(rs.getString("datum_du"), formatter));
                    ui.setInsulin(rs.getString("insulin"));
                    ui.setKolicina(rs.getInt("Kolicina"));
                    listaUnosInsulina.add(ui);
                }
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
        }
        return listaUnosInsulina;
    }

    //HBAC
    public static  double vratiHB1C(Korisnik k)
    {
        double glik=0;
        double br=0;
         List<IstorijaMerenja> listaIstorijeMerenja = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM Istorija_Merenja where Korisnik_ID=" + k.getKorisnikId()+" and Tip_Unosa='Glikemija'";
            try (Statement st = (Statement) con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                   
                     int id= rs.getInt("istorija_merenja_id");
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String datumTest=rs.getString("datum_i_vreme_im");
                   LocalDateTime datum= LocalDateTime.parse(datumTest.substring(0, datumTest.length()-2), formatter);
                    double vrednost=rs.getDouble("vrednost");
                    String tipIns=rs.getString("tip_insulina");
                    String tipUnosa=rs.getString("tip_unosa");
                    
                  if(datum.isAfter(LocalDateTime.now().minusMonths(3)))  
                  {
                      glik+=vrednost;
                      br++;
                  }
                }
            }
            con.close();

        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
        }
        try {
            return glik/br;
        } catch (Exception e) {
            return 0;
        }
    }
}
