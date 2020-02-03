package dd2480.group4;
import dd2480.group4.execute.Runner;
import dd2480.group4.net.RequestHandler;
import org.eclipse.jetty.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(8004);
        try {
            server.setHandler(new RequestHandler(new Runner()));
            server.start();
            server.join();
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}
