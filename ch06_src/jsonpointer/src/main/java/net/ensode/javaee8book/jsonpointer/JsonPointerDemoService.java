package net.ensode.javaee8book.jsonpointer;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("jsonpointer")
public class JsonPointerDemoService {

    private String jsonString;

    @GET
    public String jsonPointerDemo() {
        initializeJsonString();
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonArray jsonArray = jsonReader.readArray();
        JsonPointer jsonPointer = Json.createPointer("/1/lastName");
        
        return jsonPointer.getValue(jsonArray).toString();
    }

    private void initializeJsonString() {
        jsonString = "[\n"
                + "  {\n"
                + "    \"dateOfBirth\": \"1997-01-01\",\n"
                + "    \"firstName\": \"David\",\n"
                + "    \"lastName\": \"Delabassee\",\n"
                + "    \"salutation\": \"Mr\"\n"
                + "  },\n"
                + "  {\n"
                + "    \"dateOfBirth\": \"1997-03-03\",\n"
                + "    \"firstName\": \"David\",\n"
                + "    \"lastName\": \"Heffelfinger\",\n"
                + "    \"middleName\": \"Raymond\",\n"
                + "    \"salutation\": \"Mr\"\n"
                + "  }\n"
                + "]";
    }
}
