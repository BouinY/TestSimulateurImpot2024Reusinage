package com.kerware.simulateur.simulateurReusine;

public interface ImpotDataConstante {
    public static final int PALIER_IMPOT_N00 = 0 ;
    public static final int PALIER_IMPOT_N01 = 11294;
    public static final int PALIER_IMPOT_N02 = 28797;
    public static final int PALIER_IMPOT_N03 = 82341;
    public static final int PALIER_IMPOT_N04 = 177106;
    public static final int PALIER_IMPOT_N05 = Integer.MAX_VALUE;

    public static final int[] PALIER_IMPOT = {
    	    PALIER_IMPOT_N00, 
    	    PALIER_IMPOT_N01, 
    	    PALIER_IMPOT_N02, 
    	    PALIER_IMPOT_N03, 
    	    PALIER_IMPOT_N04, 
    	    PALIER_IMPOT_N05
    	};

    
    public static final double TAUX_IMPOT_ENTRE_N00_ET_N01 = 0.0;
    public static final double TAUX_IMPOT_ENTRE_N01_ET_N02 = 0.11;
    public static final double TAUX_IMPOT_ENTRE_N02_ET_N03 = 0.3;
    public static final double TAUX_IMPOT_ENTRE_N03_ET_N04 = 0.41;
    public static final double TAUX_IMPOT_ENTRE_N04_ET_N05 = 0.45;

    public static final double[] TAUX_IMPOT = {
    		TAUX_IMPOT_ENTRE_N00_ET_N01, 
    		TAUX_IMPOT_ENTRE_N01_ET_N02, 
    		TAUX_IMPOT_ENTRE_N02_ET_N03, 
    		TAUX_IMPOT_ENTRE_N03_ET_N04, 
    		TAUX_IMPOT_ENTRE_N04_ET_N05
    };

    
    public static final int PALIER_MAXIMUM_POUR_ABATEMENT = 14171;
    public static final int PALIER_MINIMUM_POUR_ABATEMENT = 495;
    public static final double TAUX_ABATEMENT = 0.1;

    public static final double PLAFOND_POUR_DEMI_PART = 1759;

    public static final double SEUIL_DECOTE_DECLARANT_SEUL = 1929;
    public static final double SEUIL_DECOTE_DECLARANT_COUPLE = 3191;

    public static final double DECOTE_MAX_DECLARANT_SEUL = 873;
    public static final double DECOTE_MAX_DECLARANT_COUPLE = 1444;
    public static final double TAUX_DECOTE = 0.4525;
}
