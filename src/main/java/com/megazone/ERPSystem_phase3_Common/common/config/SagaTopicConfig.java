package com.megazone.ERPSystem_phase3_Common.common.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Configuration
public class SagaTopicConfig {

    @Bean
    public CommandLineRunner ensureSagaStartTopicExists(KafkaAdmin kafkaAdmin) {
        return args -> {
            String topicName = "saga-start";

            try {
                // KafkaAdmin은 Spring Kafka가 자동으로 구성된 AdminClient를 사용
                AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());

                ListTopicsOptions options = new ListTopicsOptions();
                options.listInternal(false);
                Set<String> existingTopics = adminClient.listTopics(options).names().get();

                if (!existingTopics.contains(topicName)) {
                    NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
                    adminClient.createTopics(Collections.singleton(newTopic));
                    System.out.println("Topic " + topicName + " created.");
                } else {
                    System.out.println("Topic " + topicName + " already exists.");
                }

                adminClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
