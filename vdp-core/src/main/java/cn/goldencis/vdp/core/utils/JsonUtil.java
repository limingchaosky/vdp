package cn.goldencis.vdp.core.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Map;

public final class JsonUtil {

    protected static Log log = LogFactory.getLog(JsonUtil.class);
    private static JsonUtil jasonmapper;

    private ObjectMapper objectmapper;

    private JsonUtil() {
        this.objectmapper = new ObjectMapper();
    }

    public static JsonUtil getInstance() {
        if (null == jasonmapper) {
            jasonmapper = new JsonUtil();
        }
        return jasonmapper;

    }

    public ObjectMapper getObjectmapper() {
        return objectmapper;
    }

    /*
     * @param jsonNode
     *            传递进来的json
     * @param jsonNode
     *            需要获得的path
     * @desc: 获得该path下的值value
     * ***/
    public static String getJsonPathValue(String jsonNode, String path) {
        String result = "";
        ObjectMapper obm = JsonUtil.getInstance().getObjectmapper();
        JsonNode rootNode;
        try {
            rootNode = obm.readTree(jsonNode);
            result = rootNode.path(path).textValue();
        } catch (JsonProcessingException e) {
            log.error(Toolkit.getStackTrace(e));
        } catch (IOException e) {
            log.error(Toolkit.getStackTrace(e));
        }

        return result;
    }

    /*
     * @param jsonNode
     *            传递进来的json
     * @param jsonNode
     *            需要获得的path
     * @desc: 获得该path下的值value
     * ***/
    public static String getJsonPathValue(JsonNode jsonNode, String path) {
        String result = null;
        result = jsonNode.path(path).textValue();
        return result;
    }

    /*
     * @param jsonNode
     *            传递进来的json
     * @desc: 获得该jsonNode对应的JsonNode
     * ***/
    public static JsonNode getJsonNode(String jsonNode) {
        ObjectMapper obm = JsonUtil.getInstance().getObjectmapper();
        JsonNode rootNode = null;
        try {
            rootNode = obm.readTree(jsonNode);
        } catch (JsonProcessingException e) {
            log.error(Toolkit.getStackTrace(e));
        } catch (IOException e) {
            log.error(Toolkit.getStackTrace(e));
        }
        return rootNode;
    }

    /*
     * @param obj
     *            传递进来的对象
     * @desc: 获得该Object对应的json值(打印该对象)
     * ***/
    public static String getObjectToString(Object obj) {
        ObjectMapper obm = JsonUtil.getInstance().getObjectmapper();
        String reuslt = "";
        try {
            reuslt = obm.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(Toolkit.getStackTrace(e));
        } catch (Exception e) {
            log.error(Toolkit.getStackTrace(e));
        }
        return reuslt;
    }

    /**
     * JSON->Map
     * @param json
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map json2Map(String json) {
        Map map = null;
        try {
            map = JsonUtil.getInstance().getObjectmapper().readValue(json, Map.class);
        } catch (JsonParseException e) {
            log.error(e.toString(), e);
        } catch (JsonMappingException e) {
            log.error(e.toString(), e);
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static <T> T parseJson(String jsonString, TypeReference<T> typeOfT) {
        T t = null;
        try {
            t = (T) JsonUtil.getInstance().getObjectmapper().readValue(jsonString, typeOfT);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }
}
