package common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectToJson<T> {

    public String convert(T obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonInString = gson.toJson(obj);
        return jsonInString;
    }
}
