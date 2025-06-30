package com.welfare.lottery.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuchengcheng
 * @since 2025/6/18
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi swaggerAdminBean() {
        return GroupedOpenApi.builder()
                .group("通用功能")
                .packagesToScan("com.welfare.lottery.controller.common")
                .pathsToMatch("/**")
                .build();
    }
}
