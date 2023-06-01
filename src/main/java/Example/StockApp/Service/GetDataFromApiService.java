package Example.StockApp.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.File;

import Example.StockApp.Repository.CompanyIndexRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class GetDataFromApiService {

    private final CompanyIndexRepository companyIndexRepository;

    public GetDataFromApiService(final CompanyIndexRepository companyIndexRepository) {
        this.companyIndexRepository = companyIndexRepository;
    }


    @PostConstruct
    public void createFileWithStockData() throws IOException, InterruptedException {


        for (int i = 0; i < companyIndexRepository.findAll().size(); i++) {
            String stockPrefix = companyIndexRepository.findAll().get(i).getStockPrefix();

            //File dataFile = new File("src/main/resources/stockDataFiles/StockDataFile.txt");
            File dataFile = new File("src/main/resources/stockDataFiles/" + stockPrefix + ".txt");
            String lastModificationDate = "init";
            boolean fileExists = false;

            if (dataFile.exists()) {
                fileExists = true;
                FileTime lastModificationTime = (FileTime) Files.getAttribute(dataFile.toPath(), "lastModifiedTime");
                lastModificationDate = lastModificationTime.toString().substring(0, 10);
                System.out.println("FILE EXISTS: " + lastModificationDate);


            } else {
                dataFile.createNewFile();
                System.out.println("FILE NOT EXISTS, CREATING NEW FILE: " + dataFile.toString());
            }

            LocalDate dateObj = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String todayDate = dateObj.format(formatter);


            if ((!lastModificationDate.equals(todayDate) && fileExists == true) || (fileExists == false)) {
                System.out.println("DOWNLOADING STOCK DATA !!!");
                System.out.println("https://alpha-vantage.p.rapidapi.com/query?function=TIME_SERIES_DAILY&symbol=" + stockPrefix + "&outputsize=compact&datatype=json");

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?function=TIME_SERIES_DAILY&symbol=" + stockPrefix + "&outputsize=compact&datatype=json"))
                        .header("content-type", "application/octet-stream")
                        .header("X-RapidAPI-Key", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
                        .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                String responseBodyText = response.body();

                FileWriter myWriter = new FileWriter(dataFile);
                System.out.println("SAVE STOCK DATA OF: " + stockPrefix + " to file: " + dataFile);
                myWriter.write(responseBodyText);
                myWriter.close();
            } else {
                System.out.println("DATA ARE ACTUAL. NOTHING TO DO");
            }
        }
    }
}
