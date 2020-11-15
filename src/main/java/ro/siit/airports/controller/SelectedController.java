package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.repository.FlightRepository;

import java.util.List;

@Controller
public class SelectedController {

    private AirportRepository airportRepository;
//
//    @GetMapping("/selected")
//    public String displaySelectedPage() {
//        return "selected";
//    }

    @GetMapping("/selected")
    public ModelAndView displayAirportByCity() {
        final ModelAndView mav = new ModelAndView("selected");
        final List<Airport> airports = airportRepository.findByCity("Romania");
        final String result = airports.stream().findFirst().map(a -> a.getName()).orElse("nu avem");
        mav.addObject("airport", result);
        return mav;
    }

//    @GetMapping("/selected/{city}")
//    public ModelAndView displayAirportByIata(@PathVariable("city") final String city) {
//        final ModelAndView mav = new ModelAndView("selected");
//        final List<Airport> airports = airportRepository.findByCity(city);
//        final String airport = airports.stream()
//                .findFirst()
//                .map(a -> a.getName() + " " + a.getIata()
//                        + "\n" + a.getCity() + " " + a.getCountry())
//                .orElse("no data");
//        mav.addObject("airport", airport);
//        return mav;
//    }

//    @Autowired
//    private FlightRepository flightRepository;
//
//    @GetMapping("/flights/{flightNo}")
//    public ModelAndView displayFlightByNumber(@PathVariable("flightNo") final String flightNo) {
//        final ModelAndView mav = new ModelAndView("selected");
//        final List<Flight> flights = flightRepository.findByFlightNo(flightNo);
//        final String flight = flights.stream()
//                .findFirst()
//                .map(f -> f.getDeparture() + " " + f.getArrival()
//                        + "\n" + f.getFlightNo())
//                .orElse("no data");
//        mav.addObject("airport", flight);
//        return mav;
//    }
}
