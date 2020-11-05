package com.tutorial.crud.util;

import com.opencsv.CSVWriter;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CommonUtils {
    

    /**
     * CsvReaderExamples
     */
	
    public static List<String[]> readAll(Reader reader) {

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        List<String[]> list = new ArrayList<>();
        try {
            list = csvReader.readAll();
            reader.close();
            csvReader.close();
        } catch (Exception ex) {
            err(ex);
        }
        return list;
    }

    public static List<String[]> oneByOne(Reader reader) {
        List<String[]> list = new ArrayList<>();
        try {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(0)
                    .withCSVParser(parser)
                    .build();

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                list.add(line);
            }
            reader.close();
            csvReader.close();
        } catch (Exception ex) {
        	err(ex);
        }
        return list;
    }
    

    /**
     * CsvWriterExamples
     */
	
    public static String csvWriterOneByOne(List<String[]> stringArray, Path path) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));
            for (String[] array : stringArray) {
                writer.writeNext(array);
            }
            writer.close();
        } catch (Exception ex) {
        	err(ex);
        }
        return readFile(path);
    }

    public static void csvWriterAll(List<String[]> stringArray, Path path) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));
            writer.writeAll(stringArray);
            writer.close();
        } catch (Exception ex) {
        	err(ex);
        }
    }
    

    /**
     * Write Files
     */

    public static Path fileOutCustomPath(String path) throws URISyntaxException {
    	Path paths = Paths.get(path);
        return paths;
    }

    public static Path fileOutAllPath() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource(Constants.CSV_All).toURI();
        return Paths.get(uri);
    }

    public static Path fileOutBeanPath() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource(Constants.CSV_BEAN).toURI();
        return Paths.get(uri);
    }

    public static Path fileOutOnePath() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource(Constants.CSV_ONE).toURI();
        return Paths.get(uri);
    }

    /**
     * Read Files
     */

    public static Path twoColumnCsvPath() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource(Constants.TWO_COLUMN_CSV).toURI();
        return Paths.get(uri);
    }

    public static Path fourColumnCsvPath() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource(Constants.FOUR_COLUMN_CSV).toURI();
        return Paths.get(uri);
    }

    public static Path namedColumnCsvPath() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource(Constants.NAMED_COLUMN_CSV).toURI();
        return Paths.get(uri);
    }

    /**
     * Simple File Reader
     */

    public static String readFile(Path path) {
        String response = "";
        try {
            FileReader fr = new FileReader(path.toString());
            BufferedReader br = new BufferedReader(fr);
            String strLine;
            StringBuffer sb = new StringBuffer();
            while ((strLine = br.readLine()) != null) {
                sb.append(strLine);
            }
            response = sb.toString();
            System.out.println(response);
            fr.close();
            br.close();
        } catch (Exception ex) {
        	err(ex);
        }
        return response;
    }

    /**
     * Dummy Data for Writing.
     */

    public static List<String[]> twoColumnCsvString() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"ColA", "ColB"});
        list.add(new String[]{"A", "B"});
        return list;
    }

    public static List<String[]> fourColumnCsvString() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"ColA", "ColB", "ColC", "ColD"});
        list.add(new String[]{"A", "B", "A", "B"});
        list.add(new String[]{"BB", "AB", "AA", "B"});
        return list;
    }

    /**
     * Message Helpers
     */

    public static void print(String msg) {
        System.out.println(msg);
    }

    public static void err(Exception ex) {
        System.out.println(Constants.GENERIC_EXCEPTION + " " + ex);
    }

}
