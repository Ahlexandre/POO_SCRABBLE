package fr.pantheonsorbonne.miage;

public class Case {
    public static final String NORMALE = "Normale";
    public static final String CHANGEMENT_LANGUE = "ChangementLangue";
    public static final String DOUBLE_LETTRE = "DoubleLettre";
    public static final String TRIPLE_LETTRE = "TripleLettre";
    public static final String DOUBLE_MOT = "DoubleMot";
    public static final String TRIPLE_MOT = "TripleMot";

    private String typeSpecial;
    private Character lettre; 
    private boolean bonusActif; 

    public Case(String typeSpecial) {
        this.typeSpecial = typeSpecial;
        this.lettre = null; 
        this.bonusActif = true; 
    }

    public String getTypeSpecial() {
        return typeSpecial;
    }

    public void setLettre(Character lettre) {
        this.lettre = lettre;
    }

    public Character getLettre() {
        return lettre;
    }

    public boolean estVide() {
        return lettre == null;
    }

    public int appliquerBonusLettre(int valeurLettre) {
        if (!bonusActif) return valeurLettre;
        if (DOUBLE_LETTRE.equals(typeSpecial)) return valeurLettre * 2;
        if (TRIPLE_LETTRE.equals(typeSpecial)) return valeurLettre * 3;
        return valeurLettre; 
    }

    public int appliquerBonusMot() {
        if (!bonusActif) return 1; 
        if (DOUBLE_MOT.equals(typeSpecial)) return 2;
        if (TRIPLE_MOT.equals(typeSpecial)) return 3;
        return 1; 
    }

    public void desactiverBonus() {
        this.bonusActif = false;
    }
}
