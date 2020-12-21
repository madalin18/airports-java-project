package ro.siit.airports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Airline;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.domain.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<ArrayList<Flight>> findByDepartureAirport(Airport a);

    Optional<ArrayList<Flight>> findByArrivalAirport(Airport a);

    @Query("select f from Flight f inner join f.departureAirport a " +
            "where a.country LIKE %?1%" +
            "or a.name LIKE %?1%" +
            "or a.city LIKE %?1%"+
            "or f.flightNo LIKE %?1%")
    public List<Flight> search(String keyword);

    @Query("select f from Flight f inner join f.airline a where a.name LIKE %?1%")
    public List<Flight> searchAiline(String keyword);

    @Query("select f from Flight f where f.departure >= ?1 ")
    List<Flight> findByDeparture(LocalDateTime departureDate);

    Optional<List<Flight>> findByAirline(Airline airline);

}
