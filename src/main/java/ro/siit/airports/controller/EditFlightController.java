package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.repository.FlightRepository;
import ro.siit.airports.service.FlightService;

import java.util.List;
import java.util.Optional;

@Controller
public class EditFlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping({"/flights/{flightId}"})
    public String displayEditFlight(final Model model, @PathVariable("flightId") final Long flightId) {
        final List<Airport> airports = airportRepository.findByCountry("Romania");
        final Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        Flight flight = optionalFlight.get();

        model.addAttribute("myFlight", flight);
        model.addAttribute("myAirports", airports);
        return "edit-flight";
    }

    @PostMapping("/flights")
    public String diplayResult(final Model model, @ModelAttribute final Flight myFlight) {
//        flightService.updateFlight(myFlight);
        flightService.insertIntoDatabase(myFlight);
        final List<Flight> flights = flightRepository.findAll();
        model.addAttribute("myFlights", flights);
        model.addAttribute("msg", "Flight updated to database");
        return "flights-page";
    }
}
