package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("STANDARD")
public class VehiculeStrategieOffreStandard implements VehiculeStrategieOffre {
    @Override
    public double calcul(double tarifJournalier, int nbJours) {
        return tarifJournalier * nbJours;
    }
}
