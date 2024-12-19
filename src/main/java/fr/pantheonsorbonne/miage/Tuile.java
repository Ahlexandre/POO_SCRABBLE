package fr.pantheonsorbonne.miage;

public class Tuile {
    private char lettre;
    private int valeur;

    public Tuile(char lettre, int valeur) {
        this.lettre = lettre;
        this.valeur = valeur;
    }

    public boolean estJoker() {
        return lettre == ' ';
    }
    
    public char getLettre() {
        return lettre;
    }

    public int getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        if (estJoker()) {
            return "JOKER";
        }
        return lettre + " (" + valeur + ")";
    }
}
