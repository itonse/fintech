package com.example.api.loan.request

class LoanRequestDto {
    data class LoanRequestInputDto(
        var userName: String,  // 유저 이름
        val userIncomeAmount: Long,   // 유저 소득금액
        var userRegistrationNumber: String   // 유저 주민번호
    ) {
        fun toUserInfoDto(userKey: String) =
            UserInfoDto(
                userKey, userName, userRegistrationNumber, userIncomeAmount
            )
    }

    data class LoanRequestResponseDto(
        val userKey: String
    )
}