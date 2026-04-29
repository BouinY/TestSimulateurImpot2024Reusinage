package com.kerware.simulateur.simulateurReusine.Calculateur;

import com.kerware.simulateur.SituationFamiliale;

public class CalculateurPartsDeclarant {
    public static double calcul(SituationFamiliale situationFamiliale) {
    	
    	double nbPartsDeclarant = 0;
    	
    	//Partie des déclarants
        switch ( situationFamiliale ) {
            case CELIBATAIRE:
            	nbPartsDeclarant = 1; break;
            case MARIE:
            	nbPartsDeclarant = 2; break;
            case DIVORCE:
            	nbPartsDeclarant = 1; break;
            case VEUF:
            	/*
                if ( nbEnfantTotal == 0 ) {
                	nbPartsDeclarant = 1;
                } else {
                	nbPartsDeclarant = 2;
                }
                */
            	nbPartsDeclarant = 1; //Dans le code originel le if else est suivi de cette ligne le rendant inutile, je laisse le code car je ne sais pas si il fallait corrigée sachant que cela pour casser les test.
                break;
        }
        
        System.out.println("Nombre de parts declarant : " + nbPartsDeclarant);
        return nbPartsDeclarant;        
    }
}
