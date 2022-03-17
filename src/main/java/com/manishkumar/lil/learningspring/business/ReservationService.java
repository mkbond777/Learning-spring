package com.manishkumar.lil.learningspring.business;

import com.manishkumar.lil.learningspring.data.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by DT2(m.kumar) on 13/03/22.
 */
@Service
public record ReservationService(RoomRepository roomRepository,
                                 GuestRepository guestRepository,
                                 ReservationRepository reservationRepository) {

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }

    public List<Guest> getAllGuest(){
        Iterable<Guest> guests =  this.guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();
        guests.forEach(guestList::add);

        guestList.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest g1, Guest g2) {
                if (g1.getLastName().equals(g2.getLastName())) {
                    return g1.getFirstName().compareTo(g2.getFirstName());
                }
                return g1.getLastName().compareTo(g2.getLastName());
            }
        });

        return guestList;

    }

    public Guest saveGuest(Guest guest){
        return this.guestRepository.save(guest);
    }

    public List<Room> getHotelRooms(){
        Iterable<Room> rooms = this.roomRepository.findAll();
        List<Room> hotelRooms = new ArrayList<>();
        rooms.forEach(hotelRooms::add);
        return hotelRooms;
    }
}
