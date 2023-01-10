package com.example.minkiApi.exception;

import org.json.simple.JSONObject;

public class ErrorResponse {
    public static JSONObject JsonErrorResponse(int errCode, String errorMessage){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorCode", errCode);
        jsonObject.put("errorMessage", errorMessage);
        return jsonObject;
    }
}
