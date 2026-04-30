package com.kerware.simulateur.simulateurReusine.Calculateur;

import com.kerware.simulateur.simulateurReusine.ImpotDataConstante;

public class CalculateurDecote implements ImpotDataConstante {
  public static double calcul(double montantImpot, double nbPartsDeclarant) {
    double decote = 0;
    if (nbPartsDeclarant == 1) {
      if (montantImpot < SEUIL_DECOTE_DECLARANT_SEUL) {
        decote = DECOTE_MAX_DECLARANT_SEUL - (montantImpot * TAUX_DECOTE);
      }
    }

    if (nbPartsDeclarant == 2) {
      if (montantImpot < SEUIL_DECOTE_DECLARANT_COUPLE) {
        decote = DECOTE_MAX_DECLARANT_COUPLE - (montantImpot * TAUX_DECOTE);

      }
    }

    decote = Math.round(decote);
    if (montantImpot <= decote) {
      decote = montantImpot;
    }
    System.out.println("Decote : " + decote);
    return decote;
  }
}
