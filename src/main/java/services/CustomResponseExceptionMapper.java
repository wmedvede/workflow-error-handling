package services;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class CustomResponseExceptionMapper implements ResponseExceptionMapper {
    @Override
    public Throwable toThrowable(Response response) {
        return null;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean handles(int status, MultivaluedMap headers) {
        return false;
//        return ResponseExceptionMapper.super.handles(status, headers);
    }
}
