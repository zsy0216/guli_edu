package com.tassel.base.exception;

import com.tassel.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局统一异常处理：当出现指定的异常类型时，由此程序进行处理
 *
 * @author: zsy
 * @date: 2020/12/6 16:59
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @ExceptionHandler: 指定出现什么类型的异常时，执行此方法
     * @ResponseBody: 为了返回 Json 处理数据
     *
     * @param e 异常对象
     * @return 处理数据
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理.");
    }
}
