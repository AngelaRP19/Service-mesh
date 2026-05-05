package uptc.edu.swii.login.service;

import uptc.edu.swii.login.model.Login;
import uptc.edu.swii.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    public Login findById(Long id) {
        return loginRepository.findById(id).orElse(null);
    }

    public Login findByCustomerId(String customerid){
        return loginRepository.findByCustomerid(customerid).orElse(null);
    }

    public Login save(Login login){
        login.setId(null);
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        return loginRepository.save(login);
    }

    public Login update(Long id, Login loginDetails) {
        Optional<Login> optional = loginRepository.findById(id);
        if (optional.isPresent()) {
            Login existingLogin = optional.get();
            existingLogin.setCustomerid(loginDetails.getCustomerid());
            existingLogin.setPassword(passwordEncoder.encode(loginDetails.getPassword()));
            return loginRepository.save(existingLogin);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (loginRepository.existsById(id)) {
            loginRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean auth(String customerid, String password){
        Login login = findByCustomerId(customerid);
        if(login != null && passwordEncoder.matches(password, login.getPassword())){
            return true;
        }
        return false;
    }
}
