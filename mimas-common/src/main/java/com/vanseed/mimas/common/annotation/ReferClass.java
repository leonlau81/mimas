/**
 * 
 */
package com.vanseed.mimas.common.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author leon
 *
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface ReferClass {
	Class<?> clz();
}
