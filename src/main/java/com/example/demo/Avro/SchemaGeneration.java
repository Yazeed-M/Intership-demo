package com.example.demo.Avro;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class SchemaGeneration {

    public static Schema userSchema = SchemaBuilder.record("user")
            .namespace("user")
            .fields()
            .requiredString("name")
            .requiredString("password")
            .endRecord();

    @PostConstruct
    public void printJson() {
        String jsonFormatForUser = userSchema.toString(true);
        System.out.print(jsonFormatForUser);
    }
}
