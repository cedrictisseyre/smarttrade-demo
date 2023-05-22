package com.smarttrade.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.vavr.gson.VavrGson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.Instant;

@Configuration
public class GsonConfiguration {
    @Bean
    public Gson gson() {
        GsonBuilder builder = new GsonBuilder()
            .registerTypeAdapterFactory(new RecordTypeAdapterFactory())
            // specific type adapters for specific records should be referenced after the RecordTypeAdapterFactory
            .registerTypeAdapter(Instant.class, new InstantDeserializer());
        VavrGson.registerAll(builder);
        return builder.create();
    }

    private static class InstantDeserializer implements JsonDeserializer<Instant>, JsonSerializer<Instant> {
        @Override
        public JsonElement serialize(Instant src, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }

        @Override
        public Instant deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
            return Instant.parse(json.getAsJsonPrimitive().getAsString());
        }
    }
}
