package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

        @CsvBindByName(column = "State Id")
        private String stateID;

        @CsvBindByName(column = "State")
        public String state;

        @CsvBindByName(column = "Population Density")
        private double densityPerSqKm;

        @CsvBindByName(column = "Population")
        private int population;

        @CsvBindByName(column = "Total area")
        private double areaInSqKm;

        @CsvBindByName(column = "Housing units")
        private int housingUnits;

        @CsvBindByName(column = "Water area")
        private String waterArea;

        @CsvBindByName(column = "Land Area")
        private String landArea;

        @CsvBindByName(column = "Housing Density")
        private double housingDensity;


    @Override
    public String toString() {
        return "USCensusCSV{" +
                "State Id='" + stateID + '\'' +
                ", State='" + state + '\'' +
                ", Population Density='" + densityPerSqKm + '\'' +
                ", Population='" + population + '\'' +
                ", Total area='" + areaInSqKm + '\'' +
                ", Housing units='" + housingUnits + '\'' +
                ", Water area='" + waterArea + '\'' +
                ", Land Area='" + landArea + '\'' +
                ", Housing Density='" + housingDensity + '\'' +

                '}';
    }
    public double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(double densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    public double getHousingDensity() {
        return housingDensity;
    }

    public void setHousingDensity(double housingDensity) {
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

    public int getHousingUnits() {
        return housingUnits;
    }

    public void setHousingUnits(int housingUnits) {
        this.housingUnits = housingUnits;
    }

    public double getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(double area) {
        this.areaInSqKm = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
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
