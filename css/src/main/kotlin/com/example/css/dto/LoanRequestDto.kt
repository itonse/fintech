package com.example.css.dto

class LoanRequestDto {
    data class RequestInputDto(
        val userKey: String,   // 유저키
        val userName: String,  // 유저 이름
        val userIncomeAmount: Long,   // 유저 소득금액
        var userRegistrationNumber: String   // 유저 주민번호
    )
}