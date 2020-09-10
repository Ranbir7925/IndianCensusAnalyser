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
        state = usCensusCSV.usState;
        stateCode = usCensusCSV.stateId;
        population = usCensusCSV.usPopulation;
        populationDensity = usCensusCSV.populationDensity;
        totalArea = usCensusCSV.totalArea;

    }
}
