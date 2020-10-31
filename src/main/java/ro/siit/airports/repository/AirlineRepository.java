package ro.siit.airports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.airports.model.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
