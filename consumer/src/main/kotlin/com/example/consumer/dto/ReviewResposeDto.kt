package com.example.consumer.dto

import com.example.domain.domain.LoanReview

data class ReviewResposeDto(
    val userKey: String,
    val interest: Double,
    val limitAmount: Long
) {
    fun toLoanReviewEntity(): LoanReview =
        LoanReview(
            userKey = userKey,
            loanInterest = interest,
            loanLimitedAmount = limitAmount
        )
}
