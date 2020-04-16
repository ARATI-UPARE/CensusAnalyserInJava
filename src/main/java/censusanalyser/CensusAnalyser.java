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

    Map<String, IndiaCensusCSVDAO> censusMap;
    List<IndiaCensusCSVDAO> CensusList;

    Map<String, IndiaCensusCSVDAO> stateCodeCSVMap;
    List<IndiaCensusCSVDAO> stateCodeCSVList;

    public CensusAnalyser() {
        censusMap = new HashMap<>();
        stateCodeCSVMap=new HashMap<>();
    }
    // Method To Check Valid Find
    public void checkValidCsvFile(String csvFilePath) throws CensusAnalyserException {
        if (!csvFilePath.contains(".csv")) {
            throw new CensusAnalyserException("Invalid file type", CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE);
        }
    }
    // Method For IndianCensusCSV -Builder1

       public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        this.checkValidCsvFile(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder icsvBuilder = CSVBuilderFactory.CsvBuilder();
            Iterator<IndiaCensusCSV> censusCsvIterator = icsvBuilder.getIterator(reader,IndiaCensusCSV.class);
            while (censusCsvIterator.hasNext()) {
                IndiaCensusCSVDAO indiaCensusCSVDAO = new IndiaCensusCSVDAO(censusCsvIterator.next());
                this.censusMap.put(IndiaCensusCSVDAO.state,indiaCensusCSVDAO);
                this.CensusList = censusMap.values().stream().collect(Collectors.toList());
            }
            return censusMap.size();
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

    // Method to get IndiaCensusData State in alphabetical order
    public String getStateWiseCensusCodeData(String csvFilePath) throws CensusAnalyserException {
        loadIndiaCensusData(csvFilePath);
        if(CensusList.size()==0 || CensusList==null) {
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.state);
        this.sortCensusData(censusCSVComparator);
        String sortedCensusJson = new Gson().toJson(CensusList);
        return sortedCensusJson;
    }

    // Method to Get Population Wise Sort Census Data
    public String getPopulationWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException{
        loadIndiaCensusData(csvFilePath);
        if(CensusList.size()==0 || CensusList==null)
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndiaCensusCSVDAO> indiaCensusDaoComparator = Comparator.comparing(IndiaCensusCSVDAO -> IndiaCensusCSVDAO.population);
        this.sortCensusData(indiaCensusDaoComparator);
        Collections.reverse(CensusList);
        String sortedCensusJson = new Gson().toJson(CensusList);
        return sortedCensusJson;
    }
    // Sorting Method for IndiaCensusData
    private void sortCensusData(Comparator<IndiaCensusCSVDAO> censusCSVComparator) {
        for (int outLoop = 0; outLoop < CensusList.size()-1; outLoop++){
            for ( int innerLoop=0; innerLoop < CensusList.size()-outLoop-1; innerLoop++){
                IndiaCensusCSVDAO census1 = CensusList.get(innerLoop);
                IndiaCensusCSVDAO census2 = CensusList.get(innerLoop+1);
                if (censusCSVComparator.compare(census1,census2)>0){
                    CensusList.set(innerLoop,census2);
                    CensusList.set(innerLoop+1,census1);
                }
            }
        }
    }

    // Method For IndianStateCodeCSV -Builder 2
    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        this.checkValidCsvFile(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder icsvBuilder = CSVBuilderFactory.CsvBuilder();
            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = icsvBuilder.getIterator(reader,IndiaStateCodeCSV.class);
            while (stateCodeCSVIterator.hasNext()) {
                IndiaCensusCSVDAO stateCodeCSV = new IndiaCensusCSVDAO(stateCodeCSVIterator.next());
                this.stateCodeCSVMap.put(IndiaCensusCSVDAO.stateCode,stateCodeCSV);
                this.stateCodeCSVList = stateCodeCSVMap.values().stream().collect(Collectors.toList());
            }
            return stateCodeCSVMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("header!")) {
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);
            }
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMITER);
        } catch (CsvFileBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type);
        }
    }
    // Method to get IndiaCensusCSVDAO State in alphabetical order
    public String getStateWiseSortedStateCodeData(String csvFilePath) throws CensusAnalyserException {
        loadIndiaStateCodeData(csvFilePath);
        if(stateCodeCSVList.size()==0 || stateCodeCSVList==null)
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndiaCensusCSVDAO> IndiaCensusCSVDAOComparator = Comparator.comparing(census -> census.stateCode);
        this.sortStateCode(IndiaCensusCSVDAOComparator);
        String sortedCensusJson = new Gson().toJson(stateCodeCSVList);
        return sortedCensusJson;
    }

    // Sorting Method for StateCodeCSVData
    private void sortStateCode(Comparator<IndiaCensusCSVDAO> IndiaCensusCSVDAOComparator) {
        for (int outLoop = 0; outLoop < stateCodeCSVList.size()-1; outLoop++){
            for ( int innerLoop=0; innerLoop < stateCodeCSVList.size()-outLoop-1; innerLoop++){
                IndiaCensusCSVDAO stateCodeCSV1 = stateCodeCSVList.get(innerLoop);
                IndiaCensusCSVDAO stateCodeCSV2 = stateCodeCSVList.get(innerLoop+1);
                if (IndiaCensusCSVDAOComparator.compare(stateCodeCSV1,stateCodeCSV2)>0){
                    stateCodeCSVList.set(innerLoop,stateCodeCSV2);
                    stateCodeCSVList.set(innerLoop+1,stateCodeCSV1);
                }
            }
        }
    }

}