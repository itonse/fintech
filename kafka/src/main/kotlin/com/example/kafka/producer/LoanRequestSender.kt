package com.example.kafka.producer

import com.example.kafka.dto.LoanRequestDto
import com.example.kafka.enum.KafkaTopic
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class LoanRequestSender(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {
    fun sendMessage(topic: KafkaTopic,loanRequestDto: LoanRequestDto) {  // Kafka 로 메세지를 전송
        kafkaTemplate.send(topic.topicName, objectMapper.writeValueAsString(loanRequestDto))  // topic 과 data class 를 받아서, Serialize 하여 보냄.
    }
}