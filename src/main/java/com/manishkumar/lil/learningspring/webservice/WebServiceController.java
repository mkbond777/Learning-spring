package com.manishkumar.lil.learningspring.webservice;

import com.manishkumar.lil.learningspring.business.ReservationService;
import com.manishkumar.lil.learningspring.business.RoomReservation;
import com.manishkumar.lil.learningspring.data.Guest;
import com.manishkumar.lil.learningspring.data.Room;
import com.manishkumar.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by DT2(m.kumar) on 17/03/22.
 */
@RestController
@RequestMapping("/api")
public record WebServiceController(DateUtils dateUtils,
                                   ReservationService reservationService) {

    @RequestMapping(path = "/reservations",method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date",required = false)String dateString){
        Date date  = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);

    }

    @GetMapping(path="guests")
    public List<Guest> getHotelGuests(){
        return this.reservationService.getAllGuest();
    }

    @PostMapping(path = "guests")
    @ResponseStatus(HttpStatus.CREATED)
    public Guest saveGuest(@RequestBody Guest guest){
        return this.reservationService.saveGuest(guest);
    }

    @RequestMapping(path="rooms",method = RequestMethod.GET)
    public List<Room> getHotelRooms(){
        return this.reservationService.getHotelRooms();
    }


}
