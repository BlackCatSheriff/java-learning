package com.ouc.spring.foundation.annotation.mutibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * BeanImplOne
 *
 * @author skyUnv
 * created on 2018/5/1 21:19
 */

@Component
@Order(value = 2)
public class BeanImplOne implements BeanInterface{
}
