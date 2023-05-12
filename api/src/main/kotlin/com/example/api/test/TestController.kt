package com.example.api.test

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fintech/api/v1")   // 공통경로 (fintech 의 api 모듈 버전1)
class TestController (
    private val testService: TestService
) {

    @GetMapping("/test/get/{userKey}")
    fun test(
        @PathVariable userKey: String   // url 경로에 있는 값을 userKey 로 받아옴
    ): TestDto.UserInfoDto = testService.testGetService(userKey)
}
