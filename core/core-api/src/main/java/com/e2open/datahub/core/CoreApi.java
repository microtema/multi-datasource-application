package com.e2open.datahub.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CoreApi {

    private static final Logger LOGGER = LogManager.getLogger(CoreApi.class);

    @Value("${com.e2open.datahub.core}")
    private String core;

    @PostConstruct
    public void init() {
        LOGGER.debug(this);
    }
}
