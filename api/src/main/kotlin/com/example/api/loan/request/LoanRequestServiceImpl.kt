package com.example.api.loan.request

import com.example.api.loan.GenerateKey
import com.example.api.loan.encrypt.EncryptComponent
import com.example.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository,   //  UserInfo 엔티티 접근
    private val encryptComponent: EncryptComponent   // 암호화 기능

): LoanRequestService {
    override fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        val userKey = generateKey.generateUserKey()   // 유저키 발급

        loanRequestInputDto.userRegistrationNumber =
            encryptComponent.encryptString(loanRequestInputDto.userRegistrationNumber)  // 주민등록번호를 암호화

        saveUserInfo(loanRequestInputDto.toUserInfoDto(userKey))  // 가저온 유저 정보를 저장

        loanRequestReview(userKey)   // userKey 값을 넣어 심사를 요청 (Kafka 를 통해서 유저의 심사결과를 받음)

        return LoanRequestDto.LoanRequestResponseDto(userKey)   // 리턴
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) =
        userInfoRepository.save(userInfoDto.toEntity())

    override fun loanRequestReview(userKey: String) {
        TODO("Not yet implemented")
    }


}