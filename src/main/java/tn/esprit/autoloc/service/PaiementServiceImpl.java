package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {
    final IPaiementRepository repository;

    public Paiement findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paiement", id.toString()));
    }
    public List<Paiement> findAll(){
        return repository.findAll();
    }
}
