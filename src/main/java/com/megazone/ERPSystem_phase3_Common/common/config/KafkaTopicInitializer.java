package com.megazone.ERPSystem_phase3_Common.common.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.time.Duration;
import java.util.*;

//@Component
//public class KafkaTopicInitializer {
//
//    @Value("${spring.kafka.bootstrap-servers}") // Kafka 브로커 주소
//    private String bootstrapServers;
//
//    @Value("${spring.kafka.consumer.group-id}") // 공통 그룹 ID
//    private String groupId;
//
//    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 시 실행
//    public void initializeTopics() {
//        try (AdminClient adminClient = createAdminClient()) {
//            // Kafka 클러스터에서 모든 토픽 가져오기
//            Set<String> topics = adminClient.listTopics(new ListTopicsOptions().timeoutMs(5000)).names().get();
//            System.out.println("Discovered topics: " + topics);
//
//            // 각 토픽에 대해 오프셋 커밋 호출
//            topics.forEach(this::commitOffsetsForTopic);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private AdminClient createAdminClient() {
//        Properties props = new Properties();
//        props.put("bootstrap.servers", bootstrapServers);
//        return AdminClient.create(props);
//    }
//
//    private void commitOffsetsForTopic(String topic) {
//        Properties consumerProps = new Properties();
//        consumerProps.put("bootstrap.servers", bootstrapServers);
//        consumerProps.put("group.id", groupId);
//        consumerProps.put("key.deserializer", StringDeserializer.class.getName());
//        consumerProps.put("value.deserializer", StringDeserializer.class.getName());
//        consumerProps.put("auto.offset.reset", "latest");
//        consumerProps.put("enable.auto.commit", "false"); // 자동 커밋 비활성화
//
//        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps)) {
//            consumer.subscribe(Collections.singletonList(topic));
//
//            // 파티션 할당을 받기 위해 초기 poll 호출
//            consumer.poll(Duration.ofMillis(100));
//
//            // 할당된 파티션 가져오기
//            Set<TopicPartition> assignedPartitions = consumer.assignment();
//            if (assignedPartitions.isEmpty()) {
//                consumer.poll(Duration.ofMillis(100)); // 다시 poll 호출하여 파티션 할당 받기
//                assignedPartitions = consumer.assignment();
//            }
//
//            // 각 파티션의 끝으로 이동
//            consumer.seekToEnd(assignedPartitions);
//
//            // 현재 위치의 오프셋 가져오기
//            Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
//            for (TopicPartition partition : assignedPartitions) {
//                long position = consumer.position(partition);
//                offsets.put(partition, new OffsetAndMetadata(position));
//            }
//
//            // 오프셋 커밋
//            consumer.commitSync(offsets);
//
//            System.out.println("Committed offsets for topic: " + topic);
//        } catch (Exception e) {
//            System.err.println("Failed to commit offsets for topic: " + topic);
//            e.printStackTrace();
//        }
//    }
//}