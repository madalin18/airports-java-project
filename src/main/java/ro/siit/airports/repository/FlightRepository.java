package ro.siit.airports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
