package com.hanwhalife.chlinfcheckerpoc.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class CheckUtil {

    public static void iterate(Map<String, Object> map, Map<String, Object> targetMap) {

        for (Map.Entry<String, Object> entry : map.entrySet()) {

            if (entry.getValue() instanceof Map) {
                iterate((Map<String, Object>) entry.getValue(), targetMap);
            } else if (entry.getValue() instanceof ArrayList) {
                ArrayList<Map<String, Object>> arrayList = (ArrayList<Map<String, Object>>) entry.getValue();
                for (Map<String, Object> array : arrayList) {
                    iterate(array, targetMap);
                }
            } else if (entry.getValue() instanceof String) {
                // Key 가 있고, Value 가 있는 지점
                if(!(iterateValueCheck(targetMap, entry.getKey(), entry.getValue()))){
                    log.debug("맞는 결과 값이 없습니다..SRC Key: {}, SRC Value: {}", entry.getKey(), entry.getValue());
                }
            }
            else {
                // no jobs..
            }
        }
    }

    //KeyCheck
    public static boolean iterateKey(Map<String, Object> map, String key) {
        boolean isFind = false;
        if(key.equals("elapsedTime")) {
            //        log.debug("elapsedTime");
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(isFind) {
                return true;
            }
            // log.debug("KEY : {}", entry.getKey());
            if(entry.getKey().equals(key)) {
                return true;
            }
            if (entry.getValue() instanceof Map) {
                isFind = iterateKey((Map<String, Object>) entry.getValue(), key);
            } else if (entry.getValue() instanceof ArrayList) {
                ArrayList<Map<String, Object>> arrayList = (ArrayList<Map<String, Object>>) entry.getValue();
                for (Map<String, Object> array : arrayList) {
                    if(isFind) return true;
                    isFind = iterateKey(array, key);
                }
            }
        }
        return isFind;
    }

    //Value Check
    public static boolean iterateValueCheck(Map<String, Object> map, String key, Object value) {
        boolean isFind = false;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(isFind) {
                break;
            }

            if (entry.getValue() instanceof Map) {
                isFind = iterateValueCheck((Map<String, Object>) entry.getValue(), key, value);
            } else if (entry.getValue() instanceof ArrayList) {
                ArrayList<Map<String, Object>> arrayList = (ArrayList<Map<String, Object>>) entry.getValue();
                for (Map<String, Object> array : arrayList) {
                    if(isFind) break;
                    isFind = iterateValueCheck(array, key, value);
                }
            }else if (entry.getValue() instanceof String) {
                // Key 가 있고, Value 가 있는 지점
                // log.debug("KEY : {}, VALUE: {}", entry.getKey(), entry.getValue());
                // Key 값과 Value 값이 일치 하는 경우에만 true를 반환 한다.
                if (entry.getKey().equals(key) && entry.getValue().equals(value)) {
                    // key와 value가 같은 경우 true 리턴
                    return true;
                }
            }
            else {
                // no jobs..
            }
        }
        return isFind;
    }
}
