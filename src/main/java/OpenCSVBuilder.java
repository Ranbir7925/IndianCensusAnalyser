import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder <E> implements ICSVBuilder {
    public Iterator<E> getCSVFileIterator(Reader reader,
                                       Class csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            return csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true).build().iterator();

        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
