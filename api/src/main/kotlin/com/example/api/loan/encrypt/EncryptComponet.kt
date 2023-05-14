package com.example.api.loan.encrypt

import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class EncryptComponet {    // 암호화, 복호화 기능
    companion object {
        private const val secretKey = "12345678901234561234567890123456"   // secretKey 값 지정
    }

    private val encoder = Base64.getEncoder()    // 인코딩 하기 위해 생성
    private val decoder = Base64.getDecoder()    // 복호화에 사용

    // 암호화
    fun encryptString(encryptString: String): String {    // 암호화 할 스트링을 받음
        val encryptString = cipherPkcs5(Cipher.ENCRYPT_MODE, secretKey).doFinal(encryptString.toByteArray(Charsets.UTF_8))   // ByteArray 변환하여 암호화 진행

        return String(encoder.encode(encryptString))   // 인코딩하여 리턴
    }

    // 복호화
    fun decryptString(decryptString: String): String{     // 복호화 할 스트링을 받음
        val byteString = decoder.decode(decryptString.toByteArray(Charsets.UTF_8))   // 디코더가 받은 스트링을 디코딩

        return String(cipherPkcs5(Cipher.DECRYPT_MODE, secretKey).doFinal(byteString))   // 디코딩한 값을 넣어서 복호화 진행
    }

    fun cipherPkcs5(opMode: Int, secretKey: String): Cipher {    // 암호화 생성 함수
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val sk = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
        val iv = IvParameterSpec(secretKey.substring(0, 16).toByteArray(Charsets.UTF_8))
        c.init(opMode, sk, iv)
        return c
    }
}