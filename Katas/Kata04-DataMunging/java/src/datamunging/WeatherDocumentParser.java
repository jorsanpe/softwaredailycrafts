package datamunging;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeatherDocumentParser {
    private static int[] COLUMN_SIZES = {5, 6, 6, 6, 6, 6, 5, 6, 7, 5, 5, 4, 4, 4, 4, 4, 6};
    String[] columnNames = new String[COLUMN_SIZES.length];
    ArrayList<WeatherDocumentEntry> documentEntries = null;
    
    private static final Map<String, Integer> entryIndex = new HashMap<String, Integer>();

    static {
        entryIndex.put("Dy", 0);
        entryIndex.put("MxT", 1);
        entryIndex.put("MnT", 2);
        entryIndex.put("AvT", 3);
    }

    public void parse(BufferedReader reader) throws IOException {
        extractColumnNames(reader);
        fakeRead(reader);
        extractEntryValues(reader);
    }

    private void extractEntryValues(BufferedReader reader) throws IOException {
        String line;
        String[] fields;
        WeatherDocumentEntry entry;
        
        documentEntries = new ArrayList<WeatherDocumentEntry>();
        while ((line=reader.readLine()) != null) {
            fields = extractRowFields(line);
            if (isNotTotalsEntry(fields)) {
                entry = convertToDocumentEntry(fields);
                documentEntries.add(entry);
            }
        }
    }
    
    private boolean isNotTotalsEntry(String[] fields) {
        return !fields[0].equals("mo");
    }

    private WeatherDocumentEntry convertToDocumentEntry(String[] fields) {
        WeatherDocumentEntry entry = null;

        entry = new WeatherDocumentEntry();
        entry.Dy = Integer.parseInt(fields[entryIndex.get("Dy")]);
        entry.MxT = Integer.parseInt(fields[entryIndex.get("MxT")]);
        entry.MnT = Integer.parseInt(fields[entryIndex.get("MnT")]);
        
        return entry;
    }

    private String[] extractRowFields(String line) {
        int i, column;
        String[] fields = new String[COLUMN_SIZES.length];

        column = 0;
        for (i=0; i<COLUMN_SIZES.length; i++) {
            if ((column + COLUMN_SIZES[i]) > line.length()) {
                break;
            }
            fields[i] = line.substring(column, column+COLUMN_SIZES[i]).replaceAll("[ \\*]", "");
            column += COLUMN_SIZES[i];
        }
        
        return fields;
    }

    private void fakeRead(BufferedReader reader) throws IOException {
        reader.readLine();
    }

    private void extractColumnNames(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        line = line.replaceAll("[ \t]+", " ");
        line = line.replaceAll("^[ \t]+", "");
        columnNames = line.split(" ", -1);
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public WeatherDocumentEntry get(int i) {
        return documentEntries.get(i);
    }
}
