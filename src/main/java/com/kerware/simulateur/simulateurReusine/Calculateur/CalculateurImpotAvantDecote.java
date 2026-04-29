package com.kerware.simulateur.simulateurReusine.Calculateur;

import com.kerware.simulateur.simulateurReusine.ImpotDataConstante;

public class CalculateurImpotAvantDecote implements ImpotDataConstante {
	public static double calcul(double revenuFiscalReference, double nbParts) {
        double revenuImposable =  revenuFiscalReference / nbParts;
        double montantImpot = 0;
        
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
        System.out.println("Impot total avant decote : " + montantImpot);
        return montantImpot;
	}
}
