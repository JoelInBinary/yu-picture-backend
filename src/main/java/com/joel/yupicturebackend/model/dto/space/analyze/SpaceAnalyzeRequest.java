package com.joel.yupicturebackend.model.dto.space.analyze;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用的空间分析请求
 * 其他空间分析请求可以继承该类
 */
@Data
public class SpaceAnalyzeRequest implements Serializable {

    /**
     * 空间 ID
     * 仅在 queryAll 和 queryPublic 均为false时生效，表示对特定空间进行分析，仅该空间的创建者和管理员可以使用。
     */
    private Long spaceId;

    /**
     * 是否查询公共图库
     * 为 true 时表示查询公共图库，仅管理员可使用。
     */
    private boolean queryPublic;

    /**
     * 全空间分析
     * 为 true 时表示查询所有空间，仅管理员可使用。
     */
    private boolean queryAll;

    private static final long serialVersionUID = 1L;
}
