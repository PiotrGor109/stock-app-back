package Example.StockApp.Service;

import java.io.*;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadCompanyIndexDataService {


    public List<String> getChartData(String stockPrefix) throws IOException {

        File filePath = new File("src/main/resources/stockDataFiles/" + stockPrefix + ".txt");
        List<String> indexValueList = new ArrayList<>();

        if (filePath.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            StringBuilder contentOfFile = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("-") && !line.contains("Last Refreshed")) {
                    indexValueList.add(line.split("\"")[1]);
                    System.out.println(line);
                }
                if (line.contains("4. close")) {
                    indexValueList.add(line.split("\"")[3]);
                    System.out.println(line);
                }
                contentOfFile.append(line);
                contentOfFile.append(System.lineSeparator());
            }
            System.out.println(indexValueList);
        }

        return indexValueList;


    }
}
