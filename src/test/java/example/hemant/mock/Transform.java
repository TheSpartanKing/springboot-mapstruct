package example.hemant.mock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Transform {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }

    public static <T> T fromJson(String json, TypeReference<T> TypeReference) throws JsonProcessingException {
        return mapper.readValue(json, TypeReference);
    }

    public static <T> String fromObject(Class<T> clazz) throws JsonProcessingException {
        return mapper.writeValueAsString(clazz);
    }
}
