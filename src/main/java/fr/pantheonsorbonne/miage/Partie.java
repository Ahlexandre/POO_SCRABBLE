package fr.pantheonsorbonne.miage;

import java.util.Random;

public class Partie {
    private Plateau plateau;
    private Joueur joueur1;
    private Joueur joueur2;

   
    private Langue langueActuelle;
    private Pioche pioche;
    private Dictionnaire dictionnaire;


    public Partie() {
        plateau = new Plateau();
        pioche = new Pioche();
        dictionnaire = new Dictionnaire(Langue.FRANÇAIS);
        

        joueur1 = new Joueur("Joueur 1",new StrategieIntelligente(plateau),plateau);
        joueur2 = new Joueur("Joueur 2",new StrategieIntelligente(plateau),plateau);
        
        langueActuelle = Langue.FRANÇAIS;
        initialiserChevalets();
        
        
    }


    private void initialiserChevalets() {
        joueur1.remplirChevalet(pioche,"");
        joueur2.remplirChevalet(pioche,"");
       
    }

    public void lancer() {
        Dictionnaire dictionnaire = new Dictionnaire(langueActuelle);
       
    
        int tours = 0;
    
        while (tours < 50) { 
            System.out.println("\nTour " + (tours + 1));
            plateau.afficherPlateau();
          
            System.out.println("Avant le tour:");
            System.out.println("Chevalet de " + joueur1.getNom() + ": " + joueur1.getChevalet());
            System.out.println("Chevalet de " + joueur2.getNom() + ": " + joueur2.getChevalet());

            

            System.out.println();
            
            System.out.println("Apres le tour:");
            if (!tourDeJeu(joueur1, dictionnaire)) break;
            
            
            System.out.println("Chevalet de " + joueur1.getNom() + ": " + joueur1.getChevalet());

            if (!tourDeJeu(joueur2, dictionnaire)) break;
            
            System.out.println("Chevalet de " + joueur2.getNom() + ": " + joueur2.getChevalet());

            if (!tourDeJeu(joueur1, dictionnaire)) break;
            

 
            tours++;
        }
    
        determineGagnant();
    }

    private boolean tourDeJeu(Joueur joueur, Dictionnaire dictionnaire) {
        String mot = joueur.jouerTour(dictionnaire,pioche);
        if (mot == null) {
            System.out.println(joueur.getNom() + " ne peut pas jouer.");
            return false;
        }
        appliquerChangementLangue(mot, plateau);
        return true;
    }

 
        private void appliquerChangementLangue(String mot, Plateau plateau) {
            for (int x = 0; x < plateau.getGrille().length; x++) {
                for (int y = 0; y < plateau.getGrille()[x].length; y++) {
                    Case caseActuelle = plateau.getCaseAtPosition(x, y);
                    if (Case.CHANGEMENT_LANGUE.equals(caseActuelle.getTypeSpecial())) {
                        
                        if (langueActuelle == Langue.FRANÇAIS) {
                            langueActuelle = Langue.ANGLAIS;
                        } else if (langueActuelle == Langue.ANGLAIS) {
                            langueActuelle = Langue.ESPAGNOL;
                        } else {
                            langueActuelle = Langue.FRANÇAIS;
                        }
    
                        
                        dictionnaire.changerDictionnaire(langueActuelle);
                        pioche.ajouterLettresLangue(langueActuelle);
                    }
                }
            }
        }

    



    

    private void determineGagnant() {
        int scoreJoueur1 = joueur1.getScore();
        int scoreJoueur2 = joueur2.getScore();
    
        System.out.println("Score de Joueur 1: " + scoreJoueur1);
        System.out.println("Score de Joueur 2: " + scoreJoueur2);
    
        if (scoreJoueur1 > scoreJoueur2) {
            System.out.println("Le gagnant est Joueur 1 avec " + scoreJoueur1 + " points.");
        } else if (scoreJoueur2 > scoreJoueur1) {
            System.out.println("Le gagnant est Joueur 2 avec " + scoreJoueur2 + " points.");
        } else {
            System.out.println("Match nul avec un score de " + scoreJoueur1 + " points chacun !");
        }
    }
    
}