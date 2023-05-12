package com.example.domain.repository

import com.example.domain.domain.UserInfo
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoRepository : JpaRepository<UserInfo, Long> {   // 엔티티, PK 타입
    fun findByUserKey(userKey: String): UserInfo   // userKey 를 넣으면 UserInfo 가 반환되는 메소드
}