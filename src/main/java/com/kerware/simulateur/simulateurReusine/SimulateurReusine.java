package com.kerware.simulateur.simulateurReusine;

import java.util.ArrayList;

import com.kerware.simulateur.ICalculateurImpot;
import com.kerware.simulateur.SituationFamiliale;

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
    	
        calculAbatement();
    	calculParts();
    	calculImpotDeclarantAvantDecote();
    	calculImpotTotalAvantDecote();
    	calculPlafond();
    	calculDecote();
    	montantImpot = Math.round(montantImpot) - decote;
    	System.out.println("\n-----\n");
    }

    

    

    
    private void calculImpotTotalAvantDecote() {
        revenuImposable =  revenuFiscalReference / nbParts;
        montantImpot = 0;
        
        int i = 0;
        do {
            if ( revenuImposable >= PALIER_IMPOT[i] && revenuImposable < PALIER_IMPOT[i+1] ) {
            	montantImpot += ( revenuImposable - PALIER_IMPOT[i] ) * TAUX_IMPOT[i];
                break;
            } else {
            	montantImpot += ( PALIER_IMPOT[i+1] - PALIER_IMPOT[i] ) * TAUX_IMPOT[i];
            }
            i++;
        } while( i < 5);

        montantImpot = montantImpot * nbParts;
        montantImpot = Math.round( montantImpot );
        System.out.print("Impot total avant decote : " + montantImpot);
    }
    
    private void calculPlafond() {
        // baisse impot
        double baisseImpot = montantImpotDeclarant - montantImpot;
        System.out.print("Baisse d'impot avant plafond : " + baisseImpot);
        
        // dépassement plafond
        double ecartParts = nbParts - nbPartsDeclarant;

        double plafond = (ecartParts / 0.5) * PLAFOND_POUR_DEMI_PART;
        System.out.print("Plafond : " + plafond);
        
        if ( baisseImpot >= plafond ) {
        	System.out.print("Baisse d'impot supérieur au plafond, le plafond est appliqué ");
        	montantImpot = montantImpotDeclarant - plafond;
        }
        
        //La baisse d'impot n'est jamais appliquer si elle ne depasse pas le plafond, étant coder ainsi dans le simulateur originelle, je ne l'ai pas changer pour gharder les test cohérent.
        
    }
    
    private void calculDecote() {
        decote = 0;
        if ( nbPartsDeclarant == 1 ) {
            if ( montantImpot < SEUIL_DECOTE_DECLARANT_SEUL ) {
                 decote = DECOTE_MAX_DECLARANT_SEUL - ( montantImpot  * TAUX_DECOTE );
            }
        }
        
        if (  nbPartsDeclarant == 2 ) {
            if ( montantImpot < SEUIL_DECOTE_DECLARANT_COUPLE ) {
                 decote =  DECOTE_MAX_DECLARANT_COUPLE - ( montantImpot  * TAUX_DECOTE  );
         
            }
        }
        
        decote = Math.round( decote );
        if ( montantImpot <= decote ) {
            decote = montantImpot;
        }
        System.out.print("Decote : " + decote);
    }
    
}
