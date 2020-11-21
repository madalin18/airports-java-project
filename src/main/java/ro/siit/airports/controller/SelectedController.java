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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SelectedController {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;
//
//    @GetMapping("/selected")
//    public String displaySelectedPage() {
//        return "selected";
//    }

    @GetMapping("/selected/{airportId}")
    public ModelAndView displayAirportByCity(@PathVariable("airportId") final Long airportId) {
        final ModelAndView mav = new ModelAndView("selected");
        final Optional<Airport> optionalAirport = airportRepository.findById(airportId);
        final Airport airport = optionalAirport.get();

        // Optional<T> = map => Optional<Optional<T>>
        // Optional<T> = flatMap => Optional<T>

        final List<Flight> departureFlights = optionalAirport.map(a -> flightRepository.findByDepartureAirport(airport).orElse(new ArrayList<>())).get();
        final List<Flight> arrivalFlights = optionalAirport.map(a -> flightRepository.findByArrivalAirport(airport).orElse(new ArrayList<>())).get();

        mav.addObject("airport", airport);
        mav.addObject("departureFlights", departureFlights);
        mav.addObject("arrivalFlights", arrivalFlights);
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
