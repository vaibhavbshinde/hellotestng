package basepackages.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Msg",
        "ID"
})
public class AddBookModel {
    @JsonProperty("Msg")
    private String msg;
    @JsonProperty("ID")
    private String id;

    @JsonProperty("Msg")
    public String getMsg() {
        return msg;
    }

    @JsonProperty("Msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(String id) {
        this.id = id;
    }

}