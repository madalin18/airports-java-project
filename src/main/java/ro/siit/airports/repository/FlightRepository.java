package ro.siit.airports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.domain.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByFlightNo(String flightNo);

    List<Flight> findByDepartureAirportOrArrivalAirport(Airport departure, Airport arrival);

    Optional<ArrayList<Flight>> findByDepartureAirport(Airport a);

    Optional<ArrayList<Flight>> findByArrivalAirport(Airport a);
}
