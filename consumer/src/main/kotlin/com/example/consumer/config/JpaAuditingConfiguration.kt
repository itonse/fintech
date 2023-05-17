package com.example.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["com.example.domain"])
class JpaAuditingConfiguration     // 작은 단위로 쪼개서 개발하기 위해, ApiApplication 에서 jpa 관련 어노테이션을 분리

