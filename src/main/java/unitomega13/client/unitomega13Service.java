package unitomega13.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("unitomega13Service")
public interface unitomega13Service extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use unitomega13Service.App.getInstance() to access static instance of unitomega13ServiceAsync
     */
    public static class App {
        private static unitomega13ServiceAsync ourInstance = GWT.create(unitomega13Service.class);

        public static synchronized unitomega13ServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
