package CheckToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CheckTokenController {
    @Autowired
    private final CheckTokenService checkTokenService;

    public CheckTokenController(CheckTokenService checkTokenService) {
        this.checkTokenService = checkTokenService;
    }

    @PostMapping
    public boolean validateJWT(@RequestBody String jwt) {
        return checkTokenService.validateJWT(jwt).isValid;
    }
}
