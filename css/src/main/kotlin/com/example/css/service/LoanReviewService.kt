package com.example.css.service

import com.example.css.dto.LoanRequestDto
import com.example.css.dto.LoanResultDto
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class LoanReviewService {
    fun loanReview(loanRequestDto: LoanRequestDto.RequestInputDto): LoanResultDto.ResponseDto {
        if (loanRequestDto.userIncomeAmount < 0) throw RuntimeException("Invalid userIncomeAmount Param")   // 소득액이 음수이면 런타임 익셉션 발생
        if (loanRequestDto.userIncomeAmount < 10000000)return LoanResultDto.ResponseDto(loanRequestDto.userKey, 0.0, 10000000)
        if (loanRequestDto.userIncomeAmount < 20000000)return LoanResultDto.ResponseDto(loanRequestDto.userKey, 10.0, 20000000)
        if (loanRequestDto.userIncomeAmount < 30000000)return LoanResultDto.ResponseDto(loanRequestDto.userKey, 9.0, 30000000)
        if (loanRequestDto.userIncomeAmount < 40000000)return LoanResultDto.ResponseDto(loanRequestDto.userKey, 8.0, 40000000)
        if (loanRequestDto.userIncomeAmount < 50000000)return LoanResultDto.ResponseDto(loanRequestDto.userKey, 7.0, 50000000)
        if (loanRequestDto.userIncomeAmount >= 50000000)return LoanResultDto.ResponseDto(loanRequestDto.userKey, 6.0, 60000000)
        throw RuntimeException("Invalid userIncomeAmount Param")
    }
}