package Example.StockApp.Repository;


import Example.StockApp.model.CompanyIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface CompanyIndexRepository extends JpaRepository<CompanyIndex, Integer> {


    Page<CompanyIndex> findAll(Pageable page);

    //SEARCHING COMPANIES BY NAME
    //http://localhost:8080/companyIndexes/search/findByCompanyNameContaining?company_name=meta
    Page<CompanyIndex> findByCompanyNameContaining(@Param("company_name") String name, Pageable pageable);
}







