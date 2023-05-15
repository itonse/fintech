package com.example.api.loan.review

class LoanReviewDto {
    data class LoanReviewResponseDto(
        val userKey: String,
        val loanResult: LoanResult   // 대출 결과
    )

    data class LoanResult(
        val userLimitAmount: Long,   // 대출 한도
        val userLoanInterest: Double  // 대출 이자율
    )

    data class LoanReview(
        val userKey : String,
        val userLimitAmount: Long,
        val userLoanInterest: Double
    )
}