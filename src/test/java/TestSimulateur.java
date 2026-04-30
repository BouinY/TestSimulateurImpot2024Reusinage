import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kerware.simulateur.ICalculateurImpot;
import com.kerware.simulateur.SituationFamiliale;
import com.kerware.simulateur.simulateurOriginelle.AdaptateurCodeHerite;
import com.kerware.simulateur.simulateurReusine.SimulateurReusine;


public class TestSimulateur {
	static final int CODE_HERITE = 1;
	static final int CODE_REUSINE = 2;
	static final int CODE = CODE_REUSINE;
	
	static ICalculateurImpot calculateur;
	
	@BeforeAll
	public static void prepareCalculateurImpot() {
		switch(CODE) {
			case CODE_HERITE -> calculateur = new AdaptateurCodeHerite();
			case CODE_REUSINE -> calculateur = new SimulateurReusine();
		}
	}

	//Test des situations possibles
	
	@Test
	@DisplayName("Test du calcul de l'impot pour un célibataire sans enfant")
	public void testImpotSurRevenuNetPourUnCelibaitaireSansEnfant() {
		//Arrange
		calculateur.setRevenusNet(35000);
		calculateur.setSituationFamiliale(SituationFamiliale.CELIBATAIRE);
		calculateur.setNbEnfantsACharge(0);
		calculateur.setNbEnfantsSituationHandicap(0);
		calculateur.setParentIsole(false);
		
		//Act
		calculateur.calculImpotSurRevenuNet();
		
		//Assert
		assertEquals(2736, calculateur.getImpotSurRevenuNet());
		assertEquals(1, calculateur.getNbPartsFoyerFiscal());
	}
		
	@Test
	@DisplayName("Test du calcul de l'impôt pour un couple sans enfant")
	public void testImpotPourUnCoupleSansEnfant() {
	    // Arrange
	    calculateur.setRevenusNet(70000);
	    calculateur.setSituationFamiliale(SituationFamiliale.MARIE);
	    calculateur.setNbEnfantsACharge(0);
	    calculateur.setNbEnfantsSituationHandicap(0);
	    calculateur.setParentIsole(false);
	    
	    // Act
	    calculateur.calculImpotSurRevenuNet();
	    
	    // Assert
	    assertEquals(5472, calculateur.getImpotSurRevenuNet());
	    assertEquals(2, calculateur.getNbPartsFoyerFiscal());
	}
	
	@Test
	@DisplayName("Test du calcul de l'impôt pour un couple avec enfants")
	public void testImpotPourUnCoupleAvecEnfants() {
	    // Arrange
	    calculateur.setRevenusNet(70000);
	    calculateur.setSituationFamiliale(SituationFamiliale.MARIE);
	    calculateur.setNbEnfantsACharge(2);
	    calculateur.setNbEnfantsSituationHandicap(0);
	    calculateur.setParentIsole(false);
	    
	    // Act
	    calculateur.calculImpotSurRevenuNet();
	    
	    // Assert
	    assertEquals(3203, calculateur.getImpotSurRevenuNet());
	    assertEquals(3, calculateur.getNbPartsFoyerFiscal()); // 1 + 1 + 0.5 + 0.5 (Les enfant compte pour 0.5)
	
	
	}
	
	@Test
	@DisplayName("Test du calcul de l'impôt pour une personne divorcé avec enfants")
	public void testImpotPourUnDivorceAvecEnfant() {
	    // Arrange
	    calculateur.setRevenusNet(35000);
	    calculateur.setSituationFamiliale(SituationFamiliale.DIVORCE);
	    calculateur.setNbEnfantsACharge(1);
	    calculateur.setNbEnfantsSituationHandicap(0);
	    calculateur.setParentIsole(false);
	    
	    // Act
	    calculateur.calculImpotSurRevenuNet();
	    
	    // Assert
	    assertEquals(1452, calculateur.getImpotSurRevenuNet());
	    assertEquals(1.5, calculateur.getNbPartsFoyerFiscal()); // 1 + 0.5 
	}	
	
	@Test
	@DisplayName("Test du calcul de l'impôt pour une personne veuf isolé avec enfants")
	public void testImpotPourUnVeufAvecEnfant() {
	    // Arrange
	    calculateur.setRevenusNet(35000);
	    calculateur.setSituationFamiliale(SituationFamiliale.VEUF);
	    calculateur.setNbEnfantsACharge(1);
	    calculateur.setNbEnfantsSituationHandicap(0);
	    calculateur.setParentIsole(true);
	    
	    // Act
	    calculateur.calculImpotSurRevenuNet();
	    
	    // Assert
	    assertEquals(550, calculateur.getImpotSurRevenuNet());
	    assertEquals(2, calculateur.getNbPartsFoyerFiscal()); // 1 + 1 (Un parent isolé on leur enfant comptant pour 1 aulieu de 0.5)
	}	

	
	@Test
	@DisplayName("Test du calcul de l'impôt pour un couple avec enfant handicapé")
	public void testImpotPourUnCoupleAvecEnfantHandicape() {
	    // Arrange
	    calculateur.setRevenusNet(70000);
	    calculateur.setSituationFamiliale(SituationFamiliale.MARIE);
	    calculateur.setNbEnfantsACharge(1);
	    calculateur.setNbEnfantsSituationHandicap(1);
	    calculateur.setParentIsole(false);
	    
	    // Act
	    calculateur.calculImpotSurRevenuNet();
	    
	    // Assert
	    assertEquals(3203, calculateur.getImpotSurRevenuNet());
	    assertEquals(3, calculateur.getNbPartsFoyerFiscal()); // 1 + 1 + 0.5 + 0.5
	}
}
