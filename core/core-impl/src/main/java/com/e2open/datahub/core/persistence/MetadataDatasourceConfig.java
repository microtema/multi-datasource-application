package com.e2open.datahub.core.persistence;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "e2datahub.metadata.datasource")
public class MetadataDatasourceConfig extends DatasourceConfig {

}
