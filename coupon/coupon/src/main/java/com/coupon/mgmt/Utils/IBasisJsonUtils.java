package com.coupon.mgmt.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
public class IBasisJsonUtils {

    static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static  String serializeClass(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            log.error("Error During Serialization");
            return null;
        }
    }

    public static <T> T convetToTFromMap(Map<String,Object> map, Class<T> clazz) {
        var str = serializeClass(map);
        try {
            return mapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T deserializeClass(InputStream inputStream , Class<T> clazz){
        if (inputStream == null)
            return null;
        try {
            return mapper.readValue(inputStream, clazz);
        } catch (IOException e) {
            log.error("Error During Deserialization");
            return null;
        }
    }

    public static <T> T deserializeClass(String obj , TypeReference<T> typeReference){
        if (obj == null) return null;
        try {
            obj = obj.replaceAll("\n","");
            T value = mapper.readValue(obj, typeReference);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,Object> convertReportDtoPojo(Object obj) {
        return mapper.convertValue(obj, new TypeReference<Map<String,Object>>() {});
    }
}
