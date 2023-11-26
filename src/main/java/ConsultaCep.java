import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ConsultaCep {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Endereco buscaEndereco(String cep) {
        String url = "http://viacep.com.br/ws/" + cep + "/json";
        System.out.println("Url:\n " + url);
        RequestSpecification requestSpecification = given().baseUri(url);
        Response response = requestSpecification.get();
        System.out.println("Response:\n"  + response.asString());
        try {
            return objectMapper.readValue(response.asString(), Endereco.class);
        } catch (JsonProcessingException e) {
            if (response.getBody().jsonPath().getBoolean("erro")) {
                throw new RuntimeException("Cep não encontrado!");
            }
            throw new RuntimeException("Não consegui obter o endereço a partir do cep informado!");
        }
    }
}

