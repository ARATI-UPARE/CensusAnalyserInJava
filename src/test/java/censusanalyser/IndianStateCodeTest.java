package censusanalyser;

import censusanalyser.CensusAnalyser;
import censusanalyser.IndiaStateCodeAnalyserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IndianStateCodeTest {

    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCodeCSV.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndianStateCensusData.txt";
    private static final String WRONG_CSV_FILE_DELIMITER = "./src/test/resources/IndianStateCensusInvalidDelimiter.csv";


    // TC:2.1
    @Test
    public void givenIndiaStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser indiaStateCodAnalyser = new CensusAnalyser();
            int numOfRecords = indiaStateCodAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37,numOfRecords);
            System.out.println(numOfRecords);
             } catch (IndiaStateCodeAnalyserException e) {}
    }
    //TC:2.2
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser indiaStateCodAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IndiaStateCodeAnalyserException.class);
            indiaStateCodAnalyser.loadIndiaStateCodeData(WRONG_CSV_FILE_PATH);
        } catch (IndiaStateCodeAnalyserException e) {
            Assert.assertEquals(IndiaStateCodeAnalyserException.ExceptionType.STATECODE_FILE_PROBLEM,e.type);
        }
    }
    //TC:2.3
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser indiaStateCodAnalyser = new CensusAnalyser();
            indiaStateCodAnalyser.loadIndiaStateCodeData(WRONG_CSV_FILE_TYPE);
        } catch (IndiaStateCodeAnalyserException e) {
            Assert.assertEquals(IndiaStateCodeAnalyserException.ExceptionType.INVALID_FILE_TYPE,e.type);
        }
    }
    // TC:2.4
    @Test
    public void givenIndiaCensusData_WithWrongFileDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser indiaStateCodAnalyser = new CensusAnalyser();
            indiaStateCodAnalyser.loadIndiaStateCodeData(WRONG_CSV_FILE_DELIMITER);
        } catch (IndiaStateCodeAnalyserException e) {
            Assert.assertEquals(IndiaStateCodeAnalyserException.ExceptionType.INVALID_FILE_DELIMETER,e.type);
        }
    }
}
