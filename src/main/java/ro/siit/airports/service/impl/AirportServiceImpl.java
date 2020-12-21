package ro.siit.airports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.service.AirportService;


@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public Page<Airport> listAll(int pageNum, String sortField, String sortDir) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        return airportRepository.findAll(pageable);
    }

    @Override
    public Page<Airport> listRomania(int pageNum) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return airportRepository.findByCountry("Romania", pageable);
    }

}
