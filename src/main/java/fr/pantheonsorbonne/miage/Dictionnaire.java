package fr.pantheonsorbonne.miage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Dictionnaire {
    private HashSet<String> mots;

    public Dictionnaire(Langue langue) {
        mots = new HashSet<>();
        String cheminFichier = getCheminFichier(langue);
        chargerDictionnaire(cheminFichier);
    }

    private void chargerDictionnaire(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                mots.add(ligne.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du dictionnaire : " + e.getMessage());
        }
    }

    private String getCheminFichier(Langue langue) {
        switch (langue) {
            case FRANÇAIS:
                return "src/main/java/fr/pantheonsorbonne/miage/dictonnaire/francais.txt";
            case ANGLAIS:
                return "src/main/java/fr/pantheonsorbonne/miage/dictonnaire/francais.txt";
            case ESPAGNOL:
                return "src/main/java/fr/pantheonsorbonne/miage/dictonnaire/francais.txt";
            default:
                throw new IllegalArgumentException("Langue non prise en charge ");
        }
    }

    public void changerDictionnaire(Langue langue) {
        String cheminFichier = getCheminFichier(langue);
        mots.clear();
        chargerDictionnaire(cheminFichier);
    }

    public boolean estMotValide(String mot) {
        
        return mots.contains(mot.trim().toLowerCase()); 
    }
  
}
