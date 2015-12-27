package datamunging;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class WeatherDocumentParserShould {

    @Test
    public void shouldParseListOfColumnNames() {
        WeatherDocumentParser parser = parseDocument("data/weather.dat");
        
        String[] columnNames = parser.getColumnNames();
        assertEquals("Dy", columnNames[0]);
        assertEquals("MxT", columnNames[1]);
        assertEquals("MnT", columnNames[2]);
        assertEquals("AvT", columnNames[3]);
    }
    
    @Test
    public void shouldFillWeatherDocumentEntries() {
        WeatherDocumentParser parser = parseDocument("data/weather.dat");
        parser.getColumnNames();
        WeatherDocumentEntry entry = parser.get(0);
        assertEquals(1, entry.Dy);
        assertEquals(88, entry.MxT);
        assertEquals(59, entry.MnT);
        entry = parser.get(1);
        assertEquals(2, entry.Dy);
        assertEquals(79, entry.MxT);
        assertEquals(63, entry.MnT);
    }

    private WeatherDocumentParser parseDocument(String filename) {
        WeatherDocumentParser parser = new WeatherDocumentParser();
        try {
            parser.parse(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e) {
            fail("weather.dat file required for test");
        } catch (IOException e) {
            fail("Could not read weather.dat file");
        }
        return parser;
    }

}
