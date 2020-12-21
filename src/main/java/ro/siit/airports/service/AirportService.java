package ro.siit.airports.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ro.siit.airports.domain.Airport;

import java.util.List;

public interface AirportService {

    public Page<Airport> listAll(int pageNum, String sortField, String sortDir);

    public Page<Airport> listRomania(int pageNum, String sortField, String sortDir);

//    public List<Airport> listAll(String keyword);
}
