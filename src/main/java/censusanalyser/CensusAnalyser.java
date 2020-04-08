package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import static javafx.scene.input.KeyCode.T;

public class CensusAnalyser {

    // Method For IndianCensusCSV -Builder1
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<IndiaCensusCSV> censusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader,IndiaCensusCSV.class);
            return this.getCount(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("header!"))
                throw  new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);
                throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMETER);
        }

    }

    // Method For IndianStateCodeCSV -Builder 2
    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            return this.getCount(stateCodeCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM);
        }catch (RuntimeException e ){
            if(e.getMessage().contains("header!")){
                throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.INVALID_FILE_HEADER);}
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_FILE_DELIMETER);
        }
    }

    // Method to get Count
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numberOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numberOfEnteries;
    }

}