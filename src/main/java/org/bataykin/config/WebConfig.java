package org.bataykin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.bataykin.constants.WebConstants.ANY_URL;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private final static String[] ALLOWED_METHODS = {GET.name(), POST.name(), PUT.name(), PATCH.name(), DELETE.name()};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(ANY_URL)
                .allowedMethods(ALLOWED_METHODS);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter(UTF_8));
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
