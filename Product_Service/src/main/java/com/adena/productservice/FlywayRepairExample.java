package com.adena.productservice;

import org.flywaydb.core.Flyway;

public class FlywayRepairExample {
    public static void main(String[] args) {
        // Create Flyway instance
        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/productservice", "masteruser", "password").load();

        // Repair the schema history table
        flyway.repair();
    }
}
