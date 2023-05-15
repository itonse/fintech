package com.example.api.loan.review

import com.example.domain.repository.LoanReviewRepository
import org.springframework.stereotype.Service

@Service
class LoanReviewServiceImpl(
    private val loanReviewRepository: LoanReviewRepository   // Loan Review 테이블에 접근하기 위해 레파지토리를 받아옴
) : LoanReviewService {

    override fun loanReviewMain(userKey: String): LoanReviewDto.LoanReviewResponseDto {

        val loanResult = getLoanResult(userKey)    // 아래 메소드에서 대출 데이터 가져오기

        return LoanReviewDto.LoanReviewResponseDto(  // Front-End 서버로 응답을 보냄
            userKey = userKey,
            loanResult = LoanReviewDto.LoanResult(    // 결과
                userLimitAmount = loanResult.userLimitAmount,   // 한도금액
                userLoanInterest = loanResult.userLoanInterest  // 이자
            )
        )
    }

    // DB 에서 userKey 에 해당하는 대출 데이터 가져오기
    override fun getLoanResult(userKey: String): LoanReviewDto.LoanReview {   // 바로 LoanReview 도메인을 사용하는 것은 위험하기 때문에 사용X
        val loanReview = loanReviewRepository.findByUserKey(userKey)   // 레파지토리를 통해서 조회

        return LoanReviewDto.LoanReview(   // DTO 타입으로 변환해서 리턴
            loanReview.userKey,
            loanReview.loanLimitedAmount,
            loanReview.loanInterest
        )
    }

}