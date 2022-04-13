package boa.entrega.supplier.utils;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class HashUtils {
    public String generateUUIDv4() {
        return UUID.randomUUID().toString();
    }

    public String generateHashSha256(String text) {
        return Hashing.sha256()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }
}
