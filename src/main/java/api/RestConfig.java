package api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("v1")
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        packages(this.getClass().getPackage().getName());
        register(JacksonFeature.class);
    }
}