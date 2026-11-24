package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("LONGUE_DUREE")
public class VehiculeStrategieOffreLongueDuree implements VehiculeStrategieOffre {
    @Override
    public double calcul(double tarifJournalier, int nbJours) {
        double total = tarifJournalier * 0.75 * nbJours;

        if (total > 2000)
        {
            return total;
        }

        return total;
    }
}
