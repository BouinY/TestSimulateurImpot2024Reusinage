package com.kerware.simulateur.simulateurReusine.Calculateur;

import com.kerware.simulateur.simulateurReusine.ImpotDataConstante;

public class CalculateurAbatement implements ImpotDataConstante {

  public static double calcul(int revenuNet) {
    double abatement = revenuNet * TAUX_ABATEMENT;

    System.out.println("L'abattement avant l'application des paliers est : " + abatement);

    if (abatement > PALIER_MAXIMUM_POUR_ABATEMENT) {
      abatement = PALIER_MAXIMUM_POUR_ABATEMENT;
    }

    if (abatement < PALIER_MINIMUM_POUR_ABATEMENT) {
      abatement = PALIER_MINIMUM_POUR_ABATEMENT;
    }

    System.out.println("L'abattement après l'application des paliers est : " + abatement);
    return abatement;
  }
}
