package com.notebookapi.notebook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/hoangtien2k3")
                .username("root")
                .password("12042003")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }


    @Autowired
    private DataSource dataSource;

    public void insertUser(String username, String password, boolean enabled) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("insert into users (username, password, enabled) values (?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setBoolean(3, enabled);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }

}
