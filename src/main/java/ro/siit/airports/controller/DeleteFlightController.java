package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.repository.FlightRepository;
import ro.siit.airports.service.FlightService;

import java.util.Optional;

@Controller
public class DeleteFlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/delete/{flightId}")
    public String deleteUser(@PathVariable("flightId") final Long flightId, final Model model) {
        final Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + flightId));
        flight.setAirline(null);
        flight.setDepartureAirport(null);
        flight.setArrivalAirport(null);
        flightRepository.delete(flight);
        model.addAttribute("msg", "Flight deleted");
        return "delete";
    }
}
