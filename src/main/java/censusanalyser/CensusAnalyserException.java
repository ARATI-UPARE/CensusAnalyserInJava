package censusanalyser;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        CENSUS_CSV_FILE_PATH,
        CENSUS_FILE_PROBLEM,
        STATE_CODE_FILE_PROBLEM,
        INVALID_FILE_TYPE,
        INVALID_FILE_DELIMETER,
        INVALID_FILE_HEADER

    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
