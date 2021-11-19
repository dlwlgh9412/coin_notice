package com.jjangchen.externalmodule.config;

import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Type;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
            .create();

    private static class SpringfoxJsonToGsonAdapter implements JsonSerializer<Json> {
        @Override
        public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
            return JsonParser.parseString(src.value());
        }
    }

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("jjangchen.loca.lt")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jjangchen.api_module.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        String desc = "Welcome";
        return new ApiInfoBuilder()
                .title("코린이 API")
                .version("1.0")
                .build();
    }
}
