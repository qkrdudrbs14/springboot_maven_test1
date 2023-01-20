package com.example.demo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AppTest {
    
    @Test
    void test1() {
        log.info("Test1asd");
    }

    @Test
    void test2() {

        String body = "";

        body ="{userId=1,passWd=2,code=1,test=test1}";
        body = body.replace("{", "").replace("}", "");

        String[] bodyImsi = body.split(",");

        body = "[{";
  
        for(String t : bodyImsi) {
            String[] t1 = t.split("=");
            t1[0] = "\"" + t1[0] + "\"";
            t1[1] = "\"" + t1[1] + "\"";
            
            System.out.println(t1[0] + ":" + t1[1]);
            body = body + t1[0] + ":" + t1[1] + ",";
        }
        body = body  + "}]";

        String resultBody = body.substring(0, body.lastIndexOf(",")) + body.substring(body.lastIndexOf(",")+1, body.length());

        System.out.println(resultBody);
        System.out.println("result =================");

        ObjectMapper mapper = new ObjectMapper();
        try {
           List<Object> list =  mapper.readValue(resultBody, new TypeReference<List<Object>>(){});
           System.out.println(list);
           System.out.println(list.size());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    void test3() {

        String body = "";
        body = "[{userId=,passWd=,code=,test=test1},{userId=,passWd=,code=,test=test2},{userId=,passWd=,code=,test=test3}]";

        Pattern pattern = Pattern.compile("[{](.*?)[}]");

        Matcher matcher = pattern.matcher(body);

        while(matcher.find()) {
            System.out.println(matcher.group(1));

            if(matcher.group(1) == null) break;
        }

    }

    String convert(String body) {

        String[] bodyImsi = body.split(",");

        body = "[{";
  
        for(String t : bodyImsi) {
            String[] t1 = t.split("=");
            t1[0] = "\"" + t1[0] + "\"";
            t1[1] = "\"" + t1[1] + "\"";
            
            System.out.println(t1[0] + ":" + t1[1]);
            body = body + t1[0] + ":" + t1[1] + ",";
        }
        body = body  + "}]";

        return "";
        
    }

}
