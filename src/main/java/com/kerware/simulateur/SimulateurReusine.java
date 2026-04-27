package com.kerware.simulateur;

import java.util.ArrayList;

public class SimulateurReusine implements ICalculateurImpot{
	
    private static final int PALIER_IMPOT_N00 = 0 ;
    private static final int PALIER_IMPOT_N01 = 11294;
    private static final int PALIER_IMPOT_N02 = 28797;
    private static final int PALIER_IMPOT_N03 = 82341;
    private static final int PALIER_IMPOT_N04 = 177106;
    private static final int PALIER_IMPOT_N05 = Integer.MAX_VALUE;

    private static final int[] PALIER_IMPOT = {
    	    PALIER_IMPOT_N00, 
    	    PALIER_IMPOT_N01, 
    	    PALIER_IMPOT_N02, 
    	    PALIER_IMPOT_N03, 
    	    PALIER_IMPOT_N04, 
    	    PALIER_IMPOT_N05
    	};

    
    private static final double TAUX_IMPOT_ENTRE_N00_ET_N01 = 0.0;
    private static final double TAUX_IMPOT_ENTRE_N01_ET_N02 = 0.11;
    private static final double TAUX_IMPOT_ENTRE_N02_ET_N03 = 0.3;
    private static final double TAUX_IMPOT_ENTRE_N03_ET_N04 = 0.41;
    private static final double TAUX_IMPOT_ENTRE_N04_ET_N05 = 0.45;

    private static final double[] TAUX_IMPOT = {
    		TAUX_IMPOT_ENTRE_N00_ET_N01, 
    		TAUX_IMPOT_ENTRE_N01_ET_N02, 
    		TAUX_IMPOT_ENTRE_N02_ET_N03, 
    		TAUX_IMPOT_ENTRE_N03_ET_N04, 
    		TAUX_IMPOT_ENTRE_N04_ET_N05
    };

    
    private static final int PALIER_MAXIMUM_POUR_ABATEMENT = 14171;
    private static final int PALIER_MINIMUM_POUR_ABATEMENT = 495;
    private static final double TAUX_ABATEMENT = 0.1;

    private static final double PLAFOND_POUR_DEMI_PART = 1759;

    private static final double SEUIL_DECOTE_DECLARANT_SEUL = 1929;
    private static final double SEUIL_DECOTE_DECLARANT_COUPLE = 3191;

    private static final double DECOTE_MAX_DECLARANT_SEUL = 873;
    private static final double DECOTE_MAX_DECLARANT_COUPLE = 1444;
    private static final double TAUX_DECOTE = 0.4525;

    
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
    /*
    @Override
    public void calculImpotSurRevenuNet() {
        // Abattement

        abt = rNet * tAbt;

        if (abt > lAbtMax) {
            abt = lAbtMax;
        }

        if (abt < lAbtMin) {
            abt = lAbtMin;
        }


        rFRef = rNet - abt;

        // parts déclarants
        switch ( sitFam ) {
            case CELIBATAIRE:
                nbPtsDecl = 1;
                break;
            case MARIE:
                nbPtsDecl = 2;
                break;
            case DIVORCE:
                nbPtsDecl = 1;
                break;
            case VEUF:
                if ( nbEnf == 0 ) {
                    nbPtsDecl = 1;
                } else {
                    nbPtsDecl = 2;
                }
                nbPtsDecl = 1;
                break;
        }

        // parts enfants à charge
        if ( nbEnf <= 2 ) {
            nbPts = nbPtsDecl + nbEnf * 0.5;
        } else if ( nbEnf > 2 ) {
            nbPts = nbPtsDecl+  1.0 + ( nbEnf - 2 );
        }

        // parent isolé
        if ( parIso ) {
            if ( nbEnf > 0 ){
                nbPts = nbPts + 0.5;
            }
        }

        // enfant handicapé
        nbPts = nbPts + nbEnfH * 0.5;

        // impôt des declarants
        rImposable = rFRef / nbPtsDecl ;

        mImpDecl = 0;

        int i = 0;
        do {
            if ( rImposable >= limites[i] && rImposable < limites[i+1] ) {
                mImpDecl += ( rImposable - limites[i] ) * taux[i];
                break;
            } else {
                mImpDecl += ( limites[i+1] - limites[i] ) * taux[i];
            }
            i++;
        } while( i < 5);

        mImpDecl = mImpDecl * nbPtsDecl;
        mImpDecl = Math.round( mImpDecl );

        // impôt foyer fiscal complet
        rImposable =  rFRef / nbPts;
        mImp = 0;
        i = 0;

        do {
            if ( rImposable >= limites[i] && rImposable < limites[i+1] ) {
                mImp += ( rImposable - limites[i] ) * taux[i];
                break;
            } else {
                mImp += ( limites[i+1] - limites[i] ) * taux[i];
            }
            i++;
        } while( i < 5);

        mImp = mImp * nbPts;
        mImp = Math.round( mImp );

        // baisse impot
        double baisseImpot = mImpDecl - mImp;

        // dépassement plafond
        double ecartPts = nbPts - nbPtsDecl;

        double plafond = (ecartPts / 0.5) * plafDemiPart;

        if ( baisseImpot >= plafond ) {
            mImp = mImpDecl - plafond;
        }

        decote = 0;
        // decote
        if ( nbPtsDecl == 1 ) {
            if ( mImp < seuilDecoteDeclarantSeul ) {
                 decote = decoteMaxDeclarantSeul - ( mImp  * tauxDecote );
            }
        }
        if (  nbPtsDecl == 2 ) {
            if ( mImp < seuilDecoteDeclarantCouple ) {
                 decote =  decoteMaxDeclarantCouple - ( mImp  * tauxDecote  );
            }
        }
        decote = Math.round( decote );
        if ( mImp <= decote ) {
            decote = mImp;
        }

        mImp = Math.round(mImp) - decote;

        return (long) mImp;
    }
    */
    
    @Override
    public void calculImpotSurRevenuNet() {
    	calculAbatement();
    	calculParts();
    	
    }
    
    private void calculAbatement() {
        abatement = revenuNet * TAUX_ABATEMENT;

        if (abatement > PALIER_MAXIMUM_POUR_ABATEMENT) {
            abatement = PALIER_MAXIMUM_POUR_ABATEMENT;
        }

        if (abatement < PALIER_MINIMUM_POUR_ABATEMENT) {
            abatement = PALIER_MINIMUM_POUR_ABATEMENT;
        }

        revenuFiscalReference = revenuNet - abatement;
    }
    
    private void calculParts() {
    	//µPartie des déclarants
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
        

        //Compte de base pour les enfants
        if ( nbEnfantTotal <= 2 ) {
        	nbParts = nbPartsDeclarant + nbEnfantTotal * 0.5;
        } else if ( nbEnfantTotal > 2 ) { //Les part changes s'il y a plus de 2 enfants
        	nbParts = nbPartsDeclarant +  1.0 + ( nbEnfantTotal - 2 );
        }

        //Ajout des part suplémentaire en cas de parent isolé avec enfant
        if ( isParentIsole && nbEnfantTotal > 0) {
            nbParts = nbParts + 0.5;
        }

        //Ajout des part suplémentaire pour chaque cas d'enfant handicapé
        nbParts = nbParts + nbEnfantHandicape * 0.5;
    }
    
}
