package homeWorkTen.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Override
    public String toString() {
        return "User{" +
                "data=" + data +
                '}';
    }
    @JsonProperty("data")
    private Data data;

    public Data getData() {
        return data;
    }


}