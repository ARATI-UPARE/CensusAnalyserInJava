package censusanalyser;

import com.bl.csvbuilder.CsvBuilder;
import com.bl.csvbuilder.IcsvBuilder;

public class CSVBuilderFactory {

    public static IcsvBuilder CsvBuilder() {

        return new CsvBuilder();

    }
}