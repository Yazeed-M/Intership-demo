package com.example.demo.kafka;

import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;
// import org.springframework.kafka.core.KafkaTemplate;
@Component
public class KafkaConfig {
    // @Autowired    
    // private final KafkaTemplate kafkaTemplate;
    
    // public KafkaConfig(KafkaTemplate kafkaTemplate) {
    //     this.kafkaTemplate = kafkaTemplate;
    // }
    @Bean
    public NewTopic topic(){
         return TopicBuilder.name("avroTestingT").build();
    }
}
