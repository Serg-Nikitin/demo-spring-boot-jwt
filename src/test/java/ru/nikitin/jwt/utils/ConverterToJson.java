package ru.nikitin.jwt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterToJson {
    public static String convert(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
