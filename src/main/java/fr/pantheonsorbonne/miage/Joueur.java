package fr.pantheonsorbonne.miage;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String nom;
    private int score;
    private List<Tuile> chevalet;
    private static final int TAILLE_CHEVALET = 7;
    
    private Plateau plateau;
    private Strategie strategie;

    public Joueur(String nom,Strategie strategie, Plateau plateau) {
        this.nom = nom;
        this.score = 0;
        this.strategie  = strategie;
        this.chevalet = new ArrayList<>();
        this.plateau = plateau;
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public void ajouterScore(int points) {
        this.score += points;
    }

    public String getChevalet() {
        StringBuilder chevaletString = new StringBuilder();
        for (Tuile tuile : chevalet) {
            chevaletString.append(tuile).append(" "); 
        }
        return chevaletString.toString().trim();
    }

    public String jouerTour(Dictionnaire dictionnaire,Pioche pioche) {
        
    
        String mot = strategie.choisirMot(chevalet, dictionnaire);
        if (mot == null || mot.isEmpty() ) {
            System.out.println(nom + " ne peut pas jouer car aucun mot valide n'a été trouvé.");
            return null;
        }
    
        int[] position = strategie.choisirPosition(plateau, mot); 
        boolean horizontal = position[2] == 1;
    
        if (!plateau.placerMot(mot, position[0], position[1], horizontal)) {
           
            return null;
        }
    
        int points = plateau.calculerScore(mot, position[0], position[1], horizontal);
        ajouterScore(points);
        remplirChevalet(pioche,mot); 
        return mot;
    }
    
    
    
    

    public void remplirChevalet(Pioche pioche, String motUtilise) {
       
        for (char lettre : motUtilise.toCharArray()) {
           
            for (int i = 0; i < chevalet.size(); i++) {
                if (chevalet.get(i).getLettre() == lettre) {
                    chevalet.remove(i);
                    break;
                }
            }
        }
        
        
        while (chevalet.size() < TAILLE_CHEVALET){
            Tuile tuile = pioche.tirerTuile(); 
            if (tuile != null) {
                chevalet.add(tuile); 
            }
        }
    }
    
    
    
}
