package ro.siit.airports.service;

import org.springframework.data.jpa.repository.Query;
import ro.siit.airports.domain.Flight;

import java.util.List;

public interface FlightService {

    Flight insertIntoDatabase(Flight message);

    Flight updateFlight(Flight myFlight);

    List<Flight> listAll(String keyword);
}
