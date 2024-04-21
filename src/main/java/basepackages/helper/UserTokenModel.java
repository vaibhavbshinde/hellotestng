package basepackages.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username",
        "password",
        "token"
})
public class UserTokenModel {

    @JsonProperty("token")
    private String token;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;


    //set method
    @JsonProperty("token")
    public void setToken(String token){
        this.token = token;
    }

    //get method
    @JsonProperty("token")
    public String getToken(){
        return token;
    }

    //set method
    @JsonProperty("username")
    public  void setUsername(String username){
        this.username = username;
    }

    //get method
    @JsonProperty("username")
    public String getUsername(){
        return username;
    }

    //set method
    @JsonProperty("password")
    public  void setPassword(String password){
        this.password = password;
    }

    //get method
    @JsonProperty("password")
    public String getPassword(){
        return password;
    }
}
