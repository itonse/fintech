package com.example.domain.domain

import javax.persistence.*


@Entity
@Table(name = "LOAN_REVIEW")
class LoanReview (
    @Column(name = "usr_key")
    val userKey: String,

    @Column(name = "loan_lmt_amt")  // 대출 한도 금액
    val loanLimitedAmount: Long,

    @Column(name = "loan_intrt")   // 금리
    val loanInterest: Double
) {
    @Id   // id는 mysql에서 생성을 하도록 위임
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // 전략도 mysql이 설정을 하도록
    val id: Long? = null
}