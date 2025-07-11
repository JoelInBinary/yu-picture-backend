package com.joel.yupicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joel.yupicturebackend.api.aliyunai.model.CreateOutPaintingTaskResponse;
import com.joel.yupicturebackend.model.dto.picture.*;
import com.joel.yupicturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joel.yupicturebackend.model.entity.User;
import com.joel.yupicturebackend.model.vo.PictureVO;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * 删除图片
     * @param pictureId
     * @param loginUser
     */
    void deletePicture(long pictureId, User loginUser);

    /**
     * 编辑图片
     * @param pictureEditRequest
     * @param loginUser
     */
    void editPicture(PictureEditRequest pictureEditRequest, User loginUser);

    /**
     * 清理图片文件
     * @param oldPicture
     */
    void clearPictureFile(Picture oldPicture);

    /**
     * 校验是否有操作空间图片的权限
     * @param loginUser
     * @param picture
     */
    void checkPictureAuth(User loginUser, Picture picture);

    /**
     * 根据颜色搜索图片
     * @param spaceId
     * @param picColor
     * @param loginUser
     * @return
     */
    List<PictureVO> searchPictureByColor(Long spaceId, String picColor, User loginUser);

    /**
     * 批量编辑图片
     * @param pictureEditByBatchRequest
     * @param loginUser
     */
    @Transactional(rollbackFor = Exception.class)
    void editPictureByBatch(PictureEditByBatchRequest pictureEditByBatchRequest, User loginUser);

    /**
     * 创建 AI 扩图任务
     * @param createPictureOutPaintingTaskRequest
     * @param loginUser
     * @return
     */
    CreateOutPaintingTaskResponse createPictureOutPaintingTask(CreatePictureOutPaintingTaskRequest createPictureOutPaintingTaskRequest, User loginUser);
}
