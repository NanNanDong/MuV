package com.newx.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by erfli on 2/15/17.
 */
@Retention(RetentionPolicy.CLASS)
public @interface Provider {
    String processName();
}
