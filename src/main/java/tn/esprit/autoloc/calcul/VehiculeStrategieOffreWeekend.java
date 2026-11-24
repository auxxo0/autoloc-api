package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("WEEKEND")
public class VehiculeStrategieOffreWeekend implements VehiculeStrategieOffre {
    @Override
    public double calcul(double tarifJournalier, int nbJours) {
        return tarifJournalier * 0.8 * nbJours;
    }
}
