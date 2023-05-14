package com.example.api.loan.request

import com.example.domain.domain.UserInfo

data class UserInfoDto(   // 데이터클래스
    val userKey: String,
    val userName: String,
    val userRegistrationNumber: String,
    val userIncomeAmount: Long
) {
    fun toEntity(): UserInfo =   // 엔티티로 변환해주는 확장함수
        UserInfo(
            userKey, userRegistrationNumber, userName, userIncomeAmount
        )
}
