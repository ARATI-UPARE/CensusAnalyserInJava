package censusanalyser;

public class IndiaCensusCSVDao {

    private String landArea;
    private String waterArea;
    private int housingUnits;
    private String stateID;
    private double housingDensity;

    private String srNo;
    private String state;
    private int population;
    private double areaInSqKm;
    private double densityPerSqKm;
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
        this.areaInSqKm = usCensusCSV.getAreaInSqKm();
        this.densityPerSqKm = usCensusCSV.getDensityPerSqKm();
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

    public double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(double densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(double areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public int getHousingUnits() {
        return housingUnits;
    }

    public void setHousingUnits(int housingUnits) {
        this.housingUnits = housingUnits;
    }

    public String getWaterArea() {
        return waterArea;
    }

    public void setWaterArea(String waterArea) {
        this.waterArea = waterArea;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public double getHousingDensity() {
        return housingDensity;
    }

    public void setHousingDensity(double housingDensity) {
        this.housingDensity = housingDensity;
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }
}
