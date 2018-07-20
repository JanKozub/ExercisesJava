package ObiektyiPakiety.Zadanie2;

public class Prostokat {
    private double wysokosc;
    private double szerokosc;

    public double getWysokosc() {
        return wysokosc;
    }

    public void setWysokosc(double wysokosc) {
        this.wysokosc = wysokosc;
    }

    public double getSzerokosc() {
        return szerokosc;
    }

    public void setSzerokosc(double szerokosc) {
        this.szerokosc = szerokosc;
    }

    public Prostokat(double wysokosc, double szerokosc) {
        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
    }

}
