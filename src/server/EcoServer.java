import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class EcoServer {
    public static final int PORT = 8080;
    private EcoConnection conn;
    private HttpServer server;

    public static void main(String[] args) throws Exception {
        EcoServer s = new EcoServer();
        s.server.start();
        System.out.println("Eco server is running on port " + PORT);
    }

    public EcoServer() throws Exception {
        this.server = HttpServer.create(new InetSocketAddress(PORT), 0);
        this.conn = new EcoConnection();

        server.createContext("/", (HttpExchange t) -> {
            String response = "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Chat Server API</title>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }" +
                "h1 { color: #333; }" +
                "p { background: #fff; padding: 10px; border-radius: 5px; }" +
                "code { background: #eee; padding: 3px; border-radius: 3px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<h1>Welcome to the Eco Server API</h1>" +
                "<p>Use the API endpoints below:</p>" +
                "<ul>" +
                "<li><b>/signup?id=1&username=Bob&password=pass</b> - sign up</li>"+
                "</ul>" +
                "</body></html>";
            sendResponse(t, response);
        });

        server.createContext("/signup", (HttpExchange t) -> {
            Map<String, String> input = queryToMap(t);
            sendResponse(t, conn.signup(Integer.parseInt(input.get("id")),input.get("username"),input.get("password")));
        });
        server.setExecutor(null);
    }

    public static Map<String, String> queryToMap(HttpExchange t) {
        String query = t.getRequestURI().getRawQuery();
        Map<String, String> result = new HashMap<>();
        if (query == null) return result;
        for (String param : query.split("&")) {
            String[] entry = param.split("=", 2);
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

    private void sendResponse(HttpExchange t, String response) throws IOException {
        byte[] bytes = response.getBytes();
        t.sendResponseHeaders(200, bytes.length);
        OutputStream os = t.getResponseBody();
        os.write(bytes);
        os.close();
    }
}