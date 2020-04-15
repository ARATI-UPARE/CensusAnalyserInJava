package censusanalyser;

public class IndiaCensusCSVDao {

    public String state;
    public int population;
    public int areaInSqKm;
    public int densityPerSqKm;

    public IndiaCensusCSVDao( IndiaCensusCSV indiaCensusCSV) {
        indiaCensusCSV.state = state;
        indiaCensusCSV.population = population;
        indiaCensusCSV.areaInSqKm = areaInSqKm;
        indiaCensusCSV.densityPerSqKm = densityPerSqKm;
    }
}
