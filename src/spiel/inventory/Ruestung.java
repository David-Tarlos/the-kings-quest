package spiel.inventory;

public class Ruestung extends Item {
    private int schutz;

    public Ruestung(String name, String beschreibung, int schutz) {
        super(name, beschreibung);
        this.schutz = schutz;
    }

    public int getSchutz() {
        return schutz;
    }

    @Override
    public void benutzen(Character spieler) {

    }
}


