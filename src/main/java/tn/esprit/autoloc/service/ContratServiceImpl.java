package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

@Service
public class ContratServiceImpl extends GenericServiceImpl<Contrat,Long> implements IContratService {
    public ContratServiceImpl(IContratRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Contrat";
    }

    @Override
    public Long getId(Contrat entity) {
        return entity.getIdContrat();
    }

    @Override
    public void setFields(Contrat existingEntity, Contrat entity) {
        existingEntity.setDateSignature(entity.getDateSignature());
        existingEntity.setMontantTotal(entity.getMontantTotal());
        existingEntity.setValide(entity.getValide());
    }

    public boolean check(Contrat entity, boolean isNew) {
        return true;
    }
}
