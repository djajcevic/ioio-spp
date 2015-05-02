package hr.djajcevic.spc;


public class ApsolutniEnkoder extends Enkoder {

    public ApsolutniEnkoder(int rezolucija) {
        setRezolucija(rezolucija);
    }

    @Override
    public int pomak(int korak) {
        setPozicija(korak * getRezolucija());
        return getPozicija();
    }
}

