package fr.pantheonsorbonne.miage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategieIntelligente implements Strategie {

    private Random random = new Random();
    private Plateau plateau;
    public StrategieIntelligente(Plateau plateau){
        this.plateau = plateau;
    }
    @Override
    public String choisirMot(List<Tuile> chevalet, Dictionnaire dictionnaire) {
        if (chevalet.isEmpty()) {
            return null;
        }

        int meilleureValeur = 0;
        String meilleurMot = null;

        
        for (int tailleMot = 1; tailleMot <= chevalet.size(); tailleMot++) {
            for (String mot : generateCombinations(chevalet, tailleMot)) {
                
                if (dictionnaire.estMotValide(mot)) {
                    int score = calculerScoreMot(mot, dictionnaire);
                  
                    if (score > meilleureValeur) {
                        meilleureValeur = score;
                        meilleurMot = mot;
                    }
                }
            }
        }

        return meilleurMot; 
    }

    private List<String> generateCombinations(List<Tuile> chevalet, int tailleMot) {
        List<String> combinations = new ArrayList<>();
        generateCombinationsRec(chevalet, tailleMot, 0, new StringBuilder(), combinations);
        return combinations;
    }

    private void generateCombinationsRec(List<Tuile> chevalet, int tailleMot, int index, StringBuilder current, List<String> result) {
        if (current.length() == tailleMot) {
            result.add(current.toString());
            return;
        }
        for (int i = index; i < chevalet.size(); i++) {
            current.append(chevalet.get(i).getLettre());
            generateCombinationsRec(chevalet, tailleMot, i + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    private int calculerScoreMot(String mot, Dictionnaire dictionnaire) {
        int score = 0;
        char[] lettres = mot.toCharArray();
        int x = 0; 
        int y = 0;
    
        for (char c : lettres) {
            int valeurLettre = plateau.obtenirValeurLettre(c); 
            score += valeurLettre;
        }
    
        for (int i = 0; i < lettres.length; i++) {
            char c = lettres[i];
            Case currentCase = plateau.getCaseAtPosition(x, y + i); 
            score *= currentCase.appliquerBonusMot(); 
        }
        return score;
    }

    @Override
public int[] choisirPosition(Plateau plateau, String mot) {
    if (plateau.estPremierTour()) {
        
        int centerX = 7, centerY = 7;
        boolean horizontal = random.nextBoolean();
        if (horizontal) {
            int startY = Math.max(0, centerY - (mot.length() / 2));
            return new int[]{centerX, startY, 1}; 
        } else {
            int startX = Math.max(0, centerX - (mot.length() / 2));
            return new int[]{startX, centerY, 0}; 
        }
    }

    
    int x = random.nextInt(15);
    int y = random.nextInt(15);
    boolean horizontal = random.nextBoolean();
    return new int[]{x, y, horizontal ? 1 : 0};
}



   
}
