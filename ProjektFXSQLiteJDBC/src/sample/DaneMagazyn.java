package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DaneMagazyn {
    private final StringProperty id;
    private final StringProperty nazwa;
    private final StringProperty producent;
    private final StringProperty ilosc;

    public DaneMagazyn(String id, String nazwa, String producent, String ilosc) {
        this.id = new SimpleStringProperty(id);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.producent = new SimpleStringProperty(producent);
        this.ilosc = new SimpleStringProperty(ilosc);
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

    public String getIlosc() {
        return ilosc.get();
    }

    public StringProperty iloscProperty() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc.set(ilosc);
    }
}
