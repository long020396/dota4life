package utilities;

public class Utils {
    // Function to get an attribute of a HTML tag
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
    
    // Function to get text value of a HTML tag
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
    
    // Function to get the first specific HTML open tag
    public static String getHtmlOpenTag(String input, String tagName) {
        String openTag = "<" + tagName + " ";
        int beginIndex = input.indexOf(openTag);
        int endIndex = input.indexOf(">", beginIndex) + 1;
        return input.substring(beginIndex, endIndex);
    }
    
    // Function to transform HTML special characters to symbol
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
}
