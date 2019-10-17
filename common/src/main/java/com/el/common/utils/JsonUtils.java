package com.el.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * User: Rolandz
 * Date: 5/17/16
 * Time: 2:41 PM
 */
public class JsonUtils {
    private static Logger LOG = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * null if any exception occurs or o is null
     * @param o
     * @return
     */
    public static String toJson(Object o) {
        if (o == null) {
            return null;
        }

        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            LOG.error("toJson: {}", e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            LOG.error("fromJson: {}", e.getMessage());
            return  null;
        }
    }
}
