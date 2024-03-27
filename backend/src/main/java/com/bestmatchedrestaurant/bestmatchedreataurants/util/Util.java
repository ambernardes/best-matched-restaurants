package com.bestmatchedrestaurant.bestmatchedreataurants.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public final class Util {

    private Util(){}

    public static String readResource(String resourceName) throws IOException {
        InputStream inputStream = new ClassPathResource(resourceName).getInputStream();
        try (Scanner scanner = new Scanner(inputStream).useDelimiter("\\A")) {
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
    public static List<Map<String, String>> readCsv(String csvText) {
        List<Map<String, String>> response = new ArrayList<>();
        List<String> keys = null;

        for (String line : csvText.split("\r\n")) {
            String[] values = line.split(",");

            if (keys != null) {
                if (values.length != keys.size()) {
                    throw new IllegalArgumentException(String.format("Line size mismatched with header size, line size: %d, header size: %d, line:\n\t%s", values.length, keys.size(), line));
                }

                HashMap<String, String> row = new HashMap<>();
                for (int index = 0; index < values.length; index++) {
                    row.put(keys.get(index), values[index]);
                }
                response.add(row);
            }

            // it's the first line
            if (keys == null) {
                keys = List.of(values);
            }
        }

        return response;
    }
}
