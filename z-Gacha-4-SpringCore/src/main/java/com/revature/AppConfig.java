package com.revature;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.revature")
@EnableAspectJAutoProxy
public class AppConfig {

}
