package org.godseop.apple.model;

import org.godseop.apple.exception.AppleException;

import java.util.HashMap;
import java.util.Map;

public class Result extends HashMap<String, Object> {

    private Map<String, Object> responseMap;

    public Result() {
        responseMap = new HashMap<>();
        super.put("response", this.responseMap);
        this.put(Error.OK);
    }

    public Result(Error error) {
        this.put(error);
    }

    public Object put(String key, Object value) {
        responseMap.put(key, value);
        super.put("response", this.responseMap);
        return value;
    }

    public void put(Error error) {
        Map<String, String> result = new HashMap<>();

        result.put("code", error.getCode());
        result.put("message", error.getMessage());

        super.put("result", result);
    }

    public <E extends Exception> void put(E exception) {
        Map<String, String> result = new HashMap<>();

        if (exception instanceof AppleException) {
            result.put("code", ((AppleException)exception).getCode());
            result.put("message", exception.getMessage());
        } else {
            this.put(Error.SYSTEM_EXCEPTION);
        }

        super.put("result", result);
    }

}
