import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kerware.simulateur.SituationFamiliale;
import com.kerware.simulateur.simulateurReusine.SimulateurReusine;

public class TestSimulateurException {

    @Test
    @DisplayName("Exception si revenu négatif")
    void testRevenuNegatif() {
        SimulateurReusine sim = new SimulateurReusine();

        assertThrows(IllegalArgumentException.class, () -> {
            sim.setRevenusNet(-1000);
        });
    }

    @Test
    @DisplayName("Exception si nombre d'enfants négatif")
    void testNbEnfantsNegatif() {
        SimulateurReusine sim = new SimulateurReusine();

        assertThrows(IllegalArgumentException.class, () -> {
            sim.setNbEnfantsACharge(-1);
        });
    }

    @Test
    @DisplayName("Exception si enfants handicapés négatif")
    void testNbEnfantsHandicapesNegatif() {
        SimulateurReusine sim = new SimulateurReusine();

        assertThrows(IllegalArgumentException.class, () -> {
            sim.setNbEnfantsSituationHandicap(-2);
        });
    }

    @Test
    @DisplayName("Exception si enfants handicapés > enfants totaux")
    void testNbEnfantsHandicapesSuperieur() {
        SimulateurReusine sim = new SimulateurReusine();

        sim.setNbEnfantsACharge(1);

        assertThrows(IllegalArgumentException.class, () -> {
            sim.setNbEnfantsSituationHandicap(2);
        });
    }

    @Test
    @DisplayName("Exception si situation familiale null")
    void testSituationFamilialeNull() {
        SimulateurReusine sim = new SimulateurReusine();

        assertThrows(IllegalArgumentException.class, () -> {
            sim.setSituationFamiliale(null);
        });
    }
}
