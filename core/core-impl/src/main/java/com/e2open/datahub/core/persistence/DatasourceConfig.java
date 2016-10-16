package com.e2open.datahub.core.persistence;

public class DatasourceConfig {

    /**
     * Configuration mode
     */
    private String configmode = "simple";

    /**
     * Name of the datasource.
     */
    private String name = "testdb";

    /**
     * Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
     */
    private String driverClassName;

    /**
     * JDBC url of the database.
     */
    private String url;

    /**
     * Login user of the database.
     */
    private String username;

    /**
     * Login password of the database.
     */
    private String password;

    /**
     * Port of the database
     */
    private int port;

    public String getConfigmode() {
        return configmode;
    }

    public void setConfigmode(String configmode) {
        this.configmode = configmode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
