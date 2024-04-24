package CheckToken;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckTokenServiceTest {
    @InjectMocks
    private CheckTokenService checkTokenService;

    @Test
    public void TestaTokenValido(){
        // arrange
        var jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        // act
        ValidationResponse res = checkTokenService.validateJWT(jwt);
        // assert
        assertTrue(res.isValid);
    }

    @Test
    public void TestaTokenInvalido(){
        // arrange
        var jwt = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        // act
        ValidationResponse res = checkTokenService.validateJWT(jwt);
        // assert
        assertTrue(res.causes.contains("invalid token"));
        assertFalse(res.isValid);
    }

    @Test
    public void TestaSeedInvalido(){
        // arrange
        var jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiMTAwMCIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.WwlsCHQ0QQT3B5o4tghr5GOIMHfZYS3fA9_xapmkddc";
        // act
        ValidationResponse res = checkTokenService.validateJWT(jwt);
        // assert
        assertTrue(res.causes.contains("invalid seed"));
        assertFalse(res.isValid);
    }


    @Test
    public void TestaNome_CaractereInvalido(){
        // arrange
        var jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";
        // act
        ValidationResponse res = checkTokenService.validateJWT(jwt);
        // assert
        assertTrue(res.causes.contains("invalid name"));
        assertFalse(res.isValid);
    }

    @Test
    public void TestaNome_MaisDe256Caracteres(){
        // arrange
            var jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiUGVkcm8gZGUgQWxjw6JudGFyYSBGcmFuY2lzY28gQW50w7RuaW8gSm_Do28gQ2FybG9zIFhhdmllciBkZSBQYXVsYSBNaWd1ZWwgUmFmYWVsIEpvYXF1aW0gSm9zw6kgR29uemFnYSBQYXNjb2FsIENpcHJpYW5vIFNlcmFmaW0gZGUgQnJhZ2Fuw6dhIGUgQm91cmJvbiBQZWRybyBkZSBBbGPDom50YXJhIEZyYW5jaXNjbyBBbnTDtG5pbyBKb8OjbyBDYXJsb3MgWGF2aWVyIGRlIFBhdWxhIE1pZ3VlbCBSYWZhZWwgSm9hcXVpbSBKb3PDqSBHb256YWdhIFBhc2NvYWwgQ2lwcmlhbm8gU2VyYWZpbSBkZSBCcmFnYW7Dp2EgZSBCb3VyYm9uIn0.80zZOcqbKiLgjxIcpIB3E63WpsGk5E2sUtuhwOCDIMo";
        // act
        ValidationResponse res = checkTokenService.validateJWT(jwt);
        // assert
        assertTrue(res.causes.contains("invalid name"));
        assertFalse(res.isValid);
    }

    @Test
    public void TestaRoleInvalida(){
        // arrange
        var jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiZGV2IiwiU2VlZCI6Ijg4MDM3IiwiTmFtZSI6Ik1hcmlhIE9saXZpYSJ9.SxKiDCyBHYAg8nZphhqkoCLUrpz5tV-BZPJ3V5Wt4sM";
        // act
        ValidationResponse res = checkTokenService.validateJWT(jwt);
        // assert
        assertFalse(res.isValid);
        assertTrue(res.causes.contains("invalid role"));
    }

    @Test
    public void TestaToken_MaisDe3Claims(){
        // arrange
        var jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";
        // act
        ValidationResponse res = checkTokenService.validateJWT(jwt);
        // assert
        assertTrue(res.causes.contains("invalid claims quantity"));
        assertFalse(res.isValid);
    }

}