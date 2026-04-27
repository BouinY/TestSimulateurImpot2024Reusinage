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
    private int nbEnfant = 0;
    private int nbEnfantHandicape = 0;

    private double revenuFiscalReference = 0;
    private double revenuImposable = 0;

    private double abatement = 0;

    private double nbPartsDeclarant = 0;
    private double nbParts = 0;
    private double decote = 0;

    private double montantImpotDeclarant = 0;
    private double montantImpot = 0;

    private boolean isParentIsole = false;
    private SituationFamiliale situationFamiliale;

    
    
    
    @Override
    public void setRevenusNet(int rn) {
    }
    
    @Override
    public void setSituationFamiliale(SituationFamiliale sf) {
    }

    @Override
    public void setNbEnfantsACharge(int nbe) {
    }

    @Override
    public void setNbEnfantsSituationHandicap(int nbesh) {
    }

    @Override
    public void setParentIsole(boolean pi) {
    }

    @Override
    public void calculImpotSurRevenuNet() {
    }
    
    @Override
    public int getRevenuFiscalReference() {
        return;   
    }

    @Override
    public int getAbattement() {
        return ;
    }

    @Override
    public double getNbPartsFoyerFiscal() {
        return ;
    }

    @Override
    public int getImpotAvantDecote() {
        return;
    }

    @Override
    public int getDecote() {
        return ;
    }

    @Override
    public int getImpotSurRevenuNet() {
        return;
    }
}
