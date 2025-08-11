package com.example.gospace.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // createdAt, updateAt 세팅
public class JpaAuditingConfig {
}
