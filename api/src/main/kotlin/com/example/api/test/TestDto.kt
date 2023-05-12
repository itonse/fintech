package com.example.api.test

class TestDto {   // DTO 사용 : domain에 직접 접근하면 DB의 값이 변경될 수 있는데, 이것을 방지.
    data class UserInfoDto(
        val userKey: String,
        val userRegistrationNumber: String,
        val userName: String,
        val userIncomeAmount: Long
    )
}