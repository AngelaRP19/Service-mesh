package uptc.edu.swii.login.controller;

import uptc.edu.swii.login.model.Login;
import uptc.edu.swii.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logins")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity<List<Login>> getAllLogins() {
        return ResponseEntity.ok(loginService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Login> getLoginById(@PathVariable Long id) {
        Login login = loginService.findById(id);
        return login != null ? ResponseEntity.ok(login) : ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{customerid}")
    public ResponseEntity<Login> getLoginByCustomerId(@PathVariable String customerid) {
        Login login = loginService.findByCustomerId(customerid);
        return login != null ? ResponseEntity.ok(login) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {
        Login createdLogin = loginService.save(login);
        return ResponseEntity.ok(createdLogin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Login> updateLogin(@PathVariable Long id, @RequestBody Login login) {
        Login updatedLogin = loginService.update(id, login);
        return updatedLogin != null ? ResponseEntity.ok(updatedLogin) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogin(@PathVariable Long id) {
        boolean deleted = loginService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        String customerid = credentials.get("customerid");
        String password = credentials.get("password");
        
        boolean isAuthenticated = loginService.auth(customerid, password);
        if (isAuthenticated) {
            return ResponseEntity.ok().body(Map.of("message", "Authenticated successfully"));
        }
        return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
    }
}
