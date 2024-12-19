package fr.pantheonsorbonne.miage;

import java.util.List;

public interface Strategie {

    String choisirMot(List<Tuile>chevalet, Dictionnaire dictionnaire);
    
    int[] choisirPosition(Plateau plateau, String mot);
}
