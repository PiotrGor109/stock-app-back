package Example.StockApp;

import Example.StockApp.Repository.CompanyIndexRepository;
import Example.StockApp.Service.GetDataFromApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class StockAppApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(StockAppApplication.class, args);
	}

}
