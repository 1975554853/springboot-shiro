package com.hmb.springbootshiro.system.exception;

import com.hmb.springbootshiro.pojo.Page;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.CryptoException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    /**
     *  捕捉shiro的异常
     */
    @ExceptionHandler(ShiroException.class)
    public Page handle401(ShiroException e) {

        return new Page(401, "你没有权限访问该资源!!!", 0,null);
    }

//    @ExceptionHandler(UnknownAccountException.class)
//    public Page handle402(UnknownAccountException e) {
//
//        return new Page(402, "用户名或密码错误", 0,null);
//    }


    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public  Page nullHandler(NullPointerException e){
        return new Page(401, "没有该用户!!!", 0,null);
    }
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

}
