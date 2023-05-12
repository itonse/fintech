package com.example.api.test

import com.example.domain.domain.UserInfo
import com.example.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class TestService (   // user id를 받아서 user 정보를 가져오는 기능
    private val userInfoRepository: UserInfoRepository
) {
    fun testGetService(userKey: String) = userInfoRepository.findByUserKey(userKey).toDto()  // userKey를 받아서 UserInfo를 찾고, DTO 형식으로 변환해서 리턴

    // domain <-> DTO 변환 확장 함수 정의
    fun UserInfo.toDto() = TestDto.UserInfoDto(
        userKey, userRegistrationNumber, userName, userIncomeAmount)
}