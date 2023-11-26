import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeArquivo {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);
    public void salvarJson(Endereco endereco) throws IOException {
        FileWriter escrita = new FileWriter(endereco.cep() + ".json");
        objectMapper.writeValue(escrita, endereco);
        escrita.close();
    }

}
