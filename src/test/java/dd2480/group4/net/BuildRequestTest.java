package dd2480.group4.net;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildRequestTest {

    @Test
    void fromJson() throws IOException {

        //GIVEN
        String json = "{\"git_url\": \"url\", \"pusher\" : {\"name\": \"foo\", \"email\": \"bar\"}, \"after\": \"commithash\"}";

        //WHEN
        var buildRequest = BuildRequest.fromJson(json);

        //THEN
        assertEquals("foo", buildRequest.pusher.name, "name should be foo");
        assertEquals("bar", buildRequest.pusher.email, "email should be bar");
        assertEquals("commithash", buildRequest.hashId, "hashId should be commithash");
        assertEquals("url", buildRequest.url, "url should be url");


    }
}