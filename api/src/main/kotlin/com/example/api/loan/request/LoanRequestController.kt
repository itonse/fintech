package com.example.api.loan.request

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URLDecoder

@RestController
@RequestMapping("/fintech/api/v1")
class LoanRequestController (
    private val loanRequestServiceImpl: LoanRequestServiceImpl    // 서비스 호출
) {

    @PostMapping("/request")
    fun loanRequest(   // 대출 요청 API
        @RequestBody loanRequestInputDto: LoanRequestDto.LoanRequestInputDto   // 리퀘스트바디로 input 값 받음
    ): ResponseEntity<LoanRequestDto.LoanRequestResponseDto> {  // output: ResponseEntity 로 리턴
        return ResponseEntity.ok(
            loanRequestServiceImpl.loanRequestMain(loanRequestInputDto)
        )
    }
}