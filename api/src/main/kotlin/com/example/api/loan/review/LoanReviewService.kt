package com.example.api.loan.review

interface LoanReviewService {
    fun loanReviewMain(userKey: String): LoanReviewDto.LoanReviewResponseDto  // 응답값을 DTO 타입으로

    fun getLoanResult(userKey: String): LoanReviewDto.LoanReview // 대출 결과 가져오기
}