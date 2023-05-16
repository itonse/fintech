package com.example.api.loan.review

import com.example.domain.domain.LoanReview

interface LoanReviewService {
    fun loanReviewMain(userKey: String): LoanReviewDto.LoanReviewResponseDto  // 응답값을 DTO 타입으로

    fun getLoanResult(userKey: String): LoanReview? // 대출 결과 가져오기

}