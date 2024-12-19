package fr.pantheonsorbonne.miage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pioche {
    private List<Tuile> tuiles;

    public Pioche() {
        tuiles = new ArrayList<>();
        initialiserPioche();
        melanger();
    }

    private void initialiserPioche() {
        
        ajouterTuiles('E', 1, 15);
        ajouterTuiles('A', 1, 9);
        ajouterTuiles('I', 1, 8);
        ajouterTuiles('N', 1, 6);
        ajouterTuiles('O', 1, 6);
        ajouterTuiles('R', 1, 6);
        ajouterTuiles('S', 1, 6);
        ajouterTuiles('T', 1, 6);
        ajouterTuiles('U', 1, 6);
        ajouterTuiles('L', 1, 5);
        ajouterTuiles('D', 2, 3);
        ajouterTuiles('G', 2, 2);
        ajouterTuiles('M', 2, 3);
        ajouterTuiles('B', 3, 2);
        ajouterTuiles('C', 3, 2);
        ajouterTuiles('P', 3, 2);
        ajouterTuiles('F', 4, 2);
        ajouterTuiles('H', 4, 2);
        ajouterTuiles('V', 4, 2);
        ajouterTuiles('J', 8, 1);
        ajouterTuiles('Q', 8, 1);
        ajouterTuiles('K', 10, 1);
        ajouterTuiles('W', 10, 1);
        ajouterTuiles('X', 10, 1);
        ajouterTuiles('Y', 10, 1);
        ajouterTuiles('Z', 10, 1);
        ajouterTuiles(' ', 0, 2);

        melanger();
    }

    protected void ajouterLettresLangue(Langue langue) {
        switch (langue) {
            case ANGLAIS:
                ajouterTuiles('J', 8, 1);
                ajouterTuiles('K', 10, 1);
                ajouterTuiles('W', 10, 1);
                ajouterTuiles('X', 10, 1);
                ajouterTuiles('Z', 10, 1);
                
                break;
    
            case ESPAGNOL:
                ajouterTuiles('Ñ', 8, 1);
                
                
                break;
    
            default:
               
        }
        melanger(); 
    }

    private void ajouterTuiles(char lettre, int valeur, int quantite) {
        for (int i = 0; i < quantite; i++) {
            tuiles.add(new Tuile(lettre, valeur));
        }
    }

    private void melanger() {
        Collections.shuffle(tuiles);
    }

    public Tuile tirerTuile() {
        if (tuiles.isEmpty()) {
            System.out.println("Pioche vide");
            return null; 
        }
        return tuiles.remove(tuiles.size() - 1); 
    }
    

    public boolean estVide() {
        return tuiles.isEmpty();
    }
}
