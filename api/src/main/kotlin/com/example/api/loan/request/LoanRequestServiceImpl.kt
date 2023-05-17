package com.example.api.loan.request

import com.example.api.loan.GenerateKey
import com.example.api.loan.encrypt.EncryptComponent
import com.example.domain.repository.UserInfoRepository
import com.example.kafka.enum.KafkaTopic
import com.example.kafka.producer.LoanRequestSender
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository,   //  UserInfo 엔티티 접근
    private val encryptComponent: EncryptComponent,   // 암호화 기능
    private val loanRequestSender: LoanRequestSender

): LoanRequestService {
    override fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        val userKey = generateKey.generateUserKey()   // 유저키 발급

        loanRequestInputDto.userRegistrationNumber =
            encryptComponent.encryptString(loanRequestInputDto.userRegistrationNumber)  // 주민등록번호는 암호화 처리

        val userInfoDto = loanRequestInputDto.toUserInfoDto(userKey)   // USR_INFO 테이블 포맷에 맞게 DTO 변경

        saveUserInfo(userInfoDto)  // 유저 데이터 저장

        loanRequestReview(userInfoDto)   // 심사를 요청 (Kafka 를 통해서 유저의 심사결과를 받음)

        return LoanRequestDto.LoanRequestResponseDto(userKey)   // 리턴
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) =
        userInfoRepository.save(userInfoDto.toEntity())

    override fun loanRequestReview(userInfoDto: UserInfoDto) {
        loanRequestSender.sendMessage(   // sendMessage 를 통해 Kafka 에 topic 을 보냄
            KafkaTopic.LOAN_REQUEST,
            userInfoDto.toLoanRequestKafkaDto()
        )
    }
}