package cn.itmtx.ddd.ezlink.domain.domainservice.config;

import cn.itmtx.ddd.ezlink.domain.domainservice.util.WebFluxServerResponseWriter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

@Configuration
public class WebFluxConfig {

    @Bean
    public WebFluxServerResponseWriter webFluxServerResponseWriter(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                                   ServerCodecConfigurer serverCodecConfigurer) {
        WebFluxServerResponseWriter webFluxServerResponseWriter = new WebFluxServerResponseWriter();
        webFluxServerResponseWriter.setMessageReaders(serverCodecConfigurer.getReaders());
        webFluxServerResponseWriter.setMessageWriters(serverCodecConfigurer.getWriters());
        webFluxServerResponseWriter.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        return webFluxServerResponseWriter;
    }

}
