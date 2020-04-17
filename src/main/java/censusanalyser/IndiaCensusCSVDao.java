package censusanalyser;

public class IndiaCensusCSVDao {

    public String state;
    public int population;
    public int areaInSqKm;
    public int densityPerSqKm;

    public String srNo;
    public String stateCode;
    public String tin;

    public IndiaCensusCSVDao( IndiaCensusCSV indiaCensusCSV) {
        this.state=indiaCensusCSV.state;
        this.population =indiaCensusCSV.population ;
        this.areaInSqKm=indiaCensusCSV.areaInSqKm  ;
        this.densityPerSqKm=indiaCensusCSV.densityPerSqKm  ;
    }

    public IndiaCensusCSVDao( IndiaStateCodeCSV indiaStateCodeCSV) {
        this.srNo=indiaStateCodeCSV.srNo  ;
        this.stateCode=indiaStateCodeCSV.stateCode  ;
        this.tin=indiaStateCodeCSV.tin  ;
        this.state=indiaStateCodeCSV.stateName;
    }
}
