package server.config;

import java.awt.event.FocusEvent;

public class HttpConfigurationException extends RuntimeException {
    public HttpConfigurationException(String message){
        super(message);
    }
    public HttpConfigurationException(String message, Throwable event){
        super(message, event);
    }
    public HttpConfigurationException(Throwable event){
        super(event);
    }

}
