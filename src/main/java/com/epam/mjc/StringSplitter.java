package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringTokenizer st = new StringTokenizer(source, delimiters.toString());
        List<String> result = new ArrayList<>();
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        return result;
    }
}
