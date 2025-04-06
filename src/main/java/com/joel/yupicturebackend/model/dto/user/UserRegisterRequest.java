package com.joel.yupicturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: UserRegisterRequest
 * Description:
 * 用户注册请求
 * @Author Joel
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 8151414720081311137L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;
}
