package censusanalyser;

import com.bl.csvbuilder.CsvFileBuilderException;
import com.bl.csvbuilder.IcsvBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    Map censusDaoMap =  new HashMap<String, IndiaCensusCSVDao>();
    List censusCSVDaoList = new ArrayList<IndiaCensusCSVDao>();

    List csvFileList =new ArrayList<IndiaCensusCSVDao>();
    Map<String, IndiaCensusCSVDao> csvFileMap = new HashMap<>();

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        csvFileMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        csvFileList = (List) csvFileMap.values().stream().collect(Collectors.toList());
        return csvFileMap.size();
    }

    // Method to sort census Data according to Area
    public String getAreaWiseSortedData(Country country,String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country,csvFilePath);
        if (censusCSVDaoList == null || censusCSVDaoList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> censusComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getAreaInSqKm());
        this.sortData(censusComparator);
        Collections.reverse(censusCSVDaoList);
        String sortedStateJsonData = new Gson().toJson(censusCSVDaoList);
        return sortedStateJsonData;
    }

    // Method To sort Census Data according to population density wise
    public String getPopulationDensityWiseSortedData(Country country,String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country,csvFilePath);
        if (censusCSVDaoList == null || censusCSVDaoList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> censusComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getDensityPerSqKm());
        this.sortData(censusComparator);
        Collections.reverse(censusCSVDaoList);
        String sortedStateJsonData = new Gson().toJson(censusCSVDaoList);
        return sortedStateJsonData;
    }

    // Method To sort Census Data according to population wise
    public String getPopulationWiseSortedData(Country country,String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country,csvFilePath);
        if (censusCSVDaoList == null || censusCSVDaoList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> censusComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getPopulation());
        this.sortData(censusComparator);
        Collections.reverse(censusCSVDaoList);
        String sortedStateJsonData = new Gson().toJson(censusCSVDaoList);
        return sortedStateJsonData;
    }
   // Method to get IndiaCensus file State in alphabetical order
    public String getStateWiseCensusCodeData(Country country,String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country,csvFilePath);
        if(censusCSVDaoList.size()==0 || censusCSVDaoList==null) {
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSVDao> censusCSVDaoComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getState());
        this.sortData(censusCSVDaoComparator);
        String sortedCensusJson = new Gson().toJson(censusCSVDaoList);
        return sortedCensusJson;
    }

    private  void sortData(Comparator<IndiaCensusCSVDao> censusCSVComparator) {
        for (int i = 0; i < censusCSVDaoList.size() - 1; i++) {
            for (int j = 0; j < censusCSVDaoList.size() - i - 1; j++) {
                IndiaCensusCSVDao census1 = (IndiaCensusCSVDao)censusCSVDaoList.get(j);
                IndiaCensusCSVDao census2 = (IndiaCensusCSVDao)censusCSVDaoList.get(j + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    censusCSVDaoList.set(j, census2);
                    censusCSVDaoList.set(j + 1, census1);
                }
            }
        }
    }


     // Method to get IndiaStateCodeCSV State in alphabetical order
     public String getStateWiseSortedStateCodeData (Country country,String csvFilePath) throws CensusAnalyserException {
         loadCensusData(country,csvFilePath);
         if (censusCSVDaoList.size() == 0 || censusCSVDaoList == null) {
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
         }
         Comparator<IndiaCensusCSVDao> censusCSVDaoComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getStateCode());
         this.sortData(censusCSVDaoComparator);
         String sortedCensusJson = new Gson().toJson(censusCSVDaoList);
         return sortedCensusJson;
     }
}