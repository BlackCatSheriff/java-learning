package com.ouc.spring.foundation.annotation.mutibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * BeanImplTwo
 *
 * @author skyUnv
 * created on 2018/5/1 21:19
 */

@Component
@Order(value = 1)
public class BeanImplTwo implements BeanInterface{
}
