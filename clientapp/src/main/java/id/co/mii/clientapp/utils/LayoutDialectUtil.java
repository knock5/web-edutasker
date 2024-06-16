package id.co.mii.clientapp.utils;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LayoutDialectUtil {

  @Bean
  public LayoutDialect layoutDialect() {
    return new LayoutDialect();
  }
}
