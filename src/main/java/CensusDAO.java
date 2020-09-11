public class CensusDAO {

    public String stateCode;
    public String state;
    public int population;
    public double totalArea;
    public double populationDensity;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        population = indiaCensusCSV.population;
        populationDensity = indiaCensusCSV.densityPerSqKm;
    }


    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        stateCode = indiaStateCodeCSV.stateCode;
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        stateCode = usCensusCSV.stateId;
        population = usCensusCSV.population;
        populationDensity = usCensusCSV.populationDensity;
        totalArea = usCensusCSV.totalArea;

    }
}
