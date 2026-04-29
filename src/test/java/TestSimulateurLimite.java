import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kerware.simulateur.SituationFamiliale;
import com.kerware.simulateur.simulateurReusine.SimulateurReusine;

public class TestSimulateurLimite {

	/*
    @Test
    @DisplayName("Revenu à 0")
    void testRevenuZero() {
        SimulateurReusine sim = new SimulateurReusine();

        sim.setRevenusNet(0);
        sim.setSituationFamiliale(SituationFamiliale.CELIBATAIRE);
        sim.setNbEnfantsACharge(0);
        sim.setNbEnfantsSituationHandicap(0);
        sim.setParentIsole(false);

        sim.calculImpotSurRevenuNet();

        assertEquals(0, sim.getImpotSurRevenuNet());
    }
    */
	
    @Test
    @DisplayName("Passage tranche (11294€)")
    void testLimiteTranche1() {
        SimulateurReusine sim = new SimulateurReusine();

        sim.setRevenusNet(11294);
        sim.setSituationFamiliale(SituationFamiliale.CELIBATAIRE);
        sim.setNbEnfantsACharge(0);
        sim.setNbEnfantsSituationHandicap(0);
        sim.setParentIsole(false);

        sim.calculImpotSurRevenuNet();

        assertEquals(0, sim.getImpotSurRevenuNet());
    }

    @Test
    @DisplayName("Différence entre 2 et 3 enfants")
    void testPassageDeuxATroisEnfants() {
        SimulateurReusine sim1 = new SimulateurReusine();
        SimulateurReusine sim2 = new SimulateurReusine();

        // 2 enfants
        sim1.setRevenusNet(60000);
        sim1.setSituationFamiliale(SituationFamiliale.MARIE);
        sim1.setNbEnfantsACharge(2);
        sim1.setNbEnfantsSituationHandicap(0);
        sim1.setParentIsole(false);
        sim1.calculImpotSurRevenuNet();

        // 3 enfants
        sim2.setRevenusNet(60000);
        sim2.setSituationFamiliale(SituationFamiliale.MARIE);
        sim2.setNbEnfantsACharge(3);
        sim2.setNbEnfantsSituationHandicap(0);
        sim2.setParentIsole(false);
        sim2.calculImpotSurRevenuNet();

        // Vérifie que ça change
        assertEquals(false, sim1.getNbPartsFoyerFiscal() == sim2.getNbPartsFoyerFiscal());
    }

    @Test
    @DisplayName("Activation décote")
    void testActivationDecote() {
        SimulateurReusine sim = new SimulateurReusine();

        sim.setRevenusNet(15000);
        sim.setSituationFamiliale(SituationFamiliale.CELIBATAIRE);
        sim.setNbEnfantsACharge(0);
        sim.setNbEnfantsSituationHandicap(0);
        sim.setParentIsole(false);

        sim.calculImpotSurRevenuNet();

        // décote doit être > 0
        assertEquals(true, sim.getDecote() > 0);
    }

    @Test
    @DisplayName("Parent isolé avec 1 enfant")
    void testParentIsole() {
        SimulateurReusine sim = new SimulateurReusine();

        sim.setRevenusNet(30000);
        sim.setSituationFamiliale(SituationFamiliale.DIVORCE);
        sim.setNbEnfantsACharge(1);
        sim.setNbEnfantsSituationHandicap(0);
        sim.setParentIsole(true);

        sim.calculImpotSurRevenuNet();

        assertEquals(2.0, sim.getNbPartsFoyerFiscal());
    }
}