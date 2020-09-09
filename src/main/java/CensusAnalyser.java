import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList = null;
    List<IndiaStateCodeCSV> stateCSVList = null;


    public int loadIndiaCensusData(String indiaCensusCsvFilePath) throws CensusAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusCSVList = csvBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public int loadIndiaStateCode(String indiaStateCodeCsvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(indiaStateCodeCsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCSVList = csvBuilder.getCSVFileList(reader, IndiaStateCodeCSV.class);
            return stateCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        censusCSVList = this.sort(censusCSVList,censusComparator);
        String sortedStateCensus = new Gson().toJson(censusCSVList);
        return sortedStateCensus;
    }

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        if (stateCSVList == null || stateCSVList.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaStateCodeCSV> censusComparator = Comparator.comparing(census -> census.stateCode);
        stateCSVList = this.sort(stateCSVList,censusComparator);
        String sortedStateCode = new Gson().toJson(stateCSVList);
        return sortedStateCode;
    }

    private static <E> List<E> sort(List<E> censusList,Comparator<E> censusComparator) {
        for (int i = 0; i < censusList.size()-1; i++) {
            for (int j =0; j< censusList.size() -i -1; j++) {
                E census1 = censusList.get(j);
                E census2 = censusList.get(j+1);
                if (censusComparator.compare(census1, census2) > 0){
                    censusList.set(j, census2);
                    censusList.set(j+1, census1);
                }
            }
        }
    return censusList;
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0)
        {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
        censusCSVList = this.sortInDescendingOrder(censusCSVList,censusComparator);
        String sortedPopulationCensus = new Gson().toJson(censusCSVList);
        return sortedPopulationCensus;
    }

    private static<E> List<E> sortInDescendingOrder(List<E> censusList, Comparator<E> censusComparator) {
        for (int i = 0; i < censusList.size()-1; i++) {
            for (int j =0; j< censusList.size() -i -1; j++) {
                E census1 = censusList.get(j);
                E census2 = censusList.get(j+1);
                if (censusComparator.compare(census1, census2) < 0){
                    censusList.set(j, census2);
                    censusList.set(j+1, census1);
                }
            }
        }
        return censusList;
    }

    public String getStatePopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0)
        {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        censusCSVList = this.sortInDescendingOrder(censusCSVList,censusComparator);
        String sortedPopulationDensityCensus = new Gson().toJson(censusCSVList);
        return sortedPopulationDensityCensus;
    }

    public String getStateAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        censusCSVList = this.sortInDescendingOrder(censusCSVList, censusComparator);
        String sortedAreaWiseCensus = new Gson().toJson(censusCSVList);
        return sortedAreaWiseCensus;
    }
}