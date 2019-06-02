package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DaneProdukt {

    private final StringProperty id;
    private final StringProperty nazwa;
    private final StringProperty producent;
    private final StringProperty kwota;

    public DaneProdukt (String id, String nazwa, String producent, String kwota) {
        this.id = new SimpleStringProperty(id);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.producent = new SimpleStringProperty(producent);
        this.kwota = new SimpleStringProperty(kwota);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public String getProducent() {
        return producent.get();
    }

    public StringProperty producentProperty() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent.set(producent);
    }

    public String getKwota() {
        return kwota.get();
    }

    public StringProperty kwotaProperty() {
        return kwota;
    }

    public void setKwota(String kwota) {
        this.kwota.set(kwota);
    }
}
