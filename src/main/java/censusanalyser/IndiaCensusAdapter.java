package censusanalyser;

import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {
    public IndiaCensusAdapter() {
    }

    @Override
    public Map<String, IndiaCensusCSVDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        Map<String, IndiaCensusCSVDao> csvFileMap = super.loadCensusData(IndiaCensusCSV.class, csvFilePath[0]);
        if (csvFilePath.equals("IndiaCensusCSV")) {
            return csvFileMap;
        }
        return super.loadCensusData(IndiaStateCodeCSV.class, csvFilePath[0]);
    }
}