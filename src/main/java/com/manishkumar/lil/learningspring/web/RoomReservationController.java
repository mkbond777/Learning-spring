package com.manishkumar.lil.learningspring.web;

import com.manishkumar.lil.learningspring.business.ReservationService;
import com.manishkumar.lil.learningspring.business.RoomReservation;
import com.manishkumar.lil.learningspring.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by DT2(m.kumar) on 14/03/22.
 */
@Controller
@RequestMapping("/reservations")
public record RoomReservationController(ReservationService reservationService,
                                        DateUtils dateUtils) {

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations",roomReservations  );
        return "roomres";
    }

}
