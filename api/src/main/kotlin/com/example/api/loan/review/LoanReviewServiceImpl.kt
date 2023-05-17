package com.example.api.loan.review

import com.example.api.exception.CustomErrorCode
import com.example.api.exception.CustomException
import com.example.domain.domain.LoanReview
import com.example.domain.repository.LoanReviewRepository
import org.springframework.stereotype.Service
import org.springframework.cache.annotation.Cacheable

@Service
class LoanReviewServiceImpl(
    private val loanReviewRepository: LoanReviewRepository   // Loan Review 테이블에 접근하기 위해 레파지토리를 받아옴
) : LoanReviewService {
    override fun loanReviewMain(userKey: String): LoanReviewDto.LoanReviewResponseDto {
        return LoanReviewDto.LoanReviewResponseDto(  // Front-End 서버로 응답을 보냄
            userKey = userKey,
            loanResult = getLoanResult(userKey)?.toResponseDto()   // 대출 데이터 가져와서 유저의 응답값으로 변경
                ?: throw CustomException(CustomErrorCode.RESULT_NOT_FOUND)   // null 값이면, 커스텀익셉션 던지기
        )
    }

    // DB 에서 userKey 에 해당하는 대출 데이터 가져오기
    @Cacheable(value = ["REVIEW"], key = "#userKey", cacheManager = "redisCacheManager")   // 캐시 사용
    override fun getLoanResult(userKey: String) =   // 바로 LoanReview 도메인을 사용하는 것은 위험하기 때문에 사용X
        loanReviewRepository.findByUserKey(userKey)   // 레파지토리를 통해서 조회 (Nullable 값)

    private fun LoanReview.toResponseDto() =   // 유저의 응답값으로 변경
        LoanReviewDto.LoanResult(
            userLimitAmount = this.loanLimitedAmount,
            userLoanInterest = this.loanInterest
        )
}