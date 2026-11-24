package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

@Service
public class EquipementServiceImpl extends GenericServiceImpl<Equipement,Long> implements IEquipementService {
    public EquipementServiceImpl(IEquipementRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Equipement";
    }

    @Override
    public Long getId(Equipement entity) {
        return entity.getIdEquipement();
    }

    @Override
    public void setFields(Equipement existingEntity, Equipement entity) {
        existingEntity.setLibelle(entity.getLibelle());
    }

    public boolean check(Equipement entity, boolean isNew) {
        return Utils.isNotEmpty(entity.getLibelle());
    }
}
