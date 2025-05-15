//package com.ua.teamchallenge.handmadestore.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    //для избегания ошибки CORS (фронт)
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Разрешить CORS для всех путей
//                .allowedOrigins("http://localhost:4000") // Укажите ваш фронтенд-источник
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешенные методы
//                .allowedHeaders("Content-Type", "Authorization") // Разрешенные заголовки
//                .allowCredentials(true); // Если нужны куки или авторизация
//    }
//}
