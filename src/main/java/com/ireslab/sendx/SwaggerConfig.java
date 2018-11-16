package com.ireslab.sendx;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAspectJAutoProxy
public class SwaggerConfig {                                    

    @Bean
    public Docket ammbrBetaServiceApi() {
      return new Docket(DocumentationType.SWAGGER_2)
      .groupName("Sendx App")
      .apiInfo(apiInfo())
      .select()
//      .apis(RequestHandlerSelectors.any())
      .apis(RequestHandlerSelectors.basePackage("com.ireslab.sendx"))
//      .paths(PathSelectors.any())
      .paths(Predicates.not(PathSelectors.regex("/error.*")))
      .build()
      .pathMapping("/");
     
    }
    private ApiInfo apiInfo() {
    	  return new ApiInfoBuilder()
    	  .title("This is Sendx API services")
    	  .description(" ")
    	  .contact(new Contact("Sendx", "", ""))
    	  .version("1.0")
    	  .build();
    	}
    
    
    
    @Bean
    public RestTemplate getRestTemplate(){
    	 RestTemplate restTemplate = new RestTemplate();

         MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();


         converter.setSupportedMediaTypes(
                 Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM}));

         restTemplate.setMessageConverters(Arrays.asList(converter, new FormHttpMessageConverter()));
         return restTemplate;
    } 
}