package com.e2open.datahub.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CoreApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreApi.class);

    @Value("${com.e2open.datahub.core.api}")
    private String property;

    @PostConstruct
    public void init() {
        LOGGER.info("init: " + property);
    }
}
