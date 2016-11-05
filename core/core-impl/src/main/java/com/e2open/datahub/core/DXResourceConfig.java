package com.e2open.datahub.core;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/service")
public class DXResourceConfig extends ResourceConfig {

    public DXResourceConfig() {
        // packages ("com.e2open.datahub");
        // EncodingFilter.enableFor (this, GZipEncoder.class);
        // register (JacksonFeature.class);
        register(AboutResource.class);
    }
}
