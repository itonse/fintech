package com.example.kafka.dto

data class LoanRequestDto(   // 아래 항목들을 Kafka 를 통해서 전달을 받고, 이것을 CB 사와 금융 사로 넘김
    val userKey: String,   // 유저키
    val userName: String,  // 유저 이름
    val userIncomeAmount: Long,   // 유저 소득금액
    var userRegistrationNumber: String   // 유저 주민번호
)
