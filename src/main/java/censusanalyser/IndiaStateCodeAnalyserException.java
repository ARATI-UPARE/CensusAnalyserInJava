package censusanalyser;

public class IndiaStateCodeAnalyserException extends Exception {

    enum ExceptionType {

        STATECODE_FILE_PROBLEM ,
        INVALID_FILE_TYPE,
        INVALID_FILE_DELIMETER,
        INVALID_FILE_HEADER
    }
    ExceptionType type;

    public IndiaStateCodeAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public IndiaStateCodeAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
