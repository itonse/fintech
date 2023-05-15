plugins {}

version = "0.0.1"

dependencies {
    // spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.6")

    implementation("io.springfox:springfox-boot-starter:3.0.0")

    implementation(project(":domain"))   // domain 모듈 사용

    // test 관련
    testImplementation("io.mockk:mockk:1.12.0")    // 코틀린 테스트 프레임워크 (단위테스트)
    runtimeOnly("com.h2database:h2")   // 단위 테스트에서 사용할 DB (로컬 테스트가 돌아가도 실제 DB 에는 영향X)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")  // mapper 관련

    // AOP
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // Logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")  // 코틀린 로깅 사용
}