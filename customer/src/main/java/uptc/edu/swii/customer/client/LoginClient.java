package uptc.edu.swii.customer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class LoginClient {

    private static final Logger logger = LoggerFactory.getLogger(LoginClient.class);

    private final RestTemplate restTemplate;
    private final String loginServiceUrl;

    public LoginClient(RestTemplate restTemplate,
                       @Value("${login.service.url}") String loginServiceUrl) {
        this.restTemplate = restTemplate;
        this.loginServiceUrl = loginServiceUrl;
    }

    public void registerLogin(String customerId, String password) {
        try {
            Map<String, String> loginPayload = Map.of("customerid", customerId, "password", password);
            restTemplate.postForObject(loginServiceUrl + "/api/logins", loginPayload, Object.class);
            logger.info("Login registered for customer: {}", customerId);
        } catch (Exception e) {
            logger.error("Failed to register login for customer {}", customerId, e);
        }
    }
}
