package springboot.training.oauthclientapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Controller
public class EmployeeController {

    @RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
    public ModelAndView getEmployeeInfo(){
        return new ModelAndView("getEmployees");
    }

    @RequestMapping(value = "/showEmployees",method = RequestMethod.GET)
    public ModelAndView showEmployees(@RequestParam("code") String code)
            throws JsonProcessingException, IOException {
        ResponseEntity<String> response = null;
       log.info("Authorization Code------" + code);

        RestTemplate restTemplate = new RestTemplate();

        //// According OAuth documentation we need to send the
        // client id and secret key in the header for authentication
        String credentials = "cnoriega:secret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        // HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> request = new HttpEntity<String>(headers);

        String access_token_url = "http://localhost:8080/oauth/token";
        access_token_url += "?code=" + code;
        access_token_url += "&grant_type=authorization_code";
        access_token_url += "&redirect=http://localhost:8090/showEmployees";

        response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        log.info("Access Token Response --------- " + response.getBody());

        return null;
    }
}
