package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<DaneMagazyn> tableMagazyn;
    @FXML
    private TableColumn colMagazynId;
    @FXML
    private TableColumn colMagazynNazwa;
    @FXML
    private TableColumn colMagazynProducent;
    @FXML
    private TableColumn colMagazynIlosc;
    @FXML
    private TableColumn colIKlientId;
    @FXML
    private TableView<DaneProdukt> tableProdukt;
    @FXML
    private TableColumn colProduktId;
    @FXML
    private TableColumn colProduktNazwa;
    @FXML
    private TableColumn colProduktProducent;
    @FXML
    private TableColumn colProduktKwota;
    @FXML
    private TableView<DaneKlient> tableKlient;
    @FXML
    private TableColumn colIKlientImie;
    @FXML
    private TableColumn colKlientNazwisko;
    @FXML
    private TableColumn colKlientPesel;
    @FXML
    private TableColumn colKlientMail;
    @FXML
    private TableColumn colPracownikId;
    @FXML
    private TableColumn colPracownikImie;
    @FXML
    private TableColumn colPracownikNazwisko;
    @FXML
    private TableColumn colPracownikPesel;
    @FXML
    private TableColumn colPracownikTelefon;
    @FXML
    private TableView<DanePracownik> tablePracownik;
    @FXML
    private TextField klientImie;
    @FXML
    private TextField klientNazwisko;
    @FXML
    private TextField klientPesel;
    @FXML
    private TextField klientMail;
    @FXML
    private TextField pracownikImie;
    @FXML
    private TextField pracowniknazwisko;
    @FXML
    private TextField pracownikPesel;
    @FXML
    private TextField pracownikTelefon;
    @FXML
    private TextField produktNazwa;
    @FXML
    private TextField produktProducent;
    @FXML
    private TextField produktKwota;
    @FXML
    private TextField magazynNazwa;
    @FXML
    private TextField magazynProducent;
    @FXML
    private TextField magazynIlosc;

    private Baza b = new Baza();

    public void dodajdoMagazynu(ActionEvent actionEvent) {
        String magnazwa =  magazynNazwa.getText();
        String magproducent = magazynProducent.getText();
        String Iloscwmag =  magazynIlosc.getText();
        int  magilosc = Integer.parseInt(Iloscwmag);
        b.dodajMagazyn(magnazwa,magproducent,magilosc);
        magazynNazwa.clear();
        magazynProducent.clear();
        magazynIlosc.clear();
    }

    public void dodajdoProduktu(ActionEvent actionEvent) {
        String prnazwa =  produktNazwa.getText();
        String prproducent =  produktProducent.getText();
        String  prkwota =  produktKwota.getText();
        int kwota = Integer.parseInt(prkwota);
        b.dodajProdukt(prnazwa,prproducent,kwota);
        produktNazwa.clear();
        produktProducent.clear();
        produktKwota.clear();
    }

    public void dodajPracownika(ActionEvent actionEvent) {
        String prImie =  pracownikImie.getText();
        String prNazwisko = pracowniknazwisko.getText();
        String prPesel = pracownikPesel.getText();
        String telefonpr = pracownikTelefon.getText();
        int prtelefon = Integer.parseInt(telefonpr);
        b.dodajPracownika(prImie,prNazwisko,prPesel,prtelefon);
        pracownikImie.clear();
        pracowniknazwisko.clear();
        pracownikPesel.clear();
        pracownikTelefon.clear();
    }

    public void dodajKlienta(ActionEvent actionEvent) {
        String klImie = klientImie.getText();
        String klNazwisko = klientNazwisko.getText();
        String klPesel = klientPesel.getText();
        String klMail = klientMail.getText();
        b.dodajKlient(klImie,klNazwisko,klPesel,klMail);
        klientImie.clear();
        klientNazwisko.clear();
        klientPesel.clear();
        klientMail.clear();
    }

    public void stworzPracownik(ActionEvent actionEvent) throws SQLException {
        b.createPracownicy();
    }

    public void stworzProdukt(ActionEvent actionEvent) throws SQLException {
        b.createProdukty();
    }

    public void stworzKlient(ActionEvent actionEvent) throws SQLException {
        b.createKlient();
    }

    public void stworzMagazyn(ActionEvent actionEvent) throws SQLException {
        b.createMagazyny();
    }

    public void usunProdukt(ActionEvent actionEvent) throws SQLException {
        b.usunProdukty();
    }

    public void usunKlient(ActionEvent actionEvent) throws SQLException {
        b.usunKlienci();
    }

    public void usunMagazyn(ActionEvent actionEvent) throws SQLException {
        b.usunMagazyny();
    }

    public void usunPracownik(ActionEvent actionEvent) throws SQLException {
        b.usunPracownicy();
    }
    public void modtablepracownik() throws SQLException {
        b.zaladujPracownik();
        this.colPracownikId.setCellValueFactory(new PropertyValueFactory("Id"));
        this.colPracownikImie.setCellValueFactory(new PropertyValueFactory("Imie"));
        this.colPracownikNazwisko.setCellValueFactory(new PropertyValueFactory("Nazwisko"));
        this.colPracownikPesel.setCellValueFactory(new PropertyValueFactory("Pesel"));
        this.colPracownikTelefon.setCellValueFactory(new PropertyValueFactory("Telefon"));

        this.tablePracownik.setItems(null);
        this.tablePracownik.setItems(b.danePracowniks);
    }
    public void initialize(URL url, ResourceBundle rb){
        try {
            modtablepracownik();
            modtableklient();
            modtableprodukt();
            modtablemagazyn();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modtableklient() throws SQLException{
        b.zaladujKlient();
        this.colIKlientId.setCellValueFactory(new PropertyValueFactory("Id"));
        this.colIKlientImie.setCellValueFactory(new PropertyValueFactory("Imie"));
        this.colKlientNazwisko.setCellValueFactory(new PropertyValueFactory("Nazwisko"));
        this.colKlientPesel.setCellValueFactory(new PropertyValueFactory("Pesel"));
        this.colKlientMail.setCellValueFactory(new PropertyValueFactory("Mail"));

        this.tableKlient.setItems(null);
        this.tableKlient.setItems(b.daneKlients);
    }

    private void modtableprodukt() throws SQLException {
        b.zaladujProdukt();
        this.colProduktId.setCellValueFactory(new PropertyValueFactory("Id"));
        this.colProduktNazwa.setCellValueFactory(new PropertyValueFactory("Nazwa"));
        this.colProduktProducent.setCellValueFactory(new PropertyValueFactory("Producent"));
        this.colProduktKwota.setCellValueFactory(new PropertyValueFactory("Kwota"));

        this.tableProdukt.setItems(null);
        this.tableProdukt.setItems(b.daneProdukties);
    }

    private void modtablemagazyn() throws SQLException {
        b.zaladujMagazyn();
        this.colMagazynId.setCellValueFactory(new PropertyValueFactory("Id"));
        this.colMagazynNazwa.setCellValueFactory(new PropertyValueFactory("Nazwa"));
        this.colMagazynProducent.setCellValueFactory(new PropertyValueFactory("Producent"));
        this.colMagazynIlosc.setCellValueFactory(new PropertyValueFactory("Ilosc"));

        this.tableMagazyn.setItems(null);
        this.tableMagazyn.setItems(b.daneMagazyns);
    }


    public void odswiezPracownik(ActionEvent actionEvent) throws SQLException {
        modtablepracownik();
    }

    public void odswiezKlient(ActionEvent actionEvent) throws SQLException {
        modtableklient();
    }

    public void odswiezProdukt(ActionEvent actionEvent) throws SQLException {
        modtableprodukt();
    }

    public void odswiezMagazyn(ActionEvent actionEvent) throws SQLException {
        modtablemagazyn();
    }

    @FXML
    private void usunbuttPracownik(ActionEvent actionEvent) throws SQLException {
        ObservableList<DanePracownik> wszyscyPracownicy, tenPracownik;
        wszyscyPracownicy = tablePracownik.getItems();
        tenPracownik = tablePracownik.getSelectionModel().getSelectedItems();
        String row = tablePracownik.getSelectionModel().getSelectedItem().getId();
        System.out.println(row);
        b.usunDanePracownika(row);
        tenPracownik.forEach(wszyscyPracownicy::remove);
    }

    @FXML
    private void usunbuttKlient(ActionEvent actionEvent) throws SQLException {
        ObservableList<DaneKlient> wszyscyKlienci, tenKlient;
        wszyscyKlienci = tableKlient.getItems();
        tenKlient = tableKlient.getSelectionModel().getSelectedItems();
        String row = tableKlient.getSelectionModel().getSelectedItem().getId();
        tenKlient.forEach(wszyscyKlienci::remove);
        System.out.println(row);
        b.usunDaneKlienta(row);
    }

    @FXML
    private void usunbuttProdukt(ActionEvent actionEvent) throws SQLException {
        ObservableList<DaneProdukt> wszystkieProdukty, tenProdukt;
        wszystkieProdukty = tableProdukt.getItems();
        tenProdukt = tableProdukt.getSelectionModel().getSelectedItems();
        String row  = tableProdukt.getSelectionModel().getSelectedItem().getId();
        tenProdukt.forEach(wszystkieProdukty::remove);
        System.out.println(row);
        b.usunDaneProduktu(row);
    }

    @FXML
    private void usunbuttMagazyn(ActionEvent actionEvent) throws SQLException {
        ObservableList<DaneMagazyn> wszystkieMagazyny, tenMagazyn;
        wszystkieMagazyny = tableMagazyn.getItems();
        tenMagazyn = tableMagazyn.getSelectionModel().getSelectedItems();
        String row = tableMagazyn.getSelectionModel().getSelectedItem().getId();
        tenMagazyn.forEach(wszystkieMagazyny::remove);
        System.out.println(row);
        b.usunDaneMagazynu(row);
    }

}
