package com.joel.yupicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joel.yupicturebackend.model.dto.picture.PictureQueryRequest;
import com.joel.yupicturebackend.model.dto.picture.PictureUploadRequest;
import com.joel.yupicturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joel.yupicturebackend.model.entity.User;
import com.joel.yupicturebackend.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author Yu
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-04-07 23:38:58
*/
public interface PictureService extends IService<Picture> {

    /**
     * 图片数据校验方法，用于更新和修改图片时进行判断
     * @param picture
     */
    void validPicture(Picture picture);

    /**
     * 上传图片
     *
     * @param multipartFile 图片文件
     * @param pictureUploadRequest 图片上传query参数
     * @param loginUser 用户信息，判断是否具备上传权限
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    /**
     * 将图片请求查询条件转换为 QueryWrapper
     * @param pictureQueryRequest 图片查询请求参数
     * @return QueryWrapper
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    /**
     * 获取图片封装类（单条）
     * @param picture 图片对象
     * @param request
     * @return
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 获取图片封装类（分页）
     * @param picturePage 图片分页对象
     * @param request
     * @return
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);
}
