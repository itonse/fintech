import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.10" apply false
    kotlin("plugin.jpa") version "1.6.0" apply false

    id("org.springframework.boot") version "2.6.3" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
}

repositories {
    mavenCentral()
}

allprojects {
    group = "com.example"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")

        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

project(":api") {   // 프로젝트 별로 명시
    dependencies {
        implementation(project(":kafka"))
        implementation(project(":domain"))
    }
}

project(":consumer") {
    dependencies {
        implementation(project(":domain"))
        implementation(project(":kafka"))
    }
}

project(":domain") {   // domain 과 kafka 는 main(실행) 함수가 없다
    val jar: Jar by tasks  // 실행 가능한 것을 만들지 않아도 된다고 명시
    val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

    bootJar.enabled = false   // 실행 가능한 파일을 만들 때는
    jar.enabled = true
}

project(":kafka") {
    val jar: Jar by tasks
    val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

    bootJar.enabled = false   // 실행 가능한 파일을 만들 때는
    jar.enabled = true
}