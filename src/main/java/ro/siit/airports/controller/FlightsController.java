package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.AirlineRepository;
import ro.siit.airports.repository.FlightRepository;
import ro.siit.airports.service.FlightService;

import java.util.List;

@Controller
public class FlightsController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirlineRepository airlineRepository;

//    @GetMapping({"/flights"})
//    public String displayFlightsPage(final Model model) {
//        final List<Flight> flights = flightRepository.findAll();
//        model.addAttribute("myFlights", flights);
//        return "flights-page";
//    }

    @RequestMapping("/flights")
    public ModelAndView viewFlightsPage(@Param("keyword") String keyword) {
        final ModelAndView mav = new ModelAndView("flights-page");
        List<Flight> flights = flightService.listAll(keyword);
        mav.addObject("myFlights", flights);
        mav.addObject("keyword", keyword);
        return mav;
    }

}
