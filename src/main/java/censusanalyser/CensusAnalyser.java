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

public class CensusAnalyser {

    Map censusDaoMap =  new HashMap<String, IndiaCensusCSVDao>();
    List censusCSVDaoList = new ArrayList<IndiaCensusCSVDao>();

    public void checkValidCsvFile(String csvFilePath) throws CensusAnalyserException {
        if (!csvFilePath.contains(".csv")) {
            throw new CensusAnalyserException("Invalid file type", CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE);
        }
    }

    // Method For IndianCensusCSV -Builder1
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        this.checkValidCsvFile(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CSVBuilderFactory.CsvBuilder();
            Iterator<IndiaCensusCSV> indiaCensusCSVIterator = csvBuilder.getIterator(reader, IndiaCensusCSV.class);
            while (indiaCensusCSVIterator.hasNext()) {
                IndiaCensusCSVDao indiaCensusCSVDao = new IndiaCensusCSVDao (indiaCensusCSVIterator.next());
                this.censusDaoMap.put(indiaCensusCSVDao.state, indiaCensusCSVDao);
                this.censusCSVDaoList = (List) censusDaoMap.values().stream().collect(Collectors.toList());
            }
            return censusDaoMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("header!"))
                throw  new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMITER);
        }catch (CsvFileBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type);
        }
    }

    // Method To sort Census Data according to population density wise
    public String getPopulationDensityWiseSortedData(String csvFilePath) throws CensusAnalyserException {
        loadIndiaCensusData(csvFilePath);
        if (censusCSVDaoList == null || censusCSVDaoList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> censusComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.densityPerSqKm);
        this.sortData(censusComparator);
        Collections.reverse(censusCSVDaoList);
        String sortedStateJsonData = new Gson().toJson(censusCSVDaoList);
        return sortedStateJsonData;
    }

    // Method To sort Census Data according to population wise
    public String getPopulationWiseSortedData(String csvFilePath) throws CensusAnalyserException {
        loadIndiaCensusData(csvFilePath);
        if (censusCSVDaoList == null || censusCSVDaoList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        Comparator<IndiaCensusCSVDao> censusComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.population);
        this.sortData(censusComparator);
        Collections.reverse(censusCSVDaoList);
        String sortedStateJsonData = new Gson().toJson(censusCSVDaoList);
        return sortedStateJsonData;
    }
   // Method to get IndiaCensus file State in alphabetical order
    public String getStateWiseCensusCodeData(String csvFilePath) throws CensusAnalyserException {
        loadIndiaCensusData(csvFilePath);
        if(censusCSVDaoList.size()==0 || censusCSVDaoList==null) {
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSVDao> censusCSVDaoComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.state);
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

    // Method For IndianStateCodeCSV -Builder 2
    public int loadIndiaStateCodeData (String csvFilePath) throws CensusAnalyserException {
        this.checkValidCsvFile(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CSVBuilderFactory.CsvBuilder();

            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = csvBuilder.getIterator(reader, IndiaStateCodeCSV.class);
            while (stateCodeCSVIterator.hasNext()) {
                IndiaCensusCSVDao indiaCensusCSVDao = new IndiaCensusCSVDao (stateCodeCSVIterator.next());
                this.censusDaoMap.put(indiaCensusCSVDao.state, indiaCensusCSVDao);
                this.censusCSVDaoList = (List) censusDaoMap.values().stream().collect(Collectors.toList());
        }
        return censusDaoMap.size();
    } catch (IOException e) {
     throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM);
     } catch (RuntimeException e) {
     if (e.getMessage().contains("header!"))
     throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);
     throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMITER);
     } catch (CsvFileBuilderException e) {
     throw new CensusAnalyserException(e.getMessage(), e.type);
     }
     }

     // Method to get IndiaStateCodeCSV State in alphabetical order
     public String getStateWiseSortedStateCodeData (String csvFilePath) throws CensusAnalyserException {
     loadIndiaStateCodeData(csvFilePath);
     if (censusCSVDaoList.size() == 0 || censusCSVDaoList == null) {
         throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
     }
     Comparator<IndiaCensusCSVDao> censusCSVDaoComparator = Comparator.comparing(IndiaCensusCSVDao -> IndiaCensusCSVDao.stateCode);
     this.sortData(censusCSVDaoComparator);
     String sortedCensusJson = new Gson().toJson(censusCSVDaoList);
     return sortedCensusJson;
     }
}