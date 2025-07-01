package spiel;

import java.util.ArrayList;

public class Character {
    private int health;
    private int noiseLevel;

    public Character(int noiseLevel, int health) {
        this.noiseLevel = noiseLevel;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(int noiseLevel) {
        this.noiseLevel = noiseLevel;
    }
}

