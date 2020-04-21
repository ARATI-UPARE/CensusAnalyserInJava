package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import static censusanalyser.Country.INDIA;
import static censusanalyser.Country.US;

public class CensusAnalyserTest {
    // Constant for IndiaCensusData
    private static final String CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndianStateCensusData.txt";
    private static final String WRONG_CSV_FILE_DELIMITER = "./src/test/resources/IndianStateCensusInvalidDelimiter.csv";
    private static final String WRONG_CSV_FILE_HEADER = "./src/test/resources/IndianStateCensusInvalidHeader.csv";

    // Constant for IndiaStateCode
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_TYPE = "./src/test/resources/IndianStateCodeData.txt";
    private static final String WRONG_STATE_CODE_CSV_FILE_DELIMITER = "./src/test/resources/IndiaStateCodeInvalidDelimiter.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_HEADER = "./src/test/resources/IndianStateCodeInvalidHeader.csv";

    // Constant for US Census Data
    private static final String US_CENSUS_CSV_FILE_PATH = "src/test/resources/USCensusFile.csv";
    CensusAnalyser censusAnalyser = new CensusAnalyser();

    // TC:1.1
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            int numOfRecords = censusAnalyser.loadCensusData(INDIA, CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //TC:1.2
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    //TC:1.3
    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE, e.type);
        }
    }

    // TC:1.4
    @Test
    public void givenIndiaCensusData_WithWrongFileDelimiter_ShouldThrowException() {
        try {
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMITER, e.type);
        }
    }

    // TC:1.5
    @Test
    public void givenIndiaCensusData_WithWrongFileHeader_ShouldThrowException() {
        try {
            censusAnalyser.loadCensusData(INDIA, WRONG_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER, e.type);
        }
    }

    // TC:2.1
    @Test
    public void givenIndiaStateCodeCSVFileReturnsCorrectRecords() {
        try {
            int numOfRecords = censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    //TC:2.2
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFile_ShouldThrowException() {
        try {
            censusAnalyser.loadCensusData(INDIA, WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    //TC:2.3
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFileType_ShouldThrowException() {
        try {
            censusAnalyser.loadCensusData(INDIA, WRONG_STATE_CODE_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE, e.type);
        }
    }

    // TC:2.4
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFileDelimiter_ShouldThrowException() {
        try {
            censusAnalyser.loadCensusData(INDIA, WRONG_STATE_CODE_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMITER, e.type);
        }
    }

    // TC:2.5
    @Test
    public void givenIndiaStateCodeCSVData_WithWrongFileHeader_ShouldThrowException() {
        try {
            censusAnalyser.loadCensusData(INDIA, WRONG_STATE_CODE_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER, e.type);
        }
    }

    // UC:3.1
    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataStartState() {
        try {
            censusAnalyser.loadCensusData(INDIA, CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseCensusCodeData(INDIA, CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }

    // UC:3.2
    @Test
    public void givenIndiaCensusData_whenSorted_shouldReturnSortedDataEndState() {
        try {
            censusAnalyser.loadCensusData(INDIA, CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseCensusCodeData(INDIA, CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("West Bengal", censusCsv[28].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    // UC:4.1
    @Test
    public void givenIndiaStateCode_whenSorted_shouldReturnSortedDataStartCode() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedStateCodeData(INDIA, INDIA_STATE_CODE_CSV_FILE_PATH);
            IndiaStateCodeCSV[] codeCsv = new Gson().fromJson(sortedCensusData, IndiaStateCodeCSV[].class);
            System.out.println(codeCsv[0]);
            Assert.assertEquals("AD", codeCsv[0].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    // UC:4.2
    @Test
    public void givenIndiaStateCode_whenSorted_shouldReturnSortedDataEndCode() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedStateCodeData = censusAnalyser.getStateWiseSortedStateCodeData(INDIA, INDIA_STATE_CODE_CSV_FILE_PATH);
            IndiaStateCodeCSV[] codeCsv = new Gson().fromJson(sortedStateCodeData, IndiaStateCodeCSV[].class);
            System.out.println(codeCsv[36]);
            Assert.assertEquals("WB", codeCsv[36].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    // UC:5
    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnDescendinglySortedResult() {
        try {
            String sortedData = censusAnalyser.getPopulationWiseSortedData(INDIA, CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedData, IndiaCensusCSV[].class);
            System.out.println(censusCSV[0]);
            Assert.assertEquals(199812341, censusCSV[0].getPopulation());
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    // UC:6
    @Test
    public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnDescendinglySortedResult() {
        try {
            String sortedData = censusAnalyser.getPopulationDensityWiseSortedData(INDIA, CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedData, IndiaCensusCSV[].class);
            System.out.println(censusCSV[0]);
            Assert.assertEquals(1102, censusCSV[0].getDensityPerSqKm(), 0.0);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    // UC:7
    @Test
    public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnDescendinglySortedResult() {
        try {
            String sortedData = censusAnalyser.getAreaWiseSortedData(INDIA, CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedData, IndiaCensusCSV[].class);
            System.out.println(sortedData);
            Assert.assertEquals(342239, censusCSV[0].getAreaInSqKm(), 0.0);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    // UC:8
    @Test
    public void givenUsCensusCSVFile_ShouldReturnsCorrectRecords() {
        try {
            int numOfRecords = censusAnalyser.loadCensusData(US, US_CENSUS_CSV_FILE_PATH);
            System.out.println(numOfRecords);
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    // UC:9
    @Test
    public void givenUSCensusCSVFile_withRandomStateNames_ShouldReturnInNameSortedOrder() {
        try {
            String stateNameWiseSortedCensusData = censusAnalyser.getStateNameWiseSortedCensusData(US, US_CENSUS_CSV_FILE_PATH);
            System.out.println(stateNameWiseSortedCensusData);
            USCensusCSV[] censusCSV = new Gson().fromJson(stateNameWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alabama", censusCSV[0].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }

    // UC:10
    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldAscendinglySortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String sortedData = censusAnalyser.getPopulationWiseSortedData(US, US_CENSUS_CSV_FILE_PATH);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedData, USCensusCSV[].class);
            System.out.println(sortedData);
            Assert.assertEquals(37253956, censusCSV[0].getPopulation());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenUSCensusData_WhenSortedOnStateID_ShouldAscendinglySortedResult() {
        try {
            String sortedData = censusAnalyser.getStateIDWiseSortedCensusData(US, US_CENSUS_CSV_FILE_PATH);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedData, USCensusCSV[].class);
            System.out.println(censusCSV[0].getStateID());
            Assert.assertEquals("AK", censusCSV[0].getStateID());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnArea_ShouldReturnDescendinglySortedResult() {
        try {
            String sortedData = censusAnalyser.getAreaWiseSortedData(US, US_CENSUS_CSV_FILE_PATH);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedData, USCensusCSV[].class);
            System.out.println(censusCSV[0].getAreaInSqKm());
            Assert.assertEquals(1723338.01, censusCSV[0].getAreaInSqKm(), 0.0);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnHousingUnits_ShouldAscendinglySortedResult() {
        try {
            String sortedData = censusAnalyser.getHousingUnitsWiseSortedCensusData(US, US_CENSUS_CSV_FILE_PATH);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedData, USCensusCSV[].class);
            System.out.println(censusCSV[0].getHousingUnits());
            Assert.assertEquals(13680081, censusCSV[0].getHousingUnits());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //UC:11
    @Test
    public void givenUSCensusData_WhenSortedOnPopulationWithDensity_ShouldAscendinglySortedResult() {
        try {
            String stateNameWiseSortedCensusData = censusAnalyser.getPopulationDensityWiseSortedData(US, US_CENSUS_CSV_FILE_PATH);
            USCensusCSV[] censusCSV = new Gson().fromJson(stateNameWiseSortedCensusData, USCensusCSV[].class);
            System.out.println(censusCSV[0]);
            Assert.assertEquals(3805.61, censusCSV[0].getDensityPerSqKm(), 0.0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
