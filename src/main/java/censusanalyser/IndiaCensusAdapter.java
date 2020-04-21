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

//    @Override
//    protected Map<String, IndiaCensusCSVDao> mapCsvFileToCsvDao(Iterable csvFileIterable,Class censusCSVClass) {
//        if (censusCSVClass.getName().contains("IndiaCensusCSV")) {
//                StreamSupport.stream(csvFileIterable.spliterator(), false)
//                    .map(IndiaCensusCSV.class::cast)
//                    .forEach(censusCSV -> csvFileMap.put(((IndiaCensusCSV) censusCSV).getState(), new IndiaCensusCSVDao((IndiaCensusCSV) censusCSV)));
//            }
//        else if(censusCSVClass.getName().contains("IndiaStateCodeCSV")) {
//                StreamSupport.stream(csvFileIterable.spliterator(), false)
//                    .map(IndiaStateCodeCSV.class::cast)
//                    .forEach(censusCSV -> csvFileMap.put(((IndiaStateCodeCSV) censusCSV).getStateCode(), new IndiaCensusCSVDao((IndiaCensusCSV) censusCSV)));
//            }
//        return csvFileMap;
//    }

}