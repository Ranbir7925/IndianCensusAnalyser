import com.opencsv.bean.CsvToBean;
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
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class).withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();

            Iterator<IndiaCensusCSV> censusCSVIterator =  csvToBean.iterator();
            int numberOfEntries = 0;
            while (censusCSVIterator.hasNext())
            {
                numberOfEntries++;
                censusCSVIterator.next();
            }
            return numberOfEntries;
        } catch (IOException e) {
           throw new CensusAnalyserException(e.getMessage(),
                   CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER);
        }
    }

    public int loadIndiaStateCode(String indiaStateCodeCsvFilePath) throws CensusAnalyserException {
        try{
            Reader reader = Files.newBufferedReader(Paths.get(indiaStateCodeCsvFilePath));
            CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaStateCodeCSV.class).withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaStateCodeCSV> csvToBean = csvToBeanBuilder.build();

            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = csvToBean.iterator();
            int numberOfEntries =0;
            while (stateCodeCSVIterator.hasNext())
            {
                numberOfEntries++;
                stateCodeCSVIterator.next();
            }
            return  numberOfEntries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER);
        }
    }
}
