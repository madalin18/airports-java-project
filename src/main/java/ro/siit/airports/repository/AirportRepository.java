package ro.siit.airports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Airport;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findByCountry(String country);

    List<Airport> findByCity(String city);

    @Query("SELECT a FROM Airport a WHERE CONCAT(a.name, ' ', a.country, ' ', a.city, ' ', a.iata) LIKE %?1%")
    public List<Airport> search(String keyword);
}
