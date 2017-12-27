package cn.goldencis.vdp.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Author:
 * Date: 2012-1-29
 * Since: MyJavaExpert v1.0
 * Description: field annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Component
public @interface MenuLimitAnnotation {
    String url() default "";
}