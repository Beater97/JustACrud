package com.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "lotus-connection")
@Getter
@Setter
public class DatabaseConfigProperties {
    private String path;
	private String username;
	private String password;
	private String driver;// = "com.mysql.cj.jdbc.Driver";
}
