package com.hanwhalife.chlinfcheckerpoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanwhalife.chlinfcheckerpoc.util.CheckUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

//@SpringBootTest
class ChlInfCheckerPocApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {

        String asis_result = "{\"data\":{\"resultCode\":\"SUCCESS\",\"resutMessage\":\"정상등록되었습니다.\",\"resultInfo\":{\"customerName\":\"테스트\",\"creditInfoAgreeYn\":\"Y\",\"thirdMarketingAgree\":\"Y\",\"thirdOfferAgreeRequestTerm\":\"3\",\"thirdOfferAgreeEndDate\":\"20250510\",\"thirdCreditInfoOfferAgreeYn\":\"Y\",\"thirdApproveDate\":\"20220510\"}},\"header\":{\"elapsedTime\":\"488\"}}";
        String targetResult = "{\"data\":{\"resultCode\":\"SUCCESS\",\"resutMessage\":\"정상등록되었습니다.\",\"resultInfo\":{\"customerName\":\"테스트\",\"creditInfoAgreeYn\":\"Y\",\"thirdMarketingAgree\":\"Y\",\"thirdOfferAgreeRequestTerm\":\"3\",\"thirdOfferAgreeEndDate\":\"20250510\",\"thirdCreditInfoOfferAgreeYn\":\"Y\",\"thirdApproveDate\":\"20220510\"}},\"header\":{\"elapsedTime\":\"488\"}}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapAsIs;
        Map<String, Object> mapTarget;

        mapAsIs = objectMapper.readValue(asis_result, Map.class);
        mapTarget = objectMapper.readValue(targetResult, Map.class);

        CheckUtil.iterate(mapAsIs, mapTarget);
    }

}
