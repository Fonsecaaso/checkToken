package CheckToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CheckTokenController {
    @Autowired
    private final CheckTokenService checkTokenService;

    private static final Logger log = LoggerFactory.getLogger(CheckTokenController.class);

    public CheckTokenController(CheckTokenService checkTokenService) {
        this.checkTokenService = checkTokenService;
    }

    @PostMapping
    public boolean validateJWT(@RequestBody String jwt) {
        boolean isValid = checkTokenService.validateJWT(jwt).isValid;
        log.info(String.format("token %s is %s", jwt, isValid ? "valid" : "invalid"));
        return isValid;
    }
}
