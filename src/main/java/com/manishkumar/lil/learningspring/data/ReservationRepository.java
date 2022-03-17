package com.manishkumar.lil.learningspring.data;

import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

/**
 * Created by DT2(m.kumar) on 12/03/22.
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    Iterable<Reservation> findReservationByDate(Date date);

}
