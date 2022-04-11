package org.project.part3.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.javatuples.Pair;
import org.project.part3.DTOs.Movie;

import java.lang.reflect.Type;

public class MovieJsonDeserializer implements JsonDeserializer<Pair<Integer, Movie>> {
   @Override
   public Pair<Integer, Movie> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsc) throws JsonParseException {
      return Pair.with(jsonElement.getAsJsonObject().get("id").getAsInt(), jsc.deserialize(jsonElement, Movie.class));
   }
}
