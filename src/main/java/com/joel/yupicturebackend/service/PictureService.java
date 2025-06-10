package com.joel.yupicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joel.yupicturebackend.model.dto.picture.PictureQueryRequest;
import com.joel.yupicturebackend.model.dto.picture.PictureReviewRequest;
import com.joel.yupicturebackend.model.dto.picture.PictureUploadByBatchRequest;
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
     * @param inputSource
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(Object inputSource,
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

    /**
     * 图片审核
     * @param pictureReviewRequest 图片审核请求参数
     * @param loginUser
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

    /**
     * 审核填充参数
     * @param picture
     * @param loginUser
     */
    void fillReviewParams(Picture picture, User loginUser);

    /**
     * 批量抓取和创建图片
     *
     * @param pictureUploadByBatchRequest 图片批量抓取请求
     * @param loginUser
     * @return 成功创建的图片数
     */
    Integer uploadPictureByBatch(
            PictureUploadByBatchRequest pictureUploadByBatchRequest,
            User loginUser
    );

    /**
     * 清理图片文件
     * @param oldPicture
     */
    void clearPictureFile(Picture oldPicture);

}
