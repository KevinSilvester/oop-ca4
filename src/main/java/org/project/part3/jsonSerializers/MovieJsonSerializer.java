package org.project.part3.jsonSerializers;

import com.google.gson.*;
import org.javatuples.Pair;
import org.project.part3.DTOs.Movie;

import java.lang.reflect.Type;

public class MovieJsonSerializer implements JsonSerializer<Pair<Integer, Movie>> {

   @Override
   public JsonElement serialize(Pair<Integer, Movie> obj, Type type, JsonSerializationContext jsc) {
      JsonObject jsonObj = new JsonObject();
      jsonObj.addProperty("id", obj.getValue0());
      jsonObj.addProperty("title", obj.getValue1().getTitle());
      jsonObj.addProperty("year", obj.getValue1().getYear());
      jsonObj.addProperty("boxOffice", obj.getValue1().getBoxOffice());
      jsonObj.addProperty("director", obj.getValue1().getDirector());
      jsonObj.addProperty("leadActor", obj.getValue1().getLeadActor());
      return jsonObj;
   }
}
