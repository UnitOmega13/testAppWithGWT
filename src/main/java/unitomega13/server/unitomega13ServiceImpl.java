package unitomega13.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import unitomega13.client.unitomega13Service;

public class unitomega13ServiceImpl extends RemoteServiceServlet implements unitomega13Service {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}