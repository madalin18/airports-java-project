package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.FlightRepository;
import ro.siit.airports.service.FlightService;

import java.util.List;

@Controller
public class FlightsController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

//    @GetMapping({"/flights"})
//    public String displayFlightsPage(final Model model) {
//        final List<Flight> flights = flightRepository.findAll();
//        model.addAttribute("myFlights", flights);
//        return "flights-page";
//    }

    @RequestMapping("/flights")
    public String viewFlightsPage(final Model model, @Param("keyword") String keyword) {
        List<Flight> flights = flightService.listAll(keyword);
        model.addAttribute("myFlights", flights);
        model.addAttribute("keyword", keyword);

        return "flights-page";
    }

}
