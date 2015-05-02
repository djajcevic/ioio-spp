package hr.djajcevic.spc;


public abstract class Enkoder {

    private int rezolucija;
    private int pozicija;

    public abstract int pomak(int korak);

    public int getRezolucija() {
        return rezolucija;
    }

    public void setRezolucija(int rezolucija) {
        this.rezolucija = rezolucija;
    }

    public int getPozicija() {
        return pozicija;
    }

    public void setPozicija(int pozicija) {
        this.pozicija = pozicija;
    }
}
