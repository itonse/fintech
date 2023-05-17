package com.example.consumer.kafka

import com.example.kafka.dto.LoanRequestDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class LoanRequestConsumer(
    private val objectMapper: ObjectMapper  // 스트링으로 받게 되니
) {
    @KafkaListener(topics = ["loan_request"], groupId = "fintech")
    fun loanRequestTopicConsumer(message: String) {    // api, consumer Application 을 올리고 kafka 토픽을 발행하면, 계속 토픽을 consume 하게됨.
        val loanRequestKafkaDto = objectMapper.readValue(message, LoanRequestDto::class.java)
    }
}