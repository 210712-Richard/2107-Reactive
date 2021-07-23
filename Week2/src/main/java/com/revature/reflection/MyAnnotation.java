package com.revature.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)// has runtime visibility
@Target(ElementType.TYPE) // Can be applied to Types (classes), Methods, Fields, Constructors, and Parameters
public @interface MyAnnotation {

}
