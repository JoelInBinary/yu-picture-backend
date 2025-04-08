package com.joel.yupicturebackend.model.vo;

import lombok.Data;

import java.util.List;

/**
 * ClassName: PictureTagCategory
 * Description:
 * 图片的标签和分类列表视图
 * @Author Joel
 */
@Data
public class PictureTagCategory {

    /**
     * 图片的标签列表
     */
    private List<String> tagList;

    /**
     * 图片的分类列表
     */
    private List<String> categoryList;
}
