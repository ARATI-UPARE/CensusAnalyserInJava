package censusanalyser;

import censusanalyser.CensusAnalyser;
import censusanalyser.IndiaStateCodeAnalyserException;
import org.junit.Assert;
import org.junit.Test;

public class IndianStateCodeTest {

    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCodeCSV.csv";

    // TC:2.1
    @Test
    public void givenIndiaStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser indiaStateCodAnalyser = new CensusAnalyser();
            int numOfRecords = indiaStateCodAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37,numOfRecords);
             } catch (IndiaStateCodeAnalyserException e) {}
    }
}
