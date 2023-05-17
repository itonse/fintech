package com.example.api.loan.request

import com.example.domain.domain.UserInfo

interface LoanRequestService {     // 인터페이스로 생성하여 확장성 높이기
    fun loanRequestMain(    // 메인
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto

    fun saveUserInfo(   // 유저 정보들을 저장
        userInfoDto: UserInfoDto
    ): UserInfo   // output 값

    fun loanRequestReview(userInfoDto: UserInfoDto)  // 유저의 대출 심사정보를 받음 (Kafka 이용)
}