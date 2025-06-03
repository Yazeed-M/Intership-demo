package com.example.demo.Avro;

import java.io.IOException;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import user.user;

public class UserAvroDeserializer implements Deserializer<user> {

    public UserAvroDeserializer() {
    }

    @Override
    public user deserialize(String topic, byte[] data) {
        DatumReader<user> reader = new SpecificDatumReader<>(user.class);
        Decoder decoder = null;
        try {
            decoder = DecoderFactory.get().jsonDecoder(user.getClassSchema(),
                    new String(data));
            return reader.read(null, decoder);
        } catch (IOException e) {
            e.printStackTrace(); // Add logging here
            throw new SerializationException("Failed to deserialize", e);

        }
    }

}
