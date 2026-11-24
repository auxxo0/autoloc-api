package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

@Service
public class ClientServiceImpl extends GenericServiceImpl<Client,Long> implements IClientService {
    public ClientServiceImpl(IClientRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Client";
    }

    @Override
    public Long getId(Client entity) {
        return entity.getIdClient();
    }

    @Override
    public void setFields(Client existingEntity, Client entity) {
        existingEntity.setNom(entity.getNom());
        existingEntity.setPrenom(entity.getPrenom());
        existingEntity.setEmail(entity.getEmail());
        existingEntity.setTelephone(entity.getTelephone());
        existingEntity.setNumPermis(entity.getNumPermis());
        existingEntity.setDateInscription(entity.getDateInscription());
    }

    public boolean check(Client entity, boolean isNew) {
        return Utils.isNotEmpty(entity.getNom()) &&
                Utils.isNotEmpty(entity.getPrenom()) &&
                Utils.isNotEmpty(entity.getTelephone());
    }
}
