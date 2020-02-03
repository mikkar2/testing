package dd2480.group4.net;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Defines the structure of a request to build the project
 * TODO: should model the repository at a given state (i.e. url, commit hash, branch, time and date)
 */
public class BuildRequest {

    @JsonProperty("git_url")
    String url;
    @JsonProperty("after")
    String hashId;
    @JsonProperty("pusher")
    Pusher pusher;

    public static BuildRequest fromJson(String json) throws IOException {
        System.out.println("1.2");
        var mapper = new ObjectMapper();
        System.out.println("2.2");
        BuildRequest a = mapper.readValue(json, BuildRequest.class); //jidder here
        System.out.println("3.2");
        return a;
    }


    public static class Pusher {
        @JsonProperty("email")
        String email;
        @JsonProperty("name")
        String name;

    }
}
