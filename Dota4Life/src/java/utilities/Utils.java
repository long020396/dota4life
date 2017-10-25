package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Utils {

    /* Function to get an attribute of a HTML tag */
    public static String getHtmlAttributeValue(String input, String attrName) {
        int openDitto = 0;
        int closeDitto = 0;
        boolean getDittoBefore = false;
        int beginIndex = input.indexOf(attrName);
        for (int i = beginIndex; i < input.length(); i++) {
            if (input.charAt(i) == '\"') {
                if (!getDittoBefore) {
                    openDitto = i;
                    getDittoBefore = true;
                } else {
                    closeDitto = i;
                    break;
                }
            }
        }
        return input.substring(openDitto + 1, closeDitto);
    }

    /* Function to get text value of a HTML tag */
    public static String getHtmlTextValue(String input, String tagName) {
        int begin = 0;
        int tmp = input.indexOf("<" + tagName);
        for (int i = tmp; i < input.length(); i++) {
            if (input.charAt(i) == '>') {
                begin = i + 1;
                break;
            }
        }
        int end = input.indexOf("</" + tagName);
        return input.substring(begin, end).trim();
    }

    /* Function to get the first specific HTML open tag */
    public static String getHtmlOpenTag(String input, String tagName) {
        String openTag = "<" + tagName + " ";
        int beginIndex = input.indexOf(openTag);
        int endIndex = input.indexOf(">", beginIndex) + 1;
        return input.substring(beginIndex, endIndex);
    }

    /* Function to transform HTML special characters to symbol */
    public static String normalizeHTMLString(String input) {
        if (input.contains("&#40;")) {
            input = input.replaceAll("&#40;", "(");
        }
        if (input.contains("&#41;")) {
            input = input.replaceAll("&#41;", ")");
        }
        if (input.contains("&amp;")) {
            input = input.replaceAll("&amp;", "&");
        }
        return input;
    }

    public static String removeSpace(String input) {
        return input.replaceAll("> <", "><").replaceAll("\t", "").replaceAll("\n", "");
    }

    /* Function to close unclosed <img> tag */
    public static String autoCloseTag(String input, String tag) {
        int beginIndex = input.indexOf("<" + tag, 0);
        while (beginIndex >= 0) {
            int endIndex = input.indexOf(">", beginIndex) + 1;
            String slash = input.substring(endIndex - 2, endIndex - 1);

            if (!slash.equals("/")) {
                input = input.substring(0, endIndex - 1) + "/" + input.substring(endIndex - 1);
            }
            beginIndex = input.indexOf("<" + tag, endIndex);
        }

        return input;
    }

    /* Function to set all String in HashMap to null for better performance in set String */
    public static <T> void setAllToNull(HashMap<String, T> hashMap) {
        for (Map.Entry<String, T> entrySet : hashMap.entrySet()) {
            entrySet.setValue(null);
        }
    }

    public static String downloadImage(String realPath, String folderPath, String fileName, String uri) {
        String filePath = "";
        try {
            String imgFormat = uri.substring(uri.lastIndexOf(".") + 1);
            filePath = folderPath + "\\" + fileName + "." + imgFormat;

            URL url = new URL(uri);
            BufferedImage img = ImageIO.read(url);
            File file = new File(realPath + filePath);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                ImageIO.write(img, imgFormat, file);
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        filePath = filePath.replaceAll("\\\\", "/");
        return filePath;
    }
    
    public static <T> String marshallerToString (T object) {
        String xmlString = null;
        try {
            JAXBContext jaxb = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxb.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            marshaller.marshal(object, sw);
            
            xmlString = sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlString;
    }
    
    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
//            dbConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433:Doto4Life", "sa", "lovenex23");
            dbConnection = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1\\localhost:1433;databasename=Doto4Life;user=sa;password=lovenex23");
            return dbConnection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
