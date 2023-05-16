package com.example.api.exception

import org.springframework.http.HttpStatus

// 1. 어떤 status 의 응답을 줄 것인지 (ex 404)
// 2. 같은 status 라도, 다른 에러일 수 있음
enum class CustomErrorCode (
    val statusCode: HttpStatus,  // 상태 코드
    val errorCode: String,   // 에러 코드
    val errorMessage: String   // 에러 메시지
) {
    RESULT_NOT_FOUND(HttpStatus.BAD_REQUEST, "E001", errorMessage = "result not found")

}