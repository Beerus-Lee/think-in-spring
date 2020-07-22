package org.own.think.in.spring.data.binding;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import spring.ioc.domain.Company;
import spring.ioc.domain.User;

import java.util.HashMap;
import java.util.Map;

public class DataBinderDemo {
    public static void main(String[] args) {
        User user  = new User();
        DataBinder dataBinder = new DataBinder(user,"");


        Map<String,Object> source = new HashMap<>();
        source.put("name", "超哥");
        source.put("id", 1L);
        source.put("all", "");
        //source.put("company", new Company());
        source.put("company.name", "geek");

        PropertyValues propertyValues = new MutablePropertyValues(source);



      //  dataBinder.setIgnoreUnknownFields(true);
        //dataBinder.setIgnoreInvalidFields(true);
        dataBinder.setAutoGrowNestedPaths(false);

        // 3. 调整 ignoreInvalidFields false(默认） -> true（默认情况调整不变化，需要调增 AutoGrowNestedPaths 为 false）
        dataBinder.setIgnoreInvalidFields(true);
        dataBinder.setRequiredFields("id");

        dataBinder.bind(propertyValues);

        System.out.println(user);

        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);


    }
}
