package com.joel.yupicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joel.yupicturebackend.model.dto.user.UserLoginRequest;
import com.joel.yupicturebackend.model.dto.user.UserQueryRequest;
import com.joel.yupicturebackend.model.dto.user.UserRegisterRequest;
import com.joel.yupicturebackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joel.yupicturebackend.model.vo.LoginUserVO;
import com.joel.yupicturebackend.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author Yu
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-04-05 16:33:43
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param request 用户注册请求体
     * @return 注册成功的用户id
     */
    long userRegister(UserRegisterRequest request);

    /**
     * 加密密码
     * @param userPassword 要加密的密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String userPassword);

    /**
     * 用户登录
     * @param userLoginRequest 用户登录请求体
     * @param request http请求
     * @return 登录成功的用户信息(脱敏处理后)
     */
    LoginUserVO userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);

    /**
     * 获取当前登录用户
     * @param request http请求
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 获取脱敏后的登录用户信息
     * @param user
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获取脱敏后的用户信息
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏后的用户信息列表
     * @param userList
     * @return
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 用户注销
     * @param request http请求
     * @return 注销成功与否
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 将查询条件封装成Mybatis-Plus的QueryWrapper
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    /**
     * 判断某个用户是否为管理员
     * @param user
     * @return
     */
    boolean isAdmin(User user);

}
