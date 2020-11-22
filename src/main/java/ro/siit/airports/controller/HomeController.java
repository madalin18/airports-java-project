package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.service.AirportService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping({"/", "/index", "/home"})
    public String displayHomePage(final Model model) {
        final List<Airport> airports = airportRepository.findByCountry("Romania");
        model.addAttribute("myAirports", airports);
        model.addAttribute("msg", "Romanian Airports");
        return "home-page";
    }

    @GetMapping({"/index/all/page/{pageNum}", "/home/all/page/{pageNum}"})
    public String viewPage(final Model model,
                           @PathVariable(name = "pageNum") int pageNum,
                           @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir) {

        Page<Airport> page = airportService.listAll(pageNum, sortField, sortDir);

        final List<Airport> airports = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("myAirports", airports);
        model.addAttribute("msg", "International Airports");
        return "all-page";
    }

    @RequestMapping({"/index/all", "/home/all"})
    public String viewHomePage(Model model) {
        return viewPage(model, 1, "id", "asc");
    }
}
