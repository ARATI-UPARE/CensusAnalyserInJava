package censusanalyser;
import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    List csvFileList = new ArrayList<IndiaCensusCSVDao>();
    Map<String, IndiaCensusCSVDao> csvFileMap = new HashMap<>();


    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        if(!csvFilePath[0].contains(".csv")){
            throw new CensusAnalyserException("Wrong File Type",CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE);
        }
        csvFileMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        csvFileList = (List) csvFileMap.values().stream().collect(Collectors.toList());
        return csvFileMap.size();
    }

    // Method to sort census Data according to Area
    public String getAreaWiseSortedData(Country country,String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country,csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> censusComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getAreaInSqKm());
        this.sortData(censusComparator);
        Collections.reverse(csvFileList);
        String sortedStateJsonData = new Gson().toJson(csvFileList);
        return sortedStateJsonData;
    }

    // Method To sort Census Data according to population density wise
    public String getPopulationDensityWiseSortedData(Country country,String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country,csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> censusComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getDensityPerSqKm());
        this.sortData(censusComparator);
        Collections.reverse(csvFileList);
        String sortedStateJsonData = new Gson().toJson(csvFileList);
        return sortedStateJsonData;
    }

    // Method To sort Census Data according to population wise
    public String getPopulationWiseSortedData(Country country,String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country,csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> censusComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getPopulation());
        this.sortData(censusComparator);
        Collections.reverse(csvFileList);
        String sortedStateJsonData = new Gson().toJson(csvFileList);
        return sortedStateJsonData;
    }
   // Method to get IndiaCensus file State in alphabetical order
    public String getStateWiseCensusCodeData(Country country,String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country,csvFilePath);
        if(csvFileList.size()==0 || csvFileList==null) {
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSVDao> censusCSVDaoComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getState());
        this.sortData(censusCSVDaoComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }
    // Method to get IndiaStateCodeCSV State in alphabetical order
    public String getStateWiseSortedStateCodeData (Country country,String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country,csvFilePath);
        if (csvFileList.size() == 0 || csvFileList == null) {
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSVDao> censusCSVDaoComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getStateCode());
        this.sortData(censusCSVDaoComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }

    // Method To sort US Data
    public  String getStateNameWiseSortedCensusData(Country country, String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country, csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSVDao> censusComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getState());
        this.sortData(censusComparator);
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    public String getStateIDWiseSortedCensusData(Country country, String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country, csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> stateCSVComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getStateID());
        this.sortData(stateCSVComparator);
        String sortedStateCodeJsonData = new Gson().toJson(csvFileList);
        return sortedStateCodeJsonData;
    }

    public String getHousingUnitsWiseSortedCensusData(Country country, String csvFilePath) throws CensusAnalyserException {
        loadCensusData(country, csvFilePath);
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> stateCSVComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.getHousingUnits());
        this.sortData(stateCSVComparator);
        Collections.reverse(csvFileList);
        String sortedStateCodeJsonData = new Gson().toJson(csvFileList);
        return sortedStateCodeJsonData;
    }
    private  void sortData(Comparator<IndiaCensusCSVDao> censusCSVComparator) {
        for (int i = 0; i < csvFileList.size() - 1; i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                IndiaCensusCSVDao census1 = (IndiaCensusCSVDao) csvFileList.get(j);
                IndiaCensusCSVDao census2 = (IndiaCensusCSVDao) csvFileList.get(j + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    csvFileList.set(j, census2);
                    csvFileList.set(j + 1, census1);
                }
            }
        }
    }

}