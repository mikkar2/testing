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
        var mapper = new ObjectMapper();
        return mapper.readValue(json, BuildRequest.class);
    }


    public static class Pusher {
        @JsonProperty("email")
        String email;
        @JsonProperty("name")
        String name;

    }
}
