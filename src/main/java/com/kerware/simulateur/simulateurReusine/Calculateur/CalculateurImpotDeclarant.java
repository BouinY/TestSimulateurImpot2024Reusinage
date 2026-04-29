package com.kerware.simulateur.simulateurReusine.Calculateur;

import com.kerware.simulateur.simulateurReusine.ImpotDataConstante;

public class CalculateurImpotDeclarant implements ImpotDataConstante{
    private double calcul(double revenuFiscalReference, double nbPartsDeclarant) {
    	double revenuImposable = revenuFiscalReference / nbPartsDeclarant ;

    	double montantImpotDeclarant = 0;

        int i = 0;
        do {
            if ( revenuImposable >= PALIER_IMPOT[i] && revenuImposable < PALIER_IMPOT[i+1] ) {
            	montantImpotDeclarant += ( revenuImposable - PALIER_IMPOT[i] ) * TAUX_IMPOT[i];
                break;
            } else {
            	montantImpotDeclarant += (PALIER_IMPOT[i+1] - PALIER_IMPOT[i] ) * TAUX_IMPOT[i];
            }
            i++;
        } while( i < 5);

        montantImpotDeclarant = montantImpotDeclarant * nbPartsDeclarant;
        montantImpotDeclarant = Math.round( montantImpotDeclarant );
        System.out.print("Impot declarant : " + montantImpotDeclarant);
        return montantImpotDeclarant;
    }
}
