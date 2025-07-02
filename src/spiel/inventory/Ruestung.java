package spiel.inventory;

public class Ruestung extends Item {
    private int schutz;

    public Ruestung(String name, String beschreibung, int schutz, boolean isequiped) {
        super(name, beschreibung, isequiped);
        this.schutz = schutz;
    }

    public int getSchutz() {
        return schutz;
    }

    public void setSchutz(int schutz) {
        this.schutz = schutz;
    }

    @Override
    public void benutzen(Character spieler) {

    }
}


