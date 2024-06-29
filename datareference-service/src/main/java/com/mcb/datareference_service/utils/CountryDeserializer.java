package com.mcb.datareference_service.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.mcb.datareference_service.dto.Country;

import java.io.IOException;

public class CountryDeserializer extends JsonDeserializer<Country> {
    @Override
    public Country deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec()
                .readTree(jsonParser);

        JsonNode nameNode = node.get("name");
        String commonName = nameNode.get("common").asText();
        String officialName = nameNode.get("official").asText();
        return new Country(commonName, officialName);
    }
}
