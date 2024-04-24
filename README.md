## Check token
Esta aplicação foi desenvolvida com Java e Spring Boot com o intuito de ser uma API REST para validação de token jwt. 

Para executar este projeto basta rodar ```docker-compose up``` na mesma pasta em que estão o Dockerfile e docker-compose.yml, certifique-se que a porta 8080 da sua máquina não está sendo utilizada por outro processo.

E para testar a funcionalidade rode o seguinte curl de exemplo, que bate em um endpoint \POST localhost:8080. Para testar tokens específicos basta trocar a string jwt pelo seu token.

```
curl --location 'localhost:8080' \
--header 'Content-Type: application/json' \
--data '{
    "jwt":"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"
}'
```
