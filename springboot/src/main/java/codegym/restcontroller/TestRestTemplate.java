package codegym.restcontroller;

import codegym.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class TestRestTemplate {
    static final String URL_GET_ALL_USER = "http://localhost:8080/api/user/list";
    static final String URL_CREATE_USER = "http://localhost:8080/api/user/create";
    public static void main(String[] args) {
        getAllUser();
//        saveUser();
    }

    public static void getAllUser() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL_GET_ALL_USER, String.class);
        System.out.println(result);
    }

    public static void saveUser() {
        User user = new User(0, "Thảo Nguyên", 25, "Đà Nẵng");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<User> requestBody = new HttpEntity<User>(user, headers);
        String result = restTemplate.postForObject(URL_CREATE_USER, requestBody, String.class);
        System.out.println(result);

    }
}
