
import java.io.Reader;
import java.util.List;

public interface ICSVBuilder<E> {
    List<E> getCSVFileList(Reader var1, Class var2) throws CSVBuilderException;
}
