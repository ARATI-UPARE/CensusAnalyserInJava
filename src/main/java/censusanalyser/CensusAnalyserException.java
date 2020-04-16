package censusanalyser;

import com.bl.csvbuilder.CsvFileBuilderException;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        NO_CENSUS_DATA,
        STATE_CODE_FILE_PROBLEM,
        CENSUS_FILE_PROBLEM,
        INVALID_FILE_TYPE,
        INVALID_FILE_DELIMITER,
        INVALID_FILE_HEADER;
    }

    //Added Parameterized Constructor to get exception name;

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, CsvFileBuilderException.ExceptionType type ) {
        super(message);
        this.type = ExceptionType.valueOf(type.name());
    }
}
