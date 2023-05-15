package com.example.api.loan.request

import com.example.api.loan.GenerateKey
import com.example.api.loan.encrypt.EncryptComponent
import com.example.domain.domain.UserInfo
import com.example.domain.repository.UserInfoRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebMvcTest(LoanRequestController::class)   // 테스트를 원하는 컨트롤러 클래스 (타켓을 지정해서 빈을 띄움)
// 해당 테스트 class 가 돌아갈 때, REST 컨트롤러, 컨트롤러 어노테이션이 붙은 것 들만 빈을 띄어줌.
// 서비스, 컴포넌트들이 붙은 것 들은 제외됨. -> 가벼운 단위테스트
internal class LoanRequestControllerTest {

    private lateinit var mockMvc: MockMvc  // 단위 테스트에서 MockMvc 사용

    private lateinit var loanRequestController: LoanRequestController

    // @Autowired 사용을 안하니, 의존하는 객체들 개별 생성 필요
    private lateinit var generateKey: GenerateKey

    private lateinit var encryptComponent: EncryptComponent

    private val userInfoRepository: UserInfoRepository = mockk()  // 목 처리된 레파지토리 생성

    private lateinit var mapper: ObjectMapper

    @MockBean   // @WebMvcTest 로 인해 가져오지 못한 목 처리된 빈을 띄어줌.
    private lateinit var loanRequestServiceImpl: LoanRequestServiceImpl

    companion object {
        private const val baseUrl = "/fintech/api/v1"  // 공통 url
    }

    @BeforeEach  // 각 테스트가 실행하기 전에 호출되는 것
    fun init() {
        generateKey = GenerateKey()

        encryptComponent = EncryptComponent()

        loanRequestServiceImpl = LoanRequestServiceImpl(
            generateKey, userInfoRepository, encryptComponent
        )

        loanRequestController = LoanRequestController(loanRequestServiceImpl)

        // mockMvc 초기화: 테스트에 사용 될 해당 컨트롤러를 생성해서 띄워줌
        mockMvc = MockMvcBuilders.standaloneSetup(loanRequestController).build()

        mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())  // 기본 생성자가 없어도 ObjectMapper 가 직렬화, 역질렬화를 해준다.
    }

    @Test
    @DisplayName("유저 요청이 들어오면 정상 응답을 주어야 한다.")   // 코드에 대한 설명
    fun testNormalCase() {  // 정상적인 케이스 테스트
        // given (주어진 것)
        val loanRequestInfoDto: LoanRequestDto.LoanRequestInputDto =
            LoanRequestDto.LoanRequestInputDto(
                userName = "TEST",
                userIncomeAmount = 10000,
                userRegistrationNumber = "000101-1234567"
            )

        every { userInfoRepository.save(any()) } returns UserInfo("", "", "", 1)
            // 모든 save 에 대해서 레파지토리는 위의 UserInfo() 를 응답으로 주도록 목 처리가 됨.

        // when (실행)
        // then  (확인)
        mockMvc.post("$baseUrl/request") {   // url 로 요청을 보냄
            contentType = MediaType.APPLICATION_JSON  // JSON 형식의 데이터를 전송
            accept = MediaType.APPLICATION_JSON  // JSON 형식
            content = mapper.writeValueAsString(loanRequestInfoDto)   // 바디 (DTO -> JSON 변환)
        }.andExpect {
            status { isOk() }   // 정상응답 (200) 기대
        }
    }

}