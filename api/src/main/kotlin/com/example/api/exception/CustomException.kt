package com.example.api.exception

class CustomException (val customErrorCode: CustomErrorCode) : RuntimeException()
  // 커스텀 익셉션 발생 -> 익셉션 핸들러로 익셉션을 가져옴 -> 커스텀 에러코드를 보고 적절한 응답