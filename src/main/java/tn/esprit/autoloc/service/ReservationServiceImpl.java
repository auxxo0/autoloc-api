package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation,Long> implements IReservationService {
    public ReservationServiceImpl(IReservationRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Reservation";
    }

    @Override
    public Long getId(Reservation entity) {
        return entity.getIdReservation();
    }

    @Override
    public void setFields(Reservation existingEntity, Reservation entity) {
        existingEntity.setDateDebut(entity.getDateDebut());
        existingEntity.setDateFin(entity.getDateFin());
        existingEntity.setStatut(entity.getStatut());
    }

    public boolean check(Reservation entity, boolean isNew) {
        return entity.getDateFin().isAfter(entity.getDateDebut());
    }
}
