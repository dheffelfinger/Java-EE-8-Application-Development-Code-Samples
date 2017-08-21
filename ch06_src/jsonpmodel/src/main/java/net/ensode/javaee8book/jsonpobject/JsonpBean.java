package net.ensode.javaee8book.jsonpobject;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

@Named
@SessionScoped
public class JsonpBean implements Serializable {

    private String jsonStr;

    @Inject
    private Customer customer;

    public String buildJson() {
        JsonObject jsonObject = Json.createObjectBuilder().
                add("firstName", "Scott").
                add("lastName", "Gosling").
                add("email", "sgosling@example.com").
                build();

        StringWriter stringWriter = new StringWriter();

        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.writeObject(jsonObject);
        }

        setJsonStr(stringWriter.toString());

        return "display_json";

    }

    public String parseJson() {
        JsonObject jsonObject;
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonStr))) {
            jsonObject = jsonReader.readObject();
        }

        customer.setFirstName(jsonObject.getString("firstName"));
        customer.setLastName(jsonObject.getString("lastName"));
        customer.setEmail(jsonObject.getString("email"));

        return "display_parsed_json";
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
