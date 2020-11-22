package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.model.Dates;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.repository.FlightRepository;
import ro.siit.airports.service.FlightService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SelectedController {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;
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

        final List<Flight> departureFlights = optionalAirport.map(f -> flightRepository.findByDepartureAirport(airport).orElse(new ArrayList<>())).get();
        final List<Flight> todayDepartureFlights = flightService.listDepartureFromNowTillTomorrow(departureFlights);

        final List<Flight> arrivalFlights = optionalAirport.map(a -> flightRepository.findByArrivalAirport(airport).orElse(new ArrayList<>())).get();
        final List<Flight> todayArrivalFlights = flightService.listArrivalFromNowTillTomorrow(arrivalFlights);

        mav.addObject("dates", new Dates());
        mav.addObject("airport", airport);
        mav.addObject("departureFlights", todayDepartureFlights);
        mav.addObject("arrivalFlights", todayArrivalFlights);
        return mav;
    }

    @PostMapping("/filteredselected/{airportId}")
    public ModelAndView displayAirportByCityAndDate(@PathVariable("airportId") final Long airportId,
                                                    final Dates dates) {
        final ModelAndView mav = new ModelAndView("selected");
        final Optional<Airport> optionalAirport = airportRepository.findById(airportId);
        final Airport airport = optionalAirport.get();

        final List<Flight> departureFlights = optionalAirport.map(f -> flightRepository.findByDepartureAirport(airport).orElse(new ArrayList<>())).get();
        final List<Flight> todayDepartureFlights = flightService.listDepartureFromBetween(departureFlights, dates);

        final List<Flight> arrivalFlights = optionalAirport.map(a -> flightRepository.findByArrivalAirport(airport).orElse(new ArrayList<>())).get();
        final List<Flight> todayArrivalFlights = flightService.listArrivalFromBetween(arrivalFlights, dates);


        mav.addObject("airport", airport);
        mav.addObject("departureFlights", todayDepartureFlights);
        mav.addObject("arrivalFlights", todayArrivalFlights);
        return mav;
    }

}
