package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@Tag(name = "Vehicules", description = "Gestion des vehicules")
@RestController
@RequestMapping("vehicules")
@RequiredArgsConstructor
public class VehiculeController {
    private final IVehiculeService vehiculeService;

    @Operation(summary = "Lister les vehicules")
    @GetMapping
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @Operation(summary = "Consulter un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule trouvé"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable") })
    @GetMapping("/{id}")
    public Vehicule findById(@PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @Operation(summary = "Ajouter un vehicule")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @Operation(summary = "Modifier un vehicule")
    @PutMapping("/{id}")
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @Operation(summary = "Supprimer un vehicule")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }

    @PutMapping("/desaffecte/{id}")
    public Vehicule desaffecte(@PathVariable Long id) {
        return vehiculeService.desaffecte(id);
    }

    @PutMapping("/affecte/{id}/{idAgence}")
    public Vehicule affecte(@PathVariable Long id, @PathVariable Long idAgence) {
        return vehiculeService.affecte(id, idAgence);
    }

    @PutMapping("/{id}/equipement/{idEquipement}")
    public Equipement affecteEquipement(@PathVariable Long id, @PathVariable Long idEquipement) {
        return vehiculeService.affecteEquipement(id, idEquipement);
    }
}
