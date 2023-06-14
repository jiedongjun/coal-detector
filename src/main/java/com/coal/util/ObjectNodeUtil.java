package com.coal.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectNodeUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }

    public static <T> T objectToJson(Object object, Class<T> toValueType) {
        return objectMapper.convertValue(object, toValueType);
    }

    public static <T> T convertValue(Object object, Class<T> toValueType) {
        return objectMapper.convertValue(object, toValueType);
    }

    public static ObjectNode stringToJson(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, ObjectNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayNode stringToJsonArr(String jsonArrStr) {
        try {
            return objectMapper.readValue(jsonArrStr, ArrayNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T stringToObj(String jsonArrStr, Class<T> toClass) {
        try {
            ObjectNode jsonNodes = objectMapper.readValue(jsonArrStr, ObjectNode.class);
            return convertValue(jsonNodes, toClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String objToString(Object obj) {
        ObjectNode jsonNodes = objectMapper.convertValue(obj, ObjectNode.class);
        return jsonNodes.toString();
    }
}
