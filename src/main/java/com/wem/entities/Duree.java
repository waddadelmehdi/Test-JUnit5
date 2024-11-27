package com.wem.entities;

public class Duree {
    private int heures;
    private int minutes;
    private int secondes;

    // Constructeur sans arguments
    public Duree() {
        this.setHeures(0);
        this.setMinutes(0);
        this.setSecondes(0);
    }

    // Constructeur avec arguments
    public Duree(int heures, int minutes, int secondes) {
        this.setHeures(heures);
        this.setMinutes(minutes);
        this.setSecondes(secondes);
    }

    // Getters et Setters avec validation
    public int getHeures() {
        return heures;
    }

    public void setHeures(int heures) {
        if (heures >= 0) {
            this.heures = heures;
        } else {
            throw new IllegalArgumentException("Les heures doivent être positives.");
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        if (minutes >= 0 && minutes < 60) {
            this.minutes = minutes;
        } else {
            throw new IllegalArgumentException("Les minutes doivent être entre 0 et 59.");
        }
    }

    public int getSecondes() {
        return secondes;
    }

    public void setSecondes(int secondes) {
        if (secondes >= 0 && secondes < 60) {
            this.secondes = secondes;
        } else {
            throw new IllegalArgumentException("Les secondes doivent être entre 0 et 59.");
        }
    }

    // Formatte la durée en hh:mm:ss
    public String formatDuree() {
        return String.format("%02d:%02d:%02d", getHeures(), getMinutes(), getSecondes());
    }

    // Affiche la durée
    public void afficherDuree() {
        System.out.println(formatDuree());
    }

    // Compare deux durées : -1 si this < autre, 0 si égales, 1 si this > autre
    public int comparer(Duree autre) {
        if (this.getHeures() != autre.getHeures()) {
            return Integer.compare(this.getHeures(), autre.getHeures());
        } else if (this.getMinutes() != autre.getMinutes()) {
            return Integer.compare(this.getMinutes(), autre.getMinutes());
        } else {
            return Integer.compare(this.getSecondes(), autre.getSecondes());
        }
    }

    // Ajoute une seconde à la durée
    public void nextSeconde() {
        this.setSecondes(this.getSecondes() + 1);
        if (this.getSecondes() >= 60) {
            this.setSecondes(0);
            this.nextMinute();
        }
    }

    // Ajoute une minute à la durée
    public void nextMinute() {
        this.setMinutes(this.getMinutes() + 1);
        if (this.getMinutes() >= 60) {
            this.setMinutes(0);
            this.setHeures(this.getHeures() + 1);
        }
    }

    // Vérifie si deux durées sont égales
    public boolean isEquals(Duree autre) {
        return this.getHeures() == autre.getHeures() &&
                this.getMinutes() == autre.getMinutes() &&
                this.getSecondes() == autre.getSecondes();
    }
}
