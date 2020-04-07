package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    // Method For IndianCensusCSV -Builder1
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader); // Create Object
            csvToBeanBuilder.withType(IndiaCensusCSV.class);                                    // which type conversion
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);                                 // Accept while spaces
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();                     // to built data
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();                  // Interface
            int namOfEateries = 0;                                                              // count records

            // lambada expression for getting count
            Iterable<IndiaCensusCSV> indiaCensusCSVIterable = () -> censusCSVIterator;          // iterate all data at a time

            namOfEateries = (int) StreamSupport.stream(indiaCensusCSVIterable.spliterator(), false).count();
            return namOfEateries;

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("header!"))
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);
        }

    }
    // Method For IndianStateCodeCSV
    public int loadIndiaStateCodeData(String csvFilePath) throws IndiaStateCodeAnalyserException {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaStateCodeCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = csvToBean.iterator();
            int namOfEateries = 0;

            // lambada expression for getting count
            Iterable<IndiaStateCodeCSV> indiaCensusCSVIterable = () -> stateCodeCSVIterator;

            namOfEateries = (int) StreamSupport.stream(indiaCensusCSVIterable.spliterator(), false).count();
            return namOfEateries;

        } catch (IOException e) {
            throw new IndiaStateCodeAnalyserException(e.getMessage(),IndiaStateCodeAnalyserException.ExceptionType.STATECODE_FILE_PROBLEM);
        }catch (RuntimeException e) {
            if (e.getMessage().contains("header!"))
                throw new IndiaStateCodeAnalyserException(e.getMessage(),IndiaStateCodeAnalyserException.ExceptionType.INVALID_FILE_TYPE);
                throw new IndiaStateCodeAnalyserException(e.getMessage(), IndiaStateCodeAnalyserException.ExceptionType.INVALID_FILE_TYPE);
        }
    }
}