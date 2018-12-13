package com.cb.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.cb.admin.AdminConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(AdminConfig.class)
public @interface EnableAdmin {
	
}
