package com.example.consumer.service

import com.example.domain.domain.LoanReview
import com.example.domain.repository.LoanReviewRepository
import org.springframework.stereotype.Service

@Service
class LoanRequestService(
    private val loanReviewRepository: LoanReviewRepository
) {

    fun loanRequest() {
        // TODO : CB Component 로 요청 보내기 -> 응답값을 DB에 저장하기
    }

    fun lonRequestToCb() {
        // TODO
    }

    fun saveLoanReviewData(loanReview: LoanReview) = loanReviewRepository.save(loanReview)
}