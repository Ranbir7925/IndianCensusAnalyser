import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Housing units", required = true)
    public int housingUnits;

    @CsvBindByName(column = "Total area", required = true)
    public float totalArea;

    @CsvBindByName(column = "Water area", required = true)
    public float waterArea;

    @CsvBindByName(column = "Land area", required = true)
    public float landArea;

    @CsvBindByName(column = "Population Density", required = true)
    public float populationDensity;

    @CsvBindByName(column = "Housing Density", required = true)
    public float housingDensity;

    @Override
    public String toString() {
        return "USCensusCSV{" +
                "StateId='" + stateId + '\'' +
                ", State='" +  state + '\'' +
                ", Population=" + population +
                ", Housing Units=" + housingUnits +
                ", TotalArea=" + totalArea +
                ", WaterArea=" + waterArea +
                ", LandArea=" + landArea +
                ", Population Density=" + populationDensity +
                ", Housing Density=" + housingDensity +
                '}';
    }
}
