package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {

    private static final String CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndianStateCensusData.txt";
    private static final String WRONG_CSV_FILE_DELIMITER = "./src/test/resources/IndianStateCensusInvalidDelimiter.csv";
    private static final String WRONG_CSV_FILE_HEADER = "./src/test/resources/IndianStateCensusInvalidHeader.csv";

    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_TYPE = "./src/test/resources/IndianStateCodeData.txt";
    private static final String WRONG_STATE_CODE_CSV_FILE_DELIMITER = "./src/test/resources/IndiaStateCodeInvalidDelimiter.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_HEADER = "./src/test/resources/IndianStateCodeInvalidHeader.csv";

    CensusAnalyser censusAnalyser = new CensusAnalyser();

    // TC:1.1
   @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            int numOfRecords = censusAnalyser.loadIndiaCensusData(CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //TC:1.2
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    //TC:1.3
    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE,e.type);
        }
    }
    // TC:1.4
    @Test
    public void givenIndiaCensusData_WithWrongFileDelimiter_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMITER,e.type);
        }
    }
    // TC:1.5
    @Test
    public void givenIndiaCensusData_WithWrongFileHeader_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER,e.type);
        }
    }

    // TC:2.1
    @Test
    public void givenIndiaStateCodeCSVFileReturnsCorrectRecords() {
        try {
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37,numOfRecords);
        } catch (CensusAnalyserException e) {}
    }
    //TC:2.2
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFile_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM,e.type);
        }
    }
    //TC:2.3
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFileType_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE,e.type);
        }
    }
    // TC:2.4
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFileDelimiter_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMITER,e.type);
        }
    }
    // TC:2.5
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFileHeader_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER,e.type);
        }
    }

    // UC:3
    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataStartState() {
        try {
            censusAnalyser.loadIndiaCensusData(CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseCensusCodeData(CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh",censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataEndState() {
        try {
            censusAnalyser.loadIndiaCensusData(CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseCensusCodeData(CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
            Assert.assertEquals("West Bengal",censusCsv[28].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }
}
