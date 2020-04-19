package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

        @CsvBindByName(column = "State Id")
        private String stateID;

        @CsvBindByName(column = "State")
        private String state;

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

    }
