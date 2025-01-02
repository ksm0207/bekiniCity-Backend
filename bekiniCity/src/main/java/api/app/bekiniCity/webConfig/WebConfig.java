package api.app.bekiniCity.webConfig;

import java.io.IOException;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String ERROR_PATH = "/error";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                                : new ClassPathResource("/static/index.html");
                    }
                });

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    // CORS
     @Override
     public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
                 .allowedOrigins("*")
                 .allowedMethods("GET", "POST", "PUT", "DELETE")
                 .allowedHeaders("*");
     }
}
