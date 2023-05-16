package com.example.api.exception

import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

class ErrorResponse (   // 에러 응답
    private val customException: CustomException
) {
    fun toResponseEntity(): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity  // 상태코드, 에러코드, 에러메세지를 담은 응답 반환
            .status(customException.customErrorCode.statusCode)
            .body(
                ErrorResponseDto(
                    errorCode = customException.customErrorCode.errorCode,
                    errorMessage = customException.customErrorCode.errorMessage
                )
            )
    }

    data class ErrorResponseDto(  // 에러는 공통적으로 이 형태로 나간다.
        val errorCode: String,
        val errorMessage: String
    ) {
        val timeStamp = LocalDateTime.now()   // 시간 찍기
    }
}