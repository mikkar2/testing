package dd2480.group4.net;

import dd2480.group4.execute.Executor;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestHandlerTest {

    @Test
    public void doHandle() throws Exception {
        var executor = new Executor() {
            public BuildRequest request;

            @Override
            public void runBuild(BuildRequest req) {
                this.request = req;

            }
        };
        var server = newServer(executor);
        var http = httpPost(server, "{\"git_url\": \"url\", \"pusher\" : {\"name\": \"foo\", \"email\": \"bar\"}, \"after\": \"commithash\"}");
        assertEquals(http.getResponseCode(), HttpStatus.OK_200, "response code should be OK");
        assertEquals("foo", executor.request.pusher.name, "Expected json name to be set to foo");

        server.stop();
    }

    private Server newServer(Executor executor) {
        var rng = ThreadLocalRandom.current();
        var port = rng.nextInt(1000) + 8000;
        Server server = new Server(port);
        server.setHandler(new RequestHandler(executor));
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;
    }

    private HttpURLConnection httpPost(Server server, String json) throws IOException {
        byte[] data = json.getBytes();
        HttpURLConnection http = (HttpURLConnection) new URL("http", "localhost", server.getURI().getPort(), "/").openConnection();
        http.setDoOutput(true);
        http.setInstanceFollowRedirects(false);
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("charset", "utf-8");
        http.setRequestProperty("Content-Length", Integer.toString(data.length));
        http.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(http.getOutputStream())) {
            wr.write(data);
        }
        return http;
    }
}