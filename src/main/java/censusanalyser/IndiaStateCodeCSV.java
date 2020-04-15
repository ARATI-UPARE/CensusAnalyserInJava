package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

    @CsvBindByName(column = "SrNo", required = true)
    public String srNo;

    @CsvBindByName(column = "StateName", required = true)
    public String stateName;

    @CsvBindByName(column = "TIN", required = true)
    public String tin;

    @CsvBindByName(column = "StateCode", required = true)
    public static String stateCode;

    public IndiaStateCodeCSV(IndiaStateCodeCSV stateCodeCSV) {
    }

    @Override
    public String toString() {
        return "IndiaStateCodeCSV{" +
                "SrNo='" + srNo + '\'' +
                ", StateName='" + stateName + '\'' +
                ", TIN='" + tin + '\'' +
                ", StateCode='" + stateCode + '\'' +
                '}';
    }
}
