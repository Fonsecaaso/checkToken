package CheckToken;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class CheckTokenService {
    public CheckTokenService() {
    }

    public ValidationResponse validateJWT(String jwt) {
        JSONObject claims = decodeJWT(jwt);
        return ClaimsAreValid(claims);
    }

    private static JSONObject decodeJWT(String jwt) {
        try {
            String[] chunks = jwt.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            return new JSONObject(new String(decoder.decode(chunks[1])));
        }catch (Exception ex){
            return new JSONObject();
        }
    }

    private ValidationResponse ClaimsAreValid(JSONObject claims) {
        ValidationResponse response = new ValidationResponse();
        if(claims.length()==0) {
            response.causes.add("invalid token");
            response.isValid = false;
            return response;
        }
        if(claims.length()!=3) {
            response.causes.add("invalid claims quantity");
            response.isValid = false;
        }

        if(!validRole(claims)) {
            response.causes.add("invalid role");
            response.isValid = false;
        }
        if(!validName(claims)) {
            response.causes.add("invalid name");
            response.isValid = false;
        }
        if(!validSeed(claims)) {
            response.causes.add("invalid seed");
            response.isValid = false;
        }

        return response;
    }

    private boolean validSeed(JSONObject claims) {
        if(!claims.has("Seed")) return false;
        int seed = claims.getInt("Seed");
        boolean isPrime = true;
        for (int i = 2; i <= seed / 2; ++i) {
            if (seed % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    private boolean validRole(JSONObject claims) {
        Set<String> set = new HashSet<>();
        for(Role role:Role.values()){
            set.add(role.name());
        }
        if(!claims.has("Role")) return false;
        String role = claims.getString("Role").toUpperCase(Locale.ROOT);
        return set.contains(role);
    }

    private boolean validName(JSONObject claims) {
        if(!claims.has("Name")) return false;
        String name = claims.getString("Name");
        if(name.length()>256) return false;
        if(name.matches(".*[0-9].*")) return false;
        return true;
    }
}
