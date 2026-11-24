package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

@Service
public class EmployeServiceImpl extends GenericServiceImpl<Employe,Long> implements IEmployeService {
    public EmployeServiceImpl(IEmployeRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Employe";
    }

    @Override
    public Long getId(Employe entity) {
        return entity.getIdEmploye();
    }

    @Override
    public void setFields(Employe existingEntity, Employe entity) {
        existingEntity.setNom(entity.getNom());
        existingEntity.setPrenom(entity.getPrenom());
        existingEntity.setRole(entity.getRole());
    }

    public boolean check(Employe entity, boolean isNew) {
        return Utils.isNotEmpty(entity.getNom()) &&
                Utils.isNotEmpty(entity.getPrenom());
    }
}
