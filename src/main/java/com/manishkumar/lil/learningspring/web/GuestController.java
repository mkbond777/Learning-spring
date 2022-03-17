package com.manishkumar.lil.learningspring.web;

import com.manishkumar.lil.learningspring.business.ReservationService;
import com.manishkumar.lil.learningspring.data.Guest;
import com.manishkumar.lil.learningspring.data.GuestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by DT2(m.kumar) on 16/03/22.
 */
@Controller
@RequestMapping("/guests")
public record GuestController(ReservationService reservationService) {

    @RequestMapping(method = RequestMethod.GET)
    public String getGuestList(Model model){
        List<Guest> guests = this.reservationService.getAllGuest();
        model.addAttribute("guests",guests );
        return "hotel-guests";
    }
}
