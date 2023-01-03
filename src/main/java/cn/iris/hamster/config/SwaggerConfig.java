package cn.iris.hamster.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 *
 * @author Iris
 * @ClassName SwaggerConfig
 * @date 2023/1/3 14:37
 */

@Component
@EnableKnife4j
@EnableSwagger2
@ConditionalOnExpression("${knife4j.enable}")
public class SwaggerConfig {

    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 扫描的路径使用@Api的controller
                .apis(RequestHandlerSelectors.basePackage("cn.iris.hamster.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hamster-接口文档")
                .description("物流运输管理系统接口文档")
                .contact(new Contact("张陶涛", "https://foxiris.cn", "foxiris@qq.com"))
                .version("1.0")
                .build();
    }
}
