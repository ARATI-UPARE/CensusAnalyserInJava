package censusanalyser;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        STATE_CODE_FILE_PROBLEM,
        CENSUS_FILE_PROBLEM,
        INVALID_FILE_TYPE,
        INVALID_FILE_DELIMITER,
        INVALID_FILE_HEADER;
    }

    //Added Parameterized Constructor to get exception name;
    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
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
