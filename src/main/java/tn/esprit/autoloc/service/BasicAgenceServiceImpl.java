package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicAgenceServiceImpl implements IBasicAgenceService {
    final IAgenceRepository repository;

    public boolean check(Agence agence, boolean isNew) {
        return Utils.isNotEmpty(agence.getNom()) &&
                Utils.isNotEmpty(agence.getTelephone());
    }

    public Agence create(Agence agence){
        if (agence.getIdAgence() != null) {
             throw new IllegalArgumentException("Une nouvelle agence ne doit pas avoir d'identifiant !!");
        }

        if (!check(agence, true)) {
            throw new IllegalArgumentException("Vérifier les données de l'agence !!");
        }

        return repository.save(agence);
    }
    public Agence findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agence", id.toString()));
    }
    public List<Agence> findAll(){
        return repository.findAll();
    }
    public Agence update(Long id, Agence agence){
        var agenceExistant = findById(id);

        if (!check(agence, false)) {
            throw new IllegalArgumentException("Vérifier les données de l'agence !!");
        }

        agenceExistant.setNom(agence.getNom());
        agenceExistant.setVille(agence.getVille());
        agenceExistant.setAdresse(agence.getAdresse());
        agenceExistant.setTelephone(agence.getTelephone());

        return repository.save(agenceExistant);
    }
    public void deleteById(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id.toString());
        }

        repository.deleteById(id);
    }

    public boolean hasVehicules(Long id){
        return findById(id).getVehicules().size() > 0;
    }
}
