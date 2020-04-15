package censusanalyser;

public class IndiaCensusCSVDAO {

    public static String state;
    public int population;
    public int areaInSqKm;
    public int densityPerSqKm;
    public static String stateCode;

    public IndiaCensusCSVDAO(IndiaCensusCSVDAO indiaCensusCSV) {
        indiaCensusCSV.state = state;
        indiaCensusCSV.population = population;
        indiaCensusCSV.areaInSqKm = areaInSqKm;
        indiaCensusCSV.densityPerSqKm = densityPerSqKm;
    }
    public IndiaCensusCSVDAO(IndiaStateCodeCSV indiaStateCodeCSV) {

        indiaStateCodeCSV.stateCode = this.stateCode;
    }

}
