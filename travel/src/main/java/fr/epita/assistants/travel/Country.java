package fr.epita.assistants.travel;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Country {
    public String countryName;
    private String countryZone;
    public Map<String, Integer> travelTimes;

    public Country(String countryName, String countryZone, String inputFilePath) throws IOException {
        this.countryName = countryName;
        this.countryZone = countryZone;
        travelTimes = initTravelTimes(inputFilePath);
    }

    public Map<String, Integer> initTravelTimes(String inputFilePath) throws IOException {
        Map<String, Integer> times = new HashMap<>();
        FileReader fileReader = new FileReader(inputFilePath);
        CSVReader csvReader = new CSVReader(fileReader, ',');

        List<String[]> lines = csvReader.readAll();
        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i);
            if (line[0].equals(countryName))
                times.put(line[1], Integer.parseInt(line[2]));
            else if (line[1].equals(countryName))
                times.put(line[0], Integer.parseInt(line[2]));
        }

        csvReader.close();
        return times;
    }
}
