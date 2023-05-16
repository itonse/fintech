package com.example.api.loan.review

import com.example.api.exception.CustomException
import com.example.api.exception.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [LoanReviewController::class])  // body 필요 (LoanReviewController 에서만 한정된 에러)
class LoanReviewControllerAdvice {   // LoanReviewController 에서 발생한 익셉션들이 오게 됨

    @ExceptionHandler(CustomException::class)  // 특정 익셉션 처리
    fun customExceptionHandler(customException: CustomException) =
        ErrorResponse(customException).toResponseEntity()  // 맵핑을 한 응답을 받음
}