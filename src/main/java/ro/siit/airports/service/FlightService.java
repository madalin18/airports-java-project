package ro.siit.airports.service;

import ro.siit.airports.domain.Flight;

public interface FlightService {

    Flight insertIntoDatabase(Flight message);
}
