public class CensusAnalyserException extends Exception {
    public enum ExceptionType
    {
        CENSUS_FILE_PROBLEM,
        INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER,
        UNABLE_TO_PARSE
    }

    ExceptionType type;
    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type =type;
    }
}
