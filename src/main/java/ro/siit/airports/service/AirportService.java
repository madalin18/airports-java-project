package ro.siit.airports.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ro.siit.airports.domain.Airport;

public interface AirportService {

    public Page<Airport> listAll(int pageNum, String sortField, String sortDir);
}
