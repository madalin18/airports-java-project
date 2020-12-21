package ro.siit.airports.service;

import org.springframework.data.domain.Page;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.model.Dates;

import java.util.List;

public interface FlightService {

    Flight insertIntoDatabase(Flight message);

    Flight updateFlight(Flight myFlight);

    List<Flight> listAll(String keyword);

    List<Flight> listDepartureFromNowTillTomorrow(List<Flight> flights);

    List<Flight> listArrivalFromNowTillTomorrow(List<Flight> flights);

    List<Flight> listDepartureFromBetween(List<Flight> flights, Dates dates);

    List<Flight> listArrivalFromBetween(List<Flight> flights, Dates dates);

    public Page<Flight> listAll(int pageNum);
}
