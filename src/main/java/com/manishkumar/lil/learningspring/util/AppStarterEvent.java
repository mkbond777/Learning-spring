package com.manishkumar.lil.learningspring.util;

import com.manishkumar.lil.learningspring.data.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Created by DT2(m.kumar) on 09/03/22.
 */

@Component
public record AppStarterEvent(RoomRepository roomRepository,
                              GuestRepository guestRepository,
                              ReservationRepository reservationRepository)
        implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);

        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);

        Iterable<Reservation> reservations = this.reservationRepository.findAll();
        reservations.forEach(System.out::println);

        String str="2022-01-01";
        Iterable<Reservation> reservationByDate = this.reservationRepository.findReservationByDate(Date.valueOf(str));
        reservationByDate.forEach(System.out::println);
    }
}
