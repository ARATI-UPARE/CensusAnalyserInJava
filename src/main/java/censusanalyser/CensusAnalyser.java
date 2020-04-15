package censusanalyser;

import com.bl.csvbuilder.CsvFileBuilderException;
import com.bl.csvbuilder.IcsvBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class CensusAnalyser {

    List<IndiaCensusCSV> censusCsvList = null;
    List<IndiaStateCodeCSV> indiaStateCodeCSVList = null;


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
            censusCsvList = icsvBuilder.getList(reader,IndiaCensusCSV.class);
            return censusCsvList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("header!"))
                throw  new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMITER);
        }catch (CsvFileBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }

    }

    // Method to get IndiaCensusData State in alphabetical order
    public String getStateWiseCensusCodeData(String csvFilePath) throws CensusAnalyserException {
        loadIndiaCensusData(csvFilePath);
        if(censusCsvList.size()==0 || censusCsvList==null) {
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusCSVComparator = Comparator.comparing(census -> census.state);
        this.sortCensusData(censusCSVComparator);
        String sortedCensusJson = new Gson().toJson(censusCsvList);
        return sortedCensusJson;
    }

    // Sorting Method for IndiaCensusData
    private void sortCensusData(Comparator<IndiaCensusCSV> censusCSVComparator) {
        for (int outLoop = 0; outLoop < censusCsvList.size()-1; outLoop++){
            for ( int innerLoop=0; innerLoop < censusCsvList.size()-outLoop-1; innerLoop++){
                IndiaCensusCSV census1 = censusCsvList.get(innerLoop);
                IndiaCensusCSV census2 = censusCsvList.get(innerLoop+1);
                if (censusCSVComparator.compare(census1,census2)>0){
                    censusCsvList.set(innerLoop,census2);
                    censusCsvList.set(innerLoop+1,census1);
                }
            }
        }
    }

    // Method For IndianStateCodeCSV -Builder 2
    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        this.checkValidCsvFile(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder icsvBuilder = CSVBuilderFactory.CsvBuilder();
            indiaStateCodeCSVList = icsvBuilder.getList(reader,IndiaStateCodeCSV.class);
            return indiaStateCodeCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("header!")) {
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);
            }
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMITER);
        } catch (CsvFileBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }
    // Method to get IndiaStateCodeCSV State in alphabetical order
    public String getStateWiseSortedStateCodeData(String csvFilePath) throws CensusAnalyserException {
        loadIndiaStateCodeData(csvFilePath);
        if(indiaStateCodeCSVList.size()==0 || indiaStateCodeCSVList==null)
            throw new CensusAnalyserException("No Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndiaStateCodeCSV> indiaStateCodeCSVComparator = Comparator.comparing(census -> census.stateCode);
        this.sortStateCode(indiaStateCodeCSVComparator);
        String sortedCensusJson = new Gson().toJson(indiaStateCodeCSVList);
        return sortedCensusJson;
    }

    // Sorting Method for StateCodeCSVData
    private void sortStateCode(Comparator<IndiaStateCodeCSV> indiaStateCodeCSVComparator) {
        for (int outLoop = 0; outLoop < indiaStateCodeCSVList.size()-1; outLoop++){
            for ( int innerLoop=0; innerLoop < indiaStateCodeCSVList.size()-outLoop-1; innerLoop++){
                IndiaStateCodeCSV stateCodeCSV1 = indiaStateCodeCSVList.get(innerLoop);
                IndiaStateCodeCSV stateCodeCSV2 = indiaStateCodeCSVList.get(innerLoop+1);
                if (indiaStateCodeCSVComparator.compare(stateCodeCSV1,stateCodeCSV2)>0){
                    indiaStateCodeCSVList.set(innerLoop,stateCodeCSV2);
                    indiaStateCodeCSVList.set(innerLoop+1,stateCodeCSV1);
                }
            }
        }
    }

}