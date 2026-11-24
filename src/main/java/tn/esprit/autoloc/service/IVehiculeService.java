package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.calcul.VehiculeOffre;

public interface IVehiculeService extends IGenericService<Vehicule,Long> {
    double calculTarif(Vehicule vehicule, int nbJours, VehiculeOffre offre);
    Vehicule desaffecte(Long id);
    Vehicule affecte(Long id, Long idAgence);
    Equipement affecteEquipement(Long id, Long idEquipement);
}
