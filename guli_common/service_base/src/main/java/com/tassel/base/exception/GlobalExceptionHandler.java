package com.tassel.base.exception;

import com.tassel.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理：当出现指定的异常类型时，由此程序进行处理
 *
 * @author: zsy
 * @date: 2020/12/6 16:59
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * <p>
     * ExceptionHandler: 指定出现什么类型的异常时，执行此方法
     * ResponseBody: 为了返回 Json 处理数据
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

    /**
     * 特定异常处理
     *
     * @param e 异常对象
     * @return 处理数据
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了 ArithmeticException 异常处理.");
    }

    /**
     * 自定义异常处理
     *
     * @param e 异常对象
     * @return 处理数据
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
