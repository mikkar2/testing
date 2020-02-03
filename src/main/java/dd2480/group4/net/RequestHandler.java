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
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        var bytes = request.getInputStream().readAllBytes();
        var json = new String(bytes, StandardCharsets.UTF_8);
        var buildRequest =  BuildRequest.fromJson(json);
        executor.runBuild(buildRequest);
        baseRequest.setHandled(true);
        response.getWriter().println(json);
    }
}
