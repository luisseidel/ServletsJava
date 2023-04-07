package com.seidelsoft.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;

public class RegexUtil {

    public static Pattern getRegexById(String pattern) {
        return Pattern.compile("/"+pattern+"/([0-9]*)");
    }

    public static Pattern getRegexAll(String pattern) {
        return Pattern.compile("/"+pattern);
    }

    public static Long matchId(String requestUri, String pattern) throws ServletException {
        // Verifica o ID
        Matcher matcher = getRegexById(pattern).matcher(requestUri);
        if (matcher.find() && matcher.groupCount() > 0) {
            String s = matcher.group(1);
            if (s != null && s.trim().length() > 0) {
                return Long.parseLong(s);
            }
        }
        return null;
    }

    public boolean matchAll(String requestUri, String pattern) throws ServletException {
        Matcher matcher = getRegexAll(pattern).matcher(requestUri);

        return matcher.find();
    }
}
