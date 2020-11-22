package ro.siit.airports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.model.Dates;
import ro.siit.airports.repository.FlightRepository;
import ro.siit.airports.service.FlightService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            List<Flight> flights = flightRepository.search(keyword);
            if (flights.isEmpty()) {
                flights = flightRepository.searchAiline(keyword);
                return flights;
            } else {
                return flights;
            }
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

    @Override
    public List<Flight> listDepartureFromNowTillTomorrow(List<Flight> flights) {
        final List<Flight> filteredflights = new ArrayList<>();
        for (Flight f: flights) {
            if (f.getDeparture().isAfter(LocalDateTime.now()) &&
                    f.getDeparture().isBefore(LocalDateTime.now().plusDays(1))) {
                filteredflights.add(f);
            }
        }
        return filteredflights;
    }
    @Override
    public List<Flight> listArrivalFromNowTillTomorrow(List<Flight> flights) {
        final List<Flight> filteredflights = new ArrayList<>();
        for (Flight f: flights) {
            if (f.getArrival().isAfter(LocalDateTime.now()) &&
                    f.getArrival().isBefore(LocalDateTime.now().plusDays(1))) {
                filteredflights.add(f);
            }
        }
        return filteredflights;
    }

    @Override
    public List<Flight> listDepartureFromBetween(List<Flight> flights, Dates dates) {
        final List<Flight> filteredFlights = new ArrayList<>();
        for (Flight f: flights) {
            if (f.getDeparture().isAfter(LocalDateTime.parse(dates.getStartDateString())) &&
                    f.getDeparture().isBefore(LocalDateTime.parse(dates.getEndDateString()))){
                filteredFlights.add(f);
            }
        }
        return filteredFlights;
    }

    @Override
    public List<Flight> listArrivalFromBetween(List<Flight> flights, Dates dates) {
        final List<Flight> filteredFlights = new ArrayList<>();
        for (Flight f: flights) {
            if (f.getArrival().isAfter(LocalDateTime.parse(dates.getStartDateString())) &&
                    f.getArrival().isBefore(LocalDateTime.parse(dates.getEndDateString()))){
                filteredFlights.add(f);
            }
        }
        return filteredFlights;
    }

}
