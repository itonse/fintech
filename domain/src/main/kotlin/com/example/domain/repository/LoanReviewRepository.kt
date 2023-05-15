package com.example.domain.repository

import com.example.domain.domain.LoanReview
import org.springframework.data.jpa.repository.JpaRepository

interface LoanReviewRepository : JpaRepository<LoanReview, Long> {   // LoanReview 테이블 접근, PK 타입은 Long
    fun findByUserKey(userKey: String): LoanReview    // 유저 키 한 개 가져옴
}