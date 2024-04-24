package CheckToken;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {
    public boolean isValid;
    public List<String> causes;

    public ValidationResponse() {
        this.causes = new ArrayList<>();
        isValid = true;
    }
}
