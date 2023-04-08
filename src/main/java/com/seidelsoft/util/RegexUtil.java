package com.seidelsoft.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static Pattern getRegexById(String pattern) {
        return Pattern.compile("/"+pattern+"/([0-9]*)");
    }

    public static Pattern getRegexAll(String pattern) {
        return Pattern.compile("/"+pattern);
    }

    public static Long matchId(HttpServletRequest req, String pattern) throws ServletException {
        // Verifica o ID
        Matcher matcher = getRegexById(pattern).matcher(req.getRequestURI());
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
