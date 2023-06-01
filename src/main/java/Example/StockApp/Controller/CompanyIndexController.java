package Example.StockApp.Controller;


import Example.StockApp.Repository.CompanyIndexRepository;
import Example.StockApp.Service.CompanyIndexService;
import Example.StockApp.Service.ReadCompanyIndexDataService;
import Example.StockApp.model.CompanyIndex;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/company-indexes")
public class CompanyIndexController {


    private final CompanyIndexService companyIndexService;
    private final ReadCompanyIndexDataService readCompanyIndexDataService;
    private final CompanyIndexRepository companyIndexRepository;



    public CompanyIndexController(CompanyIndexService companyIndexService, ReadCompanyIndexDataService readCompanyIndexDataService, CompanyIndexRepository companyIndexRepository) {
        this.companyIndexService = companyIndexService;
        this.readCompanyIndexDataService = readCompanyIndexDataService;
        this.companyIndexRepository = companyIndexRepository;
    }


    @GetMapping("/details/{id}")
    public ResponseEntity<List<String>> readCompanyIndexDetails(@PathVariable int id) throws IOException {
        CompanyIndex companyIndex = companyIndexRepository.findById(id).orElseThrow();
        readCompanyIndexDataService.getChartData(companyIndex.getStockPrefix());
        return new ResponseEntity<List<String>>(readCompanyIndexDataService.getChartData(companyIndex.getStockPrefix()), HttpStatus.OK);
    }

}
