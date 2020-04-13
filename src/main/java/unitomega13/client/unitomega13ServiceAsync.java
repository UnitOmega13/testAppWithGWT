package unitomega13.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface unitomega13ServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
