package com.example.consumer.service

import com.example.consumer.dto.ReviewResposeDto
import com.example.domain.domain.LoanReview
import com.example.domain.repository.LoanReviewRepository
import com.example.kafka.dto.LoanRequestDto
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class LoanRequestService(
    private val loanReviewRepository: LoanReviewRepository
) {
    companion object {
        const val nginxUrl = "http://nginx:8085/css/api/v1/request"
    }

    fun loanRequest(loanRequestDto: LoanRequestDto) {
        val reviewResult = loanRequestToCb(loanRequestDto)   // css 모듈로 API 요청을 하고 받아온 심사 결과를

        saveLoanReviewData(reviewResult.toLoanReviewEntity())   // 응답을 Entity 형식에 맞춰 변환하여 DB에 저장
    }

    private fun loanRequestToCb(loanRequestDto: LoanRequestDto): ReviewResposeDto {   // consumer 에서 사용하는것 필요
        val restTemplate = RestTemplateBuilder()   // restTemplate 생성: HTTP 클라이언트 라이브러리 (웹 통신을 단순화)
            .setConnectTimeout(Duration.ofMillis(1000))
            .setReadTimeout(Duration.ofMillis(1000))
            .build()
        // POST 방식으로 css 모듈로 API 요청
        return restTemplate.postForEntity(nginxUrl, loanRequestDto, ReviewResposeDto::class.java).body!!  // url, body, return
    }

    private fun saveLoanReviewData(loanReview: LoanReview) = loanReviewRepository.save(loanReview)   // 심사결과 저장
}