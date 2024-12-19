package fr.pantheonsorbonne.miage;

public class Plateau {
    private Case[][] grille; 
    private static final int TAILLE = 15;

    public Plateau() {
        grille = new Case[TAILLE][TAILLE];
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        for (int x = 0; x < TAILLE; x++) {
            for (int y = 0; y < TAILLE; y++) {
                grille[x][y] = new Case(Case.NORMALE);
            }
        }
        
        int[][] doubleLettre = {
            {0, 3}, {0, 11}, {2, 6}, {2, 8}, {3, 0}, {3, 7}, {3, 14},
            {6, 2}, {6, 6}, {6, 8}, {6, 12}, {7, 3}, {7, 11}, {8, 2},
            {8, 6}, {8, 8}, {8, 12}, {11, 0}, {11, 7}, {11, 14},
            {12, 6}, {12, 8}, {14, 3}, {14, 11}
        };
    
        int[][] tripleLettre = {
            {1, 5}, {1, 9}, {5, 1}, {5, 13}, {9, 1}, {9, 13}, {13, 5}, {13, 9}
        };
    
        int[][] doubleMot = {
            {1, 1}, {2, 2}, {3, 3}, {4, 4}, {13, 13}, {12, 12}, {11, 11}, {10, 10},
            {1, 13}, {2, 12}, {3, 11}, {4, 10}, {13, 1}, {12, 2}, {11, 3}, {10, 4}
        };
    
        int[][] tripleMot = {
            {0, 0}, {0, 7}, {0, 14}, {7, 0}, {7, 14}, {14, 0}, {14, 7}, {14, 14}
        };
    
        int[][] changementLangue = {
            {5, 5}, {9, 9} 
        };
    
        for (int[] pos : doubleLettre) {
            grille[pos[0]][pos[1]] = new Case(Case.DOUBLE_LETTRE);
        }
        for (int[] pos : tripleLettre) {
            grille[pos[0]][pos[1]] = new Case(Case.TRIPLE_LETTRE);
        }
        for (int[] pos : doubleMot) {
            grille[pos[0]][pos[1]] = new Case(Case.DOUBLE_MOT);
        }
        for (int[] pos : tripleMot) {
            grille[pos[0]][pos[1]] = new Case(Case.TRIPLE_MOT);
        }
        for (int[] pos : changementLangue) {
            grille[pos[0]][pos[1]] = new Case(Case.CHANGEMENT_LANGUE);
        }
    }
    
    public Case[][] getGrille() {
        return grille;
    }


    public boolean verifierPlacementPossible(String mot, int x, int y, boolean horizontal) {
    
    if (estPremierTour() && !estMotCentre(mot, x, y, horizontal)) {
        System.out.println("Le mot doit toucher la case centrale lors du premier tour.");
        return false;
    }

    if (horizontal) {
            if (y + mot.length() > TAILLE) return false;
    
            for (int i = 0; i < mot.length(); i++) {
                Case cible = grille[x][y + i];
                if (!cible.estVide() && cible.getLettre() != mot.charAt(i)) {
                    return false;
                }
            }
        } else {
            if (x + mot.length() > TAILLE) return false;
    
            for (int i = 0; i < mot.length(); i++) {
                Case cible = grille[x + i][y];
                if (!cible.estVide() && cible.getLettre() != mot.charAt(i)) {
                    return false;
                }
            }
        }
        
        return true; 
    }

    protected boolean estPremierTour() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if (!grille[i][j].estVide()) {
                    return false; 
                }
            }
        }
        return true; 
    }
    
    private boolean estMotCentre(String mot, int x, int y, boolean horizontal) {
        int centerX = 7, centerY = 7; 
        if (horizontal) {
            return x == centerX && y <= centerY && (y + mot.length() - 1) >= centerY;
        } else {
            return y == centerY && x <= centerX && (x + mot.length() - 1) >= centerX;
        }
    }
    
    

    
    public boolean placerMot(String mot, int x, int y, boolean horizontal) {
        if (!verifierPlacementPossible(mot, x, y, horizontal)) return false;
    
        for (int i = 0; i < mot.length(); i++) {
            Case cible = horizontal ? grille[x][y + i] : grille[x + i][y];
            char lettre = mot.charAt(i);
    
            cible.setLettre(lettre);
    
            cible.desactiverBonus();
        }
        return true; 
    }
    

   

    public int calculerScore(String mot, int x, int y, boolean horizontal) {
        int score = 0;               
        int multiplicateurMot = 1;   
    
        for (int i = 0; i < mot.length(); i++) {
            Case cible = horizontal ? grille[x][y + i] : grille[x + i][y];
            char lettre = mot.charAt(i);
    
            int valeurLettre = obtenirValeurLettre(lettre);
    
            score += cible.appliquerBonusLettre(valeurLettre);
    
            multiplicateurMot *= cible.appliquerBonusMot();
        }
    
        return score * multiplicateurMot;
    }
    

    
    protected int obtenirValeurLettre(Character lettre) {
        switch (lettre) {
            case 'E': case 'A': case 'I': case 'N': case 'O': case 'R': case 'S': case 'T': case 'U': case 'L':
                return 1;
            case 'D': case 'G': case 'M':
                return 2;
            case 'B': case 'C': case 'P':
                return 3;
            case 'F': case 'H': case 'V':
                return 4;
            case 'J': case 'Q':
                return 8;
            case 'K': case 'W': case 'X': case 'Y': case 'Z':
                return 10;
            default:
                return 0;
        }
    }

    
     
    public void afficherPlateau() {
        for (int x = 0; x < TAILLE; x++) {
            for (int y = 0; y < TAILLE; y++) {
                Case currentCase = grille[x][y];
                char affichage;
    
                if (!currentCase.estVide()) {
                    affichage = currentCase.getLettre();
                } else {
                    switch (currentCase.getTypeSpecial()) {
                        case Case.DOUBLE_LETTRE:
                            affichage = 'd'; 
                            break;
                        case Case.TRIPLE_LETTRE:
                            affichage = 't';
                            break;
                        case Case.DOUBLE_MOT:
                            affichage = 'm';
                            break;
                        case Case.TRIPLE_MOT:
                            affichage = 'w';
                            break;
                        case Case.CHANGEMENT_LANGUE:
                            affichage = 'l'; 
                            break;
                        default:
                            affichage = '.'; 
                    }
                }
    
                System.out.print(affichage + " ");
            }
            System.out.println(); 
        }
    }
    
    public Case getCaseAtPosition(int x, int y) {
        return grille[x][y];
    }

    public boolean peutPlacerMot(String mot) {
       
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y <= 15 - mot.length(); y++) {
                if (verifierPlacementPossible(mot, x, y, true)) {
                    return true;
                }
            }
        }
    
        
        for (int x = 0; x <= 15 - mot.length(); x++) {
            for (int y = 0; y < 15; y++) {
                if (verifierPlacementPossible(mot, x, y, false)) {
                    return true; 
                }
            }
        }
    
        return false; 
    }
    
}