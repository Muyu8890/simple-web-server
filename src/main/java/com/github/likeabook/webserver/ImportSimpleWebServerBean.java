package com.github.likeabook.webserver;

import com.github.likeabook.webserver.result.PackageResponseBodyAdvice;
import com.github.likeabook.webserver.util.BeanFactoryUtils;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PackageResponseBodyAdvice.class, BeanFactoryUtils.class})
public @interface ImportSimpleWebServerBean {
}
