package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeStrategieOffre;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.Map;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule,Long> implements IVehiculeService {
    @Autowired
    private Map<String, VehiculeStrategieOffre> vehiculeStrategieOffres;
    private final IAgenceRepository agenceRepository;
    private final IEquipementRepository equipementRepository;

    public VehiculeServiceImpl(IVehiculeRepository repository, IAgenceRepository agenceRepository, IEquipementRepository equipementRepository) {
        super(repository);
        this.agenceRepository = agenceRepository;
        this.equipementRepository = equipementRepository;
    }

    @Override
    public String getEntityName() {
        return "Vehicule";
    }

    @Override
    public Long getId(Vehicule entity) {
        return entity.getIdVehicule();
    }

    @Override
    public void setFields(Vehicule existingEntity, Vehicule entity) {
        existingEntity.setImmatriculation(entity.getImmatriculation());
        existingEntity.setCategorie(entity.getCategorie());
        existingEntity.setMarque(entity.getMarque());
        existingEntity.setModele(entity.getModele());
        existingEntity.setStatut(entity.getStatut());
        existingEntity.setTarifJournalier(entity.getTarifJournalier());
    }

    public boolean check(Vehicule entity, boolean isNew) {
        return Utils.isNotEmpty(entity.getImmatriculation());
    }

    public double calculTarif(Vehicule vehicule, int nbJours, VehiculeOffre offre) {
        return vehiculeStrategieOffres.get(offre.toString()).
                calcul(vehicule.getTarifJournalier().toBigInteger().doubleValue(), nbJours);
    }

    @Override
    public Vehicule desaffecte(Long id) {
        var vehicule = findById(id);

        vehicule.setAgence(null);
        return repository.save(vehicule);
    }

    @Override
    public Vehicule affecte(Long id, Long idAgence) {
        var vehicule = findById(id);
        var agence = agenceRepository.findById(idAgence).
                orElseThrow(() -> new ResourceNotFoundException("Agence", idAgence.toString()));

        vehicule.setAgence(agence);
        return repository.save(vehicule);
    }

    @Override
    public Equipement affecteEquipement(Long id, Long idEquipement) {
        var vehicule = findById(id);
        var equipement = equipementRepository.findById(idEquipement).
                orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement.toString()));

        equipement.getVehicules().add (vehicule);
        return equipementRepository.save(equipement);
    }
}
