/**
 * Copyright (C), 科大讯飞股份有限公司
 * FileName: HomeController
 * Author:   xwliu
 * Date:     2018/4/2 15:04
 * Description: 登录，登出，验证码等Controller
 */
package com.iflytek.iaas.controller;

import com.alibaba.fastjson.JSON;
import com.iflytek.iaas.consts.ReturnCode;
import com.iflytek.iaas.domain.User;
import com.iflytek.iaas.dto.UserDTO;
import com.iflytek.iaas.exception.ControllerException;
import com.iflytek.iaas.service.UserService;
import com.iflytek.iaas.utils.MD5Utils;
import com.iflytek.iaas.utils.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * 〈登录，登出，验证码等Controller〉
 *
 * @author xwliu
 * @create 2018/4/2
 */
@Api(value = "Home-API", description = "登录，登出，验证码等")
@RequestMapping(path="/api/v1")
@RestController
public class HomeController{

    private static final Logger logger = LoggerFactory
            .getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param request request
     * @param account 账号/邮件/手机号
     * @param pwd 密码
     * @param code 验证码
     * @return
     */
    @ApiOperation(value = "login",notes = "用户登录")
    @ApiImplicitParam(name = "requestBody", value = "账号/邮件/手机号", required = true, dataType = "UserDTO")
    @PostMapping("/login")
    public User login(HttpServletRequest request, @RequestBody UserDTO requestBody)throws ControllerException{

        HttpSession session = request.getSession();

        String verCode = (String) session.getAttribute("verCode");

//        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(pwd)
//                || StringUtils.isEmpty(code)) {
////            throw new ControllerException(ReturnCode.PARAM_UNVALID);
//        }
//
//        if (StringUtils.isEmpty(verCode) || !verCode.equalsIgnoreCase(code)) {
////            throw new ControllerException(ReturnCode.VERIFY_ERROR);
//        }

        session.removeAttribute("verCode");

        User user = userService.getUserByAuth(requestBody.getAccount());
        if(user == null){
            throw new ControllerException(ReturnCode.ACCOUNT_PWD_ERROR);
        }

        try {
            requestBody.setPassword(MD5Utils.encrypt(requestBody.getPassword(), user.getSalt()));

        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            throw new ControllerException(ReturnCode.EXCEPTION);
        }

        Subject subject = SecurityUtils.getSubject();
        subject.getPrincipal();
        UsernamePasswordToken token = new UsernamePasswordToken(requestBody.getAccount(), requestBody.getPassword());

        try {
            subject.login(token);
            session.setAttribute("CURRENT_USER", user);
            return user;

        } catch (Exception e) {
            throw new ControllerException(ReturnCode.ACCOUNT_PWD_ERROR);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @ApiOperation(value = "logout",notes = "用户登出")
    @PostMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "success";
    }

    /**
     * 获取验证码
     * @param request request
     * @return
     */
    @ApiOperation(value = "verify",notes = "验证码")
    @GetMapping("/verify")
    public String verify(HttpServletRequest request) throws ControllerException{
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession();
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        // 生成图片
        int w = 100, h = 50;
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            VerifyCodeUtils.outputImage(w, h, outStream, verifyCode);
            byte[] buffer = outStream.toByteArray();
            outStream.close();
            String base64Str = Base64.encodeToString(buffer);
            return "data:image/jpeg;base64," + base64Str;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ControllerException(ReturnCode.VERIFY_FAIL);
        }
    }

}
