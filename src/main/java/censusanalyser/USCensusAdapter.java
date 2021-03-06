package censusanalyser;

import java.util.Map;
import java.util.stream.StreamSupport;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, IndiaCensusCSVDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}