package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Baza {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:test1.db";

    private static Connection conn;
    private static Statement stmt;

    //Połączenie z bazą
    public Baza() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem połączenia");
            e.printStackTrace();
        }
    }


    //Tworzenie tabel
    public void createKlient() throws SQLException {
        String createKlienci="CREATE TABLE IF NOT EXISTS klient(id_klienta  INTEGER PRIMARY KEY AUTOINCREMENT, imie VARCHAR(20),nazwisko VARCHAR(20),pesel VARCHAR(20),mail VARCHAR(30))";
        stmt.execute(createKlienci);
    }
    public void createPracownicy() throws SQLException{
        String createPracownicy="CREATE TABLE IF NOT EXISTS pracownik(id_pracownika  INTEGER PRIMARY KEY AUTOINCREMENT, imie VARCHAR(20),nazwisko VARCHAR(20),pesel VARCHAR(12),telefon INT)";
        stmt.execute(createPracownicy);
    }
    public void createProdukty() throws SQLException {
        String createProdukty="CREATE TABLE IF NOT EXISTS produkt(id_produktu  INTEGER PRIMARY KEY AUTOINCREMENT, nazwa VARCHAR(20),producent VARCHAR(20),kwota INT)";
        stmt.execute(createProdukty);
    }
    public void createMagazyny() throws SQLException {
        String createMagazyny="CREATE TABLE IF NOT EXISTS magazyn(id_pr_w_mag  INTEGER PRIMARY KEY AUTOINCREMENT, nazwa VARCHAR(20),producent VARCHAR(20),ilosc INT)";
        stmt.execute(createMagazyny);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void usunPracownicy() throws SQLException {
        String deletePracownicy="DROP TABLE 'pracownik'";
        stmt.execute(deletePracownicy);
    }
    public void usunKlienci() throws SQLException {
        String deleteKlienci="DROP TABLE 'klient'";
        stmt.execute(deleteKlienci);
    }
    public void usunProdukty() throws SQLException {
        String deleteProdukty="DROP TABLE 'produkt'";
        stmt.execute(deleteProdukty);
    }
    public void usunMagazyny() throws SQLException {
        String deleteMagazyny="DROP TABLE 'magazyn'";
        stmt.execute(deleteMagazyny);
    }

    //dodawanie pracownika
    public void dodajPracownika(String imie, String nazwisko, String pesel, int telefon) {
        try {
            Connection conn = stmt.getConnection();
            String dodajpracownika = "INSERT INTO pracownik(imie, nazwisko, pesel, telefon) VALUES (?,?,?,?);";
            PreparedStatement prepStmt = conn.prepareStatement(dodajpracownika);
            prepStmt.setString(1, imie);
            prepStmt.setString(2, nazwisko);
            prepStmt.setString(3, pesel);
            prepStmt.setInt(4, telefon);
            prepStmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Wpisywanie do tabeli produkt

    public void dodajProdukt(String nazwa,String producent, int kwota) {
        try {
            Connection conn = stmt.getConnection();
            String dodajProdukt = "INSERT INTO produkt(nazwa, producent, kwota) VALUES (?,?,?);";
            PreparedStatement prepStmt = conn.prepareStatement(dodajProdukt);
            prepStmt.setString(1, nazwa);
            prepStmt.setString(2, producent);
            prepStmt.setInt(3, kwota);
            prepStmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Wpisywanie do tabeli Package.Magazyn

    public void dodajMagazyn(String nazwa,String producent,int ilosc) {
        try {
            Connection conn = stmt.getConnection();
            String dodajMagazyn = "INSERT INTO magazyn(nazwa, producent, ilosc) VALUES (?,?,?);";
            PreparedStatement prepStmt = conn.prepareStatement(dodajMagazyn);
            prepStmt.setString(1, nazwa);
            prepStmt.setString(2, producent);
            prepStmt.setInt(3, ilosc);
            prepStmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Wpisywanie do tabeli

    public void dodajKlient(String imie, String nazwisko, String pesel, String mail) {
        try {
            Connection conn = stmt.getConnection();
            String dodajKlienta = "INSERT INTO klient(imie,nazwisko,pesel,mail) VALUES (?,?,?,?);";
            PreparedStatement prepStmt = conn.prepareStatement(dodajKlienta);
            prepStmt.setString(1,imie);
            prepStmt.setString(2,nazwisko);
            prepStmt.setString(3,pesel);
            prepStmt.setString(4,mail);
            prepStmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //Usuwanie rekordów


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Wyświetlanie tabeli Pracownicy
    public ObservableList<DanePracownik> danePracowniks;

    public void zaladujPracownik() throws SQLException {
        this.danePracowniks = FXCollections.observableArrayList();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM pracownik");
        while(rs.next()){
            this.danePracowniks.add(new DanePracownik(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
    }
    //Wyświetlenie tabeli Produkty

    public ObservableList<DaneProdukt> daneProdukties;

    public void zaladujProdukt() throws SQLException {
        this.daneProdukties = FXCollections.observableArrayList();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM produkt");
        while(rs.next()){
            this.daneProdukties.add(new DaneProdukt(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
        }
    }
    //Wyświetlenie tabeli Package.Magazyn

    public ObservableList<DaneMagazyn> daneMagazyns;

    public void zaladujMagazyn() throws SQLException {
        this.daneMagazyns = FXCollections.observableArrayList();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM magazyn");
        while(rs.next()){
            this.daneMagazyns.add(new DaneMagazyn(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
        }
    }

    //wyświetlenie tabeli Klienci
    public ObservableList<DaneKlient> daneKlients;

    public void zaladujKlient() throws SQLException {
        this.daneKlients = FXCollections.observableArrayList();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM klient");
        while (rs.next()){
            this.daneKlients.add(new DaneKlient(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
    }

    public void usunDanePracownika(String row) throws SQLException {
        String usun = "DELETE FROM pracownik WHERE id_pracownika=?";
        PreparedStatement stmt = conn.prepareStatement(usun);
        stmt.setString(1,row);
        stmt.executeUpdate();
        System.out.println("Usunięto");
    }

    public void usunDaneKlienta(String row) throws SQLException {
    String usun = "DELETE FROM klient WHERE id_klienta=?";
    PreparedStatement stmt = conn.prepareStatement(usun);
    stmt.setString(1,row);
    stmt.executeUpdate();
    System.out.println("Usunięto");
    }

    public void usunDaneProduktu(String row) throws SQLException {
        String usun = "DELETE FROM produkt WHERE id_produktu=?";
        PreparedStatement stmt = conn.prepareStatement(usun);
        stmt.setString(1,row);
        stmt.executeUpdate();
        System.out.println("Usunięto");
    }

    public void usunDaneMagazynu(String row) throws SQLException {
        String usun = "DELETE FROM magazyn WHERE id_pr_w_mag=?";
        PreparedStatement stmt = conn.prepareStatement(usun);
        stmt.setString(1,row);
        stmt.executeUpdate();
        System.out.println("Usunięto");
    }
}
