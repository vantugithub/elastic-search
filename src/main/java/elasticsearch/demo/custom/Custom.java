package elasticsearch.demo.custom;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Documented
@ComponentScan("elasticsearch.demo.custom")
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Custom {
}
