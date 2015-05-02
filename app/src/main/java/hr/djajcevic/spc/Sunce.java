package hr.djajcevic.spc;

import java.util.Date;

public class Sunce {

    private Date izlazak;
    private Date zalazak;
    private Double azimut;
    private Double latituda;

    public Sunce() {
    }

    public Date getIzlazak() {
        return izlazak;
    }

    public void setIzlazak(Date izlazak) {
        this.izlazak = izlazak;
    }

    public Date getZalazak() {
        return zalazak;
    }

    public void setZalazak(Date zalazak) {
        this.zalazak = zalazak;
    }

    public Double getAzimut() {
        return azimut;
    }

    public void setAzimut(Double azimut) {
        this.azimut = azimut;
    }

    public Double getLatituda() {
        return latituda;
    }

    public void setLatituda(Double latituda) {
        this.latituda = latituda;
    }
}


