package com.joel.yupicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joel.yupicturebackend.model.dto.space.SpaceAddRequest;
import com.joel.yupicturebackend.model.dto.space.SpaceQueryRequest;
import com.joel.yupicturebackend.model.entity.Space;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joel.yupicturebackend.model.entity.User;
import com.joel.yupicturebackend.model.vo.SpaceVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author Yu
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-06-11 17:58:39
*/
public interface SpaceService extends IService<Space> {

    /**
     * 创建空间
     * @param spaceAddRequest
     * @param loginUser
     * @return
     */
    long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);

    /**
     * 校验空间
     * @param space 空间信息
     * @param add 是否为创建空间时校验
     */
    void validSpace(Space space, boolean add);

    /**
     * 获取单条空间信息封装类
     * @param space
     * @param request
     * @return
     */
    SpaceVO getSpaceVO(Space space, HttpServletRequest request);

    /**
     * 获取分页空间信息封装类
     * @param spacePage
     * @param request
     * @return
     */
    Page<SpaceVO> getSpaceVOPage(Page<Space> spacePage, HttpServletRequest request);

    /**
     * 将空间请求查询条件转换为 QueryWrapper
     * @param spaceQueryRequest
     * @return
     */
    QueryWrapper<Space> getQueryWrapper(SpaceQueryRequest spaceQueryRequest);

    /**
     * 在创建或更新空间时，需要根据空间级别自动填充限额数据
     * @param space
     */
    void fillSpaceBySpaceLevel(Space space);

    /**
     * 校验空间权限
     * 仅本人或管理员具有该权限
     * @param loginUser
     * @param space
     */
    void checkSpaceAuth(User loginUser, Space space);
}
