package dd2480.group4;
import dd2480.group4.execute.Runner;
import dd2480.group4.net.RequestHandler;
import org.eclipse.jetty.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(8004);
        try {
            System.out.println("1");
            server.setHandler(new RequestHandler(new Runner()));
            System.out.println("2");
            server.start();
            System.out.println("3");
            server.join();
            System.out.println("4");
        } catch(Exception e) {
            System.err.println("dafuq");
        }
    }
}
