package fr.epita.assistants.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public String string;
    public String value;
    public String path;

    public Json(String string, String value, String path) {
        this.string = string;
        this.value = value;
        this.path = path;
    }

    public Json() {
    }

    public static void addToJson(String string, String value, String path) {
        ObjectMapper mapper = new ObjectMapper();
        Json json = new Json(string, value, path);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            Map<String, Object> map = new HashMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                 map.putAll(objectMapper.readValue(line, new TypeReference<Map<String,Object>>(){}));
            }
            map.put(json.string, json.value);
            mapper.writeValue(new File(path), map);

            reader.close();
        } catch (Exception e) {
            try {
                Map<String, Object> map = new HashMap<>();
                map.put(json.string, json.value);
                mapper.writeValue(new File(path), map);
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }
}
