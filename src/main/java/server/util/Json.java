package server.util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

/**
 *  Serialize Java objects into JSON
 *  Deserialize JSON string into Java object
 *  JSON files stored in target folder
 */
public class Json {

    private static ObjectMapper mObjectMapper = defaultObjectMapper();

    /**
     * Creates and configures ObjectMapper object
     * @return ObjectMapper
     */
    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //program will not crash if a property missing
        return om;
    }

    /**
     * Converts string to into ObjectMapper
     * @param jsonSource
     * @return
     * @throws IOException
     */
    public static JsonNode parse(String jsonSource) throws IOException {
        System.out.println(jsonSource);
        return mObjectMapper.readTree(jsonSource);
    }

    /**
     * Returns an object of generic type
     * @param node
     * @param obj
     * @return
     * @param <A>
     * @throws JsonProcessingException
     */
    public static <A> A fromJson(JsonNode node, Class<A> obj) throws JsonProcessingException {
        return mObjectMapper.treeToValue(node, obj );
    }

    public static JsonNode toJson(Object obj){
        return mObjectMapper.valueToTree(obj);
    }
    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }
    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }
    public static String generateJson(Object obj, boolean pretty) throws JsonProcessingException{
        ObjectWriter objectWriter = mObjectMapper.writer();
        if(pretty){
            objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(obj);
    }




}
