package censusanalyser;

public class IndiaCensusCSVDao {

    private String landArea;
    private String waterArea;
    private String housingUnits;
    private String stateID;
    private String housingDensity;

    private String srNo;
    private String state;
    private String population;
    private String areaInSqKm;
    private String densityPerSqKm;
    private String stateCode;
    private String tin;


    public IndiaCensusCSVDao(IndiaCensusCSV indiaCensusCSV) {
        this.state = indiaCensusCSV.getState();
        this.population = indiaCensusCSV.getPopulation();
        this.areaInSqKm = indiaCensusCSV.getAreaInSqKm();
        this.densityPerSqKm = indiaCensusCSV.getDensityPerSqKm();
    }

    public IndiaCensusCSVDao(IndiaStateCodeCSV indiaStateCodeCSV) {
        this.srNo = indiaStateCodeCSV.getSrNo();
        this.state = indiaStateCodeCSV.getStateName();
        this.stateCode = indiaStateCodeCSV.getStateCode();
        this.tin = indiaStateCodeCSV.getTin();
    }

    public IndiaCensusCSVDao(USCensusCSV usCensusCSV) {
        this.state = usCensusCSV.getState();
        this.population = usCensusCSV.getPopulation();
        this.areaInSqKm = usCensusCSV.getArea();
        this.densityPerSqKm = usCensusCSV.getPopulationDensity();
        this.stateID = usCensusCSV.getStateID();
        this.housingDensity = usCensusCSV.getHousingDensity();
        this.housingUnits=usCensusCSV.getHousingUnits();
        this.waterArea = usCensusCSV.getWaterArea();
        this.landArea = usCensusCSV.getLandArea();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }
}
