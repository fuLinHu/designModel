package springAll.servlet.controller;

import commit.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/21 9:39
 * @Version V1.0
 */
@RestController
@RequestMapping("/testroot")
@Slf4j
public class TestController {

    private Queue<DeferredResult<User>> queue = new LinkedBlockingDeque();

    @RequestMapping("/test")
    public String test(){
        System.out.println("请求-----");
        return "flh";
    }

    @RequestMapping("/testCall")
    public Callable<User> testCall(){
        log.info("请求-----testCall");
        Callable<User> callable = new Callable<User>() {
            @Override
            public User call() throws Exception {
                log.info("获取的结果");
                return new User();
            }
        };

        return callable;
    }
    @RequestMapping("/testDefer")
    public DeferredResult<User> testDefer(){
        DeferredResult<User> deferredResult = new DeferredResult<User>(1000l,"请求超时");
        deferredResult.setResult(new User());
        return deferredResult;
    }

    @RequestMapping("/getDefer")
    public DeferredResult<User> getDefer(HttpServletRequest httpRequest){
        String requestURI = httpRequest.getRequestURI();
        log.info(requestURI+"");
        DeferredResult<User> deferredResult = new DeferredResult<User>(5000l,"请求超时");
        deferredResult.onCompletion(()->{
            //只是请求完成 不管成功与否
            log.info("请求完成，只是请求完成 不管成功与否");
        });
        deferredResult.onTimeout(()->{
            log.info("请求超时0----");
        });
        deferredResult.onError((u)->{
            log.info("请求失败"+u);
        });
        queue.add(deferredResult);
        return deferredResult;
    }

    @RequestMapping("/putDefer")
    public String putDefer(HttpServletRequest httpRequest){
        String requestURI = httpRequest.getRequestURI();
        log.info(requestURI+"");
        AtomicInteger i = new AtomicInteger();
        queue.forEach((def)->{
            def.setResult(new User("fulinhu"+(i.getAndIncrement())));
        });
     /*   while(true){
            AtomicInteger i= new AtomicInteger();
        }*/
        return "成功";
    }




}
