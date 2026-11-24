package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.io.AgenceReporterFactory;
import tn.esprit.autoloc.io.HybridReport;
import tn.esprit.autoloc.io.IAgenceReporter;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
@HybridReport
public class AgenceServiceImpl extends GenericServiceImpl<Agence,Long> implements IAgenceService {
    private final IVehiculeService vehiculeService;
    public AgenceServiceImpl(IAgenceRepository repository, IVehiculeService vehiculeService) {
        super(repository);
        this.vehiculeService = vehiculeService;
    }

    @Override
    public String getEntityName() {
        return "Agence";
    }

    @Override
    public Long getId(Agence entity) {
        return entity.getIdAgence();
    }

    @Override
    public void setFields(Agence existingEntity, Agence entity) {
        existingEntity.setNom(entity.getNom());
        existingEntity.setVille(entity.getVille());
        existingEntity.setAdresse(entity.getAdresse());
        existingEntity.setTelephone(entity.getTelephone());
    }

    public boolean check(Agence entity, boolean isNew) {
        return Utils.isNotEmpty(entity.getNom()) &&
                Utils.isNotEmpty(entity.getTelephone());
    }

    public boolean hasVehicules(Long id) {
        return findById(id).getVehicules().size() > 0;
    }

    @Override
    public void deleteById(Long id) {
        findById(id).getVehicules().forEach(v -> {v.setAgence(null);});
        super.deleteById(id);
    }

    public String simuler (Agence agence, int nbJours, VehiculeOffre offre) {

        IAgenceReporter reporter = AgenceReporterFactory.createReport(this);

        reporter.putHeader("Id : " + agence.getIdAgence() + ", Nom : " + agence.getNom() + ", Tel : " + agence.getTelephone() + "\n");

        double total = 0;

        for(var vehicule : agence.getVehicules()) {
            total += vehiculeService.calculTarif(vehicule, nbJours, offre);
        }

        reporter.putTarif("Total tarif : " + total + "\n");
        return reporter.getString();
    }
}
