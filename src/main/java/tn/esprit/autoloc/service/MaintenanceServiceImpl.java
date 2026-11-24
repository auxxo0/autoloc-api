package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

@Service
public class MaintenanceServiceImpl extends GenericServiceImpl<Maintenance,Long> implements IMaintenanceService {
    public MaintenanceServiceImpl(IMaintenanceRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Maintenance";
    }

    @Override
    public Long getId(Maintenance entity) {
        return entity.getIdMaintenance();
    }

    @Override
    public void setFields(Maintenance existingEntity, Maintenance entity) {
        existingEntity.setDateDebut(entity.getDateDebut());
        existingEntity.setDateFin(entity.getDateFin());
        existingEntity.setDescription(entity.getDescription());
    }

    public boolean check(Maintenance entity, boolean isNew) {
        return entity.getDateFin().isAfter(entity.getDateDebut());
    }
}
