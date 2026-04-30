package com.kerware.simulateur.simulateurReusine.Calculateur;

import com.kerware.simulateur.SituationFamiliale;

public class CalculateurParts {
  public static double calcul(double nbPartsDeclarant, double nbEnfantTotal, double nbEnfantHandicape,
      boolean isParentIsole) {

    double nbParts = 0;

    // Compte de base pour les enfants
    if (nbEnfantTotal <= 2) {
      nbParts = nbPartsDeclarant + nbEnfantTotal * 0.5;
    } else if (nbEnfantTotal > 2) { // Les part changes s'il y a plus de 2 enfants
      nbParts = nbPartsDeclarant + 1.0 + (nbEnfantTotal - 2);
    }

    // Ajout des part suplémentaire en cas de parent isolé avec enfant
    if (isParentIsole && nbEnfantTotal > 0) {
      nbParts = nbParts + 0.5;
    }

    // Ajout des part suplémentaire pour chaque cas d'enfant handicapé
    nbParts = nbParts + nbEnfantHandicape * 0.5;
    System.out.println("Nombre de parts total : " + nbParts);

    return nbParts;
  }
}
