package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.calcul.VehiculeOffre;

public interface IAgenceService extends IBasicAgenceService {
    String simuler (Agence agence, int nbJours, VehiculeOffre offre);
}
