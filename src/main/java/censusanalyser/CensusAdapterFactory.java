package censusanalyser;

import java.util.Map;

public class CensusAdapterFactory {
    public CensusAdapterFactory() {
    }

    public static Map<String, IndiaCensusCSVDao> getCensusData(Country country, String[] csvFilePath) throws  CensusAnalyserException {
        if (country.equals(Country.INDIA)) {
            return new IndiaCensusAdapter().loadCensusData(csvFilePath);
        } else if (country.equals(Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath[0]);
        else
            throw new  CensusAnalyserException( "Invalid country name", CensusAnalyserException. ExceptionType.INVALID_COUNTRY);
    }
}