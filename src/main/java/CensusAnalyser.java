import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser {

    public int loadIndiaCensusData(String indiaCensusCsvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCsvFilePath));
            Iterator<IndiaCensusCSV> censusCSVIterator = getCSVFileIterator(reader, IndiaCensusCSV.class);
            int numberOfEntries = 0;
            while (censusCSVIterator.hasNext()) {
                numberOfEntries++;
                censusCSVIterator.next();
            }
            return numberOfEntries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER);
        }
    }

    public int loadIndiaStateCode(String indiaStateCodeCsvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(indiaStateCodeCsvFilePath));
            Iterator<IndiaCensusCSV> stateCodeCSVIterator = getCSVFileIterator(reader, IndiaCensusCSV.class);
            int numberOfEntries = 0;
            while (stateCodeCSVIterator.hasNext()) {
                numberOfEntries++;
                stateCodeCSVIterator.next();
            }
            return numberOfEntries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER);
        }
    }

    private <E> Iterator<E> getCSVFileIterator(Reader reader,
                                               Class<E> csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            return csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build().iterator();

        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
