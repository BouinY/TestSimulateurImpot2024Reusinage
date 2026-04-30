package com.kerware.simulateur.simulateurReusine.Calculateur;

import com.kerware.simulateur.simulateurReusine.ImpotDataConstante;

public class CalculateurPlafond implements ImpotDataConstante {

  public static double calcul(double montantImpotDeclarant, double montantImpot, double nbParts,
      double nbPartsDeclarant) {
    // baisse impot
    double baisseImpot = montantImpotDeclarant - montantImpot;
    System.out.println("Baisse d'impot avant plafond : " + baisseImpot);

    // dépassement plafond
    double ecartParts = nbParts - nbPartsDeclarant;

    double plafond = (ecartParts / 0.5) * PLAFOND_POUR_DEMI_PART;	
    System.out.println("Plafond : " + plafond);

    if (baisseImpot >= plafond) {
      System.out.println("Baisse d'impot supérieur au plafond, le plafond est appliqué ");
      montantImpot = montantImpotDeclarant - plafond;
      System.out.println("Montant après plafond : " + montantImpot);
    }

    // La baisse d'impot n'est jamais appliquer si elle ne depasse pas le plafond,
    // étant coder ainsi dans le simulateur originelle, je ne l'ai pas changer pour
    // garder les test cohérent.
    return montantImpot;
  }
}
