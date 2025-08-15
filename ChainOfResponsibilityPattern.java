// ChainOfResponsibilityPattern.java
// Requests travel through handlers until one handles it.

abstract class RequestHandler {
    private RequestHandler next;
    public RequestHandler setNext(RequestHandler next) { this.next = next; return next; }

    public String handle(Request req) {
        if (canHandle(req)) return process(req);
        if (next != null) return next.handle(req);
        return "Unhandled";
    }

    protected abstract boolean canHandle(Request req);
    protected abstract String process(Request req);
}

class AuthHandler extends RequestHandler {
    protected boolean canHandle(Request req) { return req.getUser() != null; }
    protected String process(Request req) { return "Auth OK for " + req.getUser(); }
}

class ValidationHandler extends RequestHandler {
    protected boolean canHandle(Request req) { return req.getBody() != null && !req.getBody().isEmpty(); }
    protected String process(Request req) { return "Validated body: " + req.getBody(); }
}

class BusinessHandler extends RequestHandler {
    protected boolean canHandle(Request req) { return true; } // last in chain
    protected String process(Request req) { return "Processed: " + req.getBody(); }
}

class Request {
    private final String user;
    private final String body;
    public Request(String user, String body) { this.user = user; this.body = body; }
    public String getUser() { return user; }
    public String getBody() { return body; }
}

public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        RequestHandler auth = new AuthHandler();
        RequestHandler val = new ValidationHandler();
        RequestHandler biz = new BusinessHandler();

        auth.setNext(val).setNext(biz);

        Request r1 = new Request("alice", "payload");
        System.out.println(auth.handle(r1)); // Auth OK ... (only first handled in this design)

        Request r2 = new Request(null, "payload");
        System.out.println(auth.handle(r2)); // goes to next -> Validated or Unhandled
    }
}
