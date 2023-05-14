package com.example.api.loan

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GenerateKey {   // 키 생성
    fun generateUserKey() = UUID.randomUUID().toString().replace("-", "")  // '-' 를 제거한 랜덤 UUID 값 생성
}