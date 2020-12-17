## 拾遗

### @RequestBody 注解的使用事项

- @RequestBody 主要用来接收前端传递给后端的 json 字符串中的数据的(请求体中的数据的)，而 Get 方式无请求体，所有使用该注解只能使用 POST 请求（有请求体的请求方式，PUT 等）；

### 统一异常处理

[GlobalExceptionHandler.java](/guli_common/service_base/src/main/java/com/tassel/base/exception/GlobalExceptionHandler.java)

```java
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

```

#### 自定义异常

注意：自定义异常需要自己手动抛出(throw)
[GuliException.java](/guli_common/service_base/src/main/java/com/tassel/base/exception/GuliException.java)

```java

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
    private Integer code;
    private String msg;
}
```

### Vue 
#### 指令：
- v-bind 单向绑定可以简写为 `:`;
- v-on 事件绑定可以简写为 `@`;

- v-model 数据双向绑定;

### Nginx
反向代理服务器

#### 作用
- 请求转发
- 负载均衡
- 动静分离

#### 使用
http://nginx.org/en/download.html
这里使用的是 Windows 版本，启动使用 CMD (任务管理器会有两个进程)
需要注意的是，关闭 CMD 并不会停止 Nginx，需要使用以下命令操作
```shell
# 停止
nginx.exe -s stop
# 启动
nginx.exe
```

#### 配置Nginx 实现请求转发
找到配置文件 `/nginx-1.18.0/conf/nginx.conf`
```shell
http {
    ...
    server {
        listen       9001; # 端口号
        server_name  localhost; # 主机ip
        
        location ~ /edu/service/ { # 匹配路径 ~ 使用正则
            proxy_pass http://localhost:8001; # 转发到的路径
        }
        location ~ /edu/oss/ {
            proxy_pass http://localhost:8002;
        }
        ...
    }
    ...
}
```