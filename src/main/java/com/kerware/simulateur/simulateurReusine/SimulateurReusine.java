package com.kerware.simulateur.simulateurReusine;

import java.util.ArrayList;

import com.kerware.simulateur.ICalculateurImpot;
import com.kerware.simulateur.SituationFamiliale;
import com.kerware.simulateur.simulateurReusine.Calculateur.CalculateurAbatement;
import com.kerware.simulateur.simulateurReusine.Calculateur.CalculateurDecote;
import com.kerware.simulateur.simulateurReusine.Calculateur.CalculateurImpotAvantDecote;
import com.kerware.simulateur.simulateurReusine.Calculateur.CalculateurImpotDeclarant;
import com.kerware.simulateur.simulateurReusine.Calculateur.CalculateurParts;
import com.kerware.simulateur.simulateurReusine.Calculateur.CalculateurPartsDeclarant;
import com.kerware.simulateur.simulateurReusine.Calculateur.CalculateurPlafond;

public class SimulateurReusine implements ICalculateurImpot{
	
    //Paramètre Client
    private int revenuNet = 0;
    private int nbEnfantTotal = 0;
    private int nbEnfantHandicape = 0;
    private boolean isParentIsole = false;
    private SituationFamiliale situationFamiliale = SituationFamiliale.CELIBATAIRE;
    
    //Donnée Client 
    
    private double revenuFiscalReference = 0;
    private double revenuImposable = 0;

    private double abatement = 0;

    private double nbPartsDeclarant = 0;
    private double nbParts = 0;
    private double decote = 0;

    private double montantImpotDeclarant = 0;
    private double montantImpot = 0;

    
    //Fonction de Paramètrage
    @Override
    public void setRevenusNet(int revenuNet) {
    	this.revenuNet = revenuNet;
    }
    
    @Override
    public void setSituationFamiliale(SituationFamiliale situationFamiliale) {
    	this.situationFamiliale = situationFamiliale;
    }

    @Override
    public void setNbEnfantsACharge(int nbEnfant) {
    	this.nbEnfantTotal = nbEnfant;
    }

    @Override
    public void setNbEnfantsSituationHandicap(int nbEnfantHandicape) {
    	this.nbEnfantHandicape = nbEnfantHandicape;
    }

    @Override
    public void setParentIsole(boolean isParentIsole) {
    	this.isParentIsole = isParentIsole;
    }
    
   
    //Fonction d'acquisition d'information
    @Override
    public int getRevenuFiscalReference() {
        return (int) Math.round(revenuFiscalReference);   
    }

    @Override
    public int getAbattement() {
        return (int) Math.round(abatement);
    }

    @Override
    public double getNbPartsFoyerFiscal() {
        return nbParts;
    }

    @Override
    public int getImpotAvantDecote() {
        return getImpotSurRevenuNet() + getDecote();
    }

    @Override
    public int getDecote() {
        return (int) Math.round(decote);
    }

    @Override
    public int getImpotSurRevenuNet() {
        return (int) Math.round(montantImpot);
    }
          
    
    //Fonction de calcul    
    @Override
    public void calculImpotSurRevenuNet() {
        System.out.println("Paramètre de calcul : \n"
        		+ "Revenu Net : " + revenuNet + "\n" + "Enfant Total : " + nbEnfantTotal + "\n" 
        		+ "Enfant Handicapé : " + nbEnfantHandicape + "\n" + "Est un parent isolé : " + isParentIsole + "\n"
        		+ "Situation familiale : " + situationFamiliale + "\n");
    	
        abatement = CalculateurAbatement.calcul(revenuNet);
        revenuFiscalReference = revenuNet - abatement;

        nbPartsDeclarant = CalculateurPartsDeclarant.calcul(situationFamiliale);
        nbParts = CalculateurParts.calcul(nbPartsDeclarant, nbEnfantTotal, nbEnfantHandicape, isParentIsole);
        
        montantImpotDeclarant = CalculateurImpotDeclarant.calcul(revenuFiscalReference, nbPartsDeclarant);
        montantImpot = CalculateurImpotAvantDecote.calcul(revenuFiscalReference, nbParts);
    	
        montantImpot = CalculateurPlafond.calcul(montantImpotDeclarant, montantImpot, nbParts, nbPartsDeclarant);
        decote = CalculateurDecote.calcul(montantImpot, nbPartsDeclarant);
    	
    	montantImpot = Math.round(montantImpot) - decote;
    	System.out.println("\n-----\n");
    }


    

    
}
