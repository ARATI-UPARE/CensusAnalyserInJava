package censusanalyser;

import java.util.Map;
import java.util.stream.StreamSupport;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, IndiaCensusCSVDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }

//    @Override
//    protected Map<String, IndiaCensusCSVDao> mapCsvFileToCsvDao(Iterable iterable,Class censusCSVClass) {
//        StreamSupport.stream(iterable.spliterator(), false)
//            .map(USCensusCSV.class::cast)
//            .forEach(censusCSV -> csvFileMap.put(((USCensusCSV) censusCSV).state, new IndiaCensusCSVDao((IndiaCensusCSV) censusCSV)));
//    return csvFileMap;
//    }
}