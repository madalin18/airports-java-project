package ro.siit.airports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.FlightRepository;
import ro.siit.airports.service.FlightService;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight insertIntoDatabase(final Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> listAll(String keyword) {
        if (keyword != null) {
            return flightRepository.search(keyword);
        }
        return flightRepository.findAll();
    }

    @Override
    public Flight updateFlight(final Flight myFlight) {
        final Flight flight = new Flight();
        flight.setId(myFlight.getId());
        flight.setFlightNo(myFlight.getFlightNo());
        flight.setAirline(myFlight.getAirline());
        flight.setArrival(myFlight.getArrival());
        flight.setArrivalAirport(myFlight.getArrivalAirport());
        flight.setDeparture(myFlight.getDeparture());
        flight.setDepartureAirport(myFlight.getDepartureAirport());
        final Flight updatedFlight = flightRepository.save(flight);
        return updatedFlight;
    }
}
