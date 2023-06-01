package Example.StockApp.Service;

import Example.StockApp.Repository.CompanyIndexRepository;
import Example.StockApp.model.CompanyIndex;
import org.springframework.stereotype.Service;

@Service
public class CompanyIndexService {


    private final CompanyIndexRepository companyIndexRepository;

    public CompanyIndexService(CompanyIndexRepository companyIndexRepository) {
        this.companyIndexRepository = companyIndexRepository;
    }





}
