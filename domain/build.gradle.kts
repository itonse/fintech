plugins {
    kotlin("plugin.jpa")  // 생성자 타입에 초기값을 넣지 않아도 에러 발생x
}

version = "0.0.1"

allprojects {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.6")
        implementation("mysql:mysql-connector-java")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
    }
}