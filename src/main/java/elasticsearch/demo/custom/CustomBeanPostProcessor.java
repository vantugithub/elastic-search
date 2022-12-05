package elasticsearch.demo.custom;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ConfigurableListableBeanFactory configurableBeanFactory;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("anh nho em");
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(SpringBootApplication.class)) {
            ReflectionUtils.doWithFields(bean.getClass(), new CustomFieldCallBack(configurableBeanFactory, bean));
            System.out.println("anh nho em 2");
        }
        return bean;
    }
}
