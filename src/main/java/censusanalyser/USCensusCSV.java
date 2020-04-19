package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

        @CsvBindByName(column = "State Id")
        private String stateID;

        @CsvBindByName(column = "State")
        public String state;

        @CsvBindByName(column = "Population Density")
        private String populationDensity;

        @CsvBindByName(column = "Population")
        private String population;

        @CsvBindByName(column = "Total area")
        private String area;

        @CsvBindByName(column = "Housing units")
        private String housingUnits;

        @CsvBindByName(column = "Water area")
        private String waterArea;

        @CsvBindByName(column = "Land Area")
        private String landArea;

        @CsvBindByName(column = "Housing Density")
        private String housingDensity;

    @Override
    public String toString() {
        return "USCensusCSV{" +
                "State Id='" + stateID + '\'' +
                ", State='" + state + '\'' +
                ", Population Density='" + populationDensity + '\'' +
                ", Population='" + population + '\'' +
                ", Total area='" + area + '\'' +
                ", Housing units='" + housingUnits + '\'' +
                ", Water area='" + waterArea + '\'' +
                ", Land Area='" + landArea + '\'' +
                ", Housing Density='" + housingDensity + '\'' +

                '}';
    }

    public String getHousingDensity() {
        return housingDensity;
    }

    public void setHousingDensity(String housingDensity) {
        this.housingDensity = housingDensity;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getWaterArea() {
        return waterArea;
    }

    public void setWaterArea(String waterArea) {
        this.waterArea = waterArea;
    }

    public String getHousingUnits() {
        return housingUnits;
    }

    public void setHousingUnits(String housingUnits) {
        this.housingUnits = housingUnits;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(String populationDensity) {
        this.populationDensity = populationDensity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }
}
