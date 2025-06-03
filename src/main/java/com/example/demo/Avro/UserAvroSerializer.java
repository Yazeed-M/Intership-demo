package com.example.demo.Avro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.common.serialization.Serializer;

import user.user;

public class UserAvroSerializer implements Serializer<user>{   
    public UserAvroSerializer(){}
  @Override
    public byte[] serialize(String topic, user data){
        DatumWriter<user> writer= new SpecificDatumWriter<>(user.class);
        byte[] dataToByte = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder=null;
        try{
            jsonEncoder=EncoderFactory.get().jsonEncoder(user.getClassSchema(), stream);
            writer.write(data, jsonEncoder);
            jsonEncoder.flush();
            dataToByte=stream.toByteArray();
        }catch(IOException e){
            System.err.print((e.getMessage()));
        }
        return dataToByte;
    }

 
}
