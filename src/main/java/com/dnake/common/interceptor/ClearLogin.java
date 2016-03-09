package com.dnake.common.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 描述：用于控制器取消登入拦截注解
 * @author 蔡小龙
 * @date 创建时间：2015年7月9日 上午10:33:46 
 * @version 1.0
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClearLogin {

}
