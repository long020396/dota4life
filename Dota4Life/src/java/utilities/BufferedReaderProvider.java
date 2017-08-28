package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BufferedReaderProvider {

    public static BufferedReader getBufferedReader(String uri) {
        BufferedReader bReader = null;

        try {
            URL url = new URL(uri);
            URLConnection uc = url.openConnection();
            uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            InputStream inStream = uc.getInputStream();
            bReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(BufferedReaderProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bReader;
    }
}
