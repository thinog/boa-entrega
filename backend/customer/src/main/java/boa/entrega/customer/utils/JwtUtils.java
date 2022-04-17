package boa.entrega.customer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    @SneakyThrows
    public Object getClaim(String token, String claim) {
        if(token == null) {
            return "";
        }

        token = token.replaceAll("[Bb]earer ", "");

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String body = new String(decoder.decode(chunks[1]));

        Map<String, Object> jsonMap = new ObjectMapper().readValue(body, HashMap.class);

        return jsonMap.containsKey(claim) ? jsonMap.get(claim) : "";
    }
}
