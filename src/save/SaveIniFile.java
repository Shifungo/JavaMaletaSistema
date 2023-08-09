package save;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class SaveIniFile {

    record KeyValue(String key, String value) {}

    public static void saveIniFile(String fileName, Properties properties) throws IOException {
        try (OutputStream output = new FileOutputStream(fileName)) {
            properties.store(output, null);
        }
    }

}
