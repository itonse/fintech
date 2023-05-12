package com.example.domain.domain

import javax.persistence.*

@Entity
@Table(name = "USR_INFO")   // 테이블 명
class UserInfo (
    @Column(name ="usr_key")  // 컬럼 명(축약)
    val userKey: String,

    @Column(name = "usr_reg_num")
    val userRegistrationNumber: String,

    @Column(name = "usr_nm")
    val userName: String,

    @Column(name = "usr_icm_amt")
    val userIncomeAmount: Long
) {
    // PK 값은 mysql 에서 알아서 지정하도록 하여, 위에 생성자에 넣지 않음.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
