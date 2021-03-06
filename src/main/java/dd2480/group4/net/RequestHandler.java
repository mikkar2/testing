package dd2480.group4.net;

import dd2480.group4.execute.Executor;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * A class which defines the handlers and endpoints for the web hooks
 */
public class RequestHandler extends AbstractHandler {

    Executor executor;

    public RequestHandler(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException
    {
        System.out.println("1.1");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        var bytes = request.getInputStream().readAllBytes();
        System.out.println("2.1");
        var json = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("3.1");
        var buildRequest =  BuildRequest.fromJson(json);
        System.out.println("4.1");
        System.out.println("ID: " + buildRequest.hashId );
        System.out.println("email: " + buildRequest.pusher.email );
        System.out.println("url: " + buildRequest.url );
        System.out.println("name: " + buildRequest.pusher.name );
        executor.runBuild(buildRequest);
        baseRequest.setHandled(true);
        response.getWriter().println(json);
    }
}
