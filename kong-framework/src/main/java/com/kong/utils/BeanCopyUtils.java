package com.kong.utils;

import com.kong.domain.entity.Article;
import com.kong.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){
    }

    public static <V> V copyBean(Object source,Class<V> clazz){
        V result=null;
        try {
            //创建目标对象
            result=clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source,result);
            //返回结果
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }

}
