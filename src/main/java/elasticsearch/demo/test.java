package elasticsearch.demo;

import elasticsearch.demo.document.db_location_5;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
//        String path =  "C:\\Users\\admin\\Downloads\\final.csv";
//
//        try(ICsvBeanReader beanReader
//                    = new CsvBeanReader(new FileReader(path), CsvPreference.STANDARD_PREFERENCE))
//        {
//            final String[] headers = beanReader.getHeader(true);
//            final CellProcessor[] processors = getProcessors();
//
//            db_location_5 db;
//            while ((db = beanReader.read(db_location_5.class, headers, processors)) != null) {
//                System.out.println(db);
//            }
//        }
        Float x,y;
        String point = "POINT(106.704321652 10.7709812240001)";
        for (int i =0 ; i < point.length(); i++) {
            Boolean flag = Character.isDigit(point.charAt(i));
            if(flag) {
                System.out.println("'"+ point.charAt(i)+"' is a number");
            }
        }
    }
    private static CellProcessor[] getProcessors() {

        final CellProcessor[] processors = new CellProcessor[] {
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new ParseInt()),
                new Optional(new ParseInt()),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new ParseInt()),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new ParseInt()),
        };
        return processors;
    }
}
