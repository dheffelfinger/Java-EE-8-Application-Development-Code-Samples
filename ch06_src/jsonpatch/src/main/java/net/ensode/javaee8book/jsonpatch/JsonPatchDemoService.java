package net.ensode.javaee8book.jsonpatch;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonPatch;
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("jsonpatch")
public class JsonPatchDemoService {

    private String jsonString;

    @GET
    public Response jsonPatchDemo() {
        initializeJsonString();
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonArray jsonArray = jsonReader.readArray();
        JsonPatch jsonPatch = Json.createPatchBuilder()
                .replace("/1/dateOfBirth", "1977-01-01")
                .build();
        JsonArray modifiedJsonArray =jsonPatch.apply(jsonArray);

        return Response.ok(modifiedJsonArray.toString(), MediaType.APPLICATION_JSON).build();
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
