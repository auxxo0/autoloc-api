package tn.esprit.autoloc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.service.IAgenceService;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AgenceTests {
    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;

    @Autowired
    private IAgenceService agenceService;

    private void addAgence(CrudRepository<Agence, Long> repository) {
        Agence agence = new Agence(
                null, "Agence ariana", "Tunis", "1 Rue Hedi", "71585874",
                new HashSet<>(),  null);

        int ms = (int)System.currentTimeMillis();

        Vehicule vehicule1 = new Vehicule(
                null, "785414TU95-" + ms, "Toyota", "Yaris",
                CategorieVehicule.UTILITAIRE, new BigDecimal(80), StatutVehicule.DISPONIBLE, agence,  null, null);

        Vehicule vehicule2 = new Vehicule(
                null, "785414TU96-" + ms, "Isuzu", "DMax",
                CategorieVehicule.SUV, new BigDecimal(100), StatutVehicule.MAINTENANCE, agence, null, null);

        agence.getVehicules().add(vehicule1);
        agence.getVehicules().add(vehicule2);

        repository.save(agence);
    }

    @Test
    public void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    public void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }


    private void loadAgence(CrudRepository<Agence, Long> repository, String repoType) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Repo : " + repoType + "\n");

        repository.findAll().forEach(e -> {
            stringBuilder.append("\n" + e.getIdAgence() + " | " + e.getNom() + "\n");
            stringBuilder.append("Vehicules Count : " + e.getVehicules().size() + "\n");
            e.getVehicules().forEach(v -> stringBuilder.append("=== " + v.getIdVehicule() + "|" + v.getImmatriculation() + "\n"));
        });
        fail (stringBuilder.toString());
    }

    @Test
    public void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "Basic");
    }

    @Test
    public void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "Full");
    }

    @Test
    public void loadSortedAgences() {
        StringBuilder stringBuilder = new StringBuilder();

        var sort = Sort.by("idAgence").descending();

        fullAgenceRepository.findAll(sort).forEach(e -> {
            stringBuilder.append("\n" + e.getIdAgence() + " | " + e.getNom() + "\n");
            stringBuilder.append("Vehicules Count : " + e.getVehicules().size());
        });
        fail (stringBuilder.toString());
    }

    @Test
    public void loadPagedAgences() {
        StringBuilder stringBuilder = new StringBuilder();

        var sort = Sort.by("idAgence").descending();
        var pageRequest = PageRequest.of(0, 2, sort);
        Page<Agence> page = fullAgenceRepository.findAll(pageRequest);

        stringBuilder.append("\nTotal pages : " + page.getTotalPages() + "\n");
        do {
            stringBuilder.append("\n -- Current page : " + page.getNumber() + " --\n");
            page.getContent().forEach ( e -> {
                stringBuilder.append("\n" + e.getIdAgence() + " | " + e.getNom() + "\n");
                stringBuilder.append("Vehicules Count : " + e.getVehicules().size() + "\n");
            });

            if (page.hasNext()) {
                pageRequest = PageRequest.of(page.getNumber() + 1, 2, sort);
                page = fullAgenceRepository.findAll(pageRequest);
            }
            else {
                page = null;
            }
        }while (page != null);

        fail (stringBuilder.toString());
    }

    @Test
    public void createAgence() {
        Agence agence = new Agence(
                null, "Agence passage", "Tunis 2", "1 Rue Hedi", "71585874",
                null,  null);

        agenceService.create(agence);
    }
}

@Repository
interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}