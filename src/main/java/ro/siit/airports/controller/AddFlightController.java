package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.repository.FlightRepository;
import ro.siit.airports.service.FlightService;

import java.util.List;

@Controller
public class AddFlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/add")
    public String displayAddPage(final Model model) {
        final List<Airport> airports = airportRepository.findByCountry("Romania");
        model.addAttribute("myAirports", airports);
        model.addAttribute("flight", new Flight());
        return "add-flight";
    }

    @PostMapping("/flights/post-add")
    public String diplayResult(final Model model, @ModelAttribute final Flight flight,
                               @Param("keyword") String keyword) {
        flightService.insertIntoDatabase(flight);
        List<Flight> flights = flightService.listAll(keyword);
        model.addAttribute("myFlights", flights);
        model.addAttribute("keyword", keyword);

        model.addAttribute("msg", "New flight inserted");
        return "flights-page";
    }

}
