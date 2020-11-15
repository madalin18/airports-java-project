package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.FlightRepository;

import java.util.List;

@Controller
public class FlightsController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping({"/flights"})
    public String displayFlightsPage(final Model model) {
        final List<Flight> flights = flightRepository.findAll();
        model.addAttribute("myFlights", flights);
        return "flights-page";
    }
}
