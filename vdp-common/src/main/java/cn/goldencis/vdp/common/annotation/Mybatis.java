package cn.goldencis.vdp.common.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * dao层添加注解
 * @author mll
 * 2017年6月5日下午9:36:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repository
public @interface Mybatis {

}
