package com.onem2.web.common;

import java.lang.annotation.*;

/**
 *
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
