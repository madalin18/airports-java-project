package ro.siit.airports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
}
