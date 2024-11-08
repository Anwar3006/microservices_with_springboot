package microservices_book.gamification_service.client;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import microservices_book.gamification_service.client.dto.MultiplicationAttempt;

public class MultiplicationAttemptDeserializer extends JsonDeserializer<MultiplicationAttempt>{

    @Override
    public MultiplicationAttempt deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException, JacksonException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new MultiplicationAttempt(node.get("user").get("alias").asText(), 
                                        node.get("multiplication").get("factorA").asInt(), 
                                        node.get("multiplication").get("factorB").asInt(), 
                                        node.get("result").asInt(), 
                                        node.get("correct").asBoolean());
    }
    
}
