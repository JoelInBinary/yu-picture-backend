package com.joel.yupicturebackend.api.imagesearch;

import com.joel.yupicturebackend.api.imagesearch.model.ImageSearchResult;
import com.joel.yupicturebackend.api.imagesearch.sub.GetImageFirstUrlApi;
import com.joel.yupicturebackend.api.imagesearch.sub.GetImageListApi;
import com.joel.yupicturebackend.api.imagesearch.sub.GetImagePageUrlApi;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 图片搜索服务
 * 运用“门面模式”的设计模式。门面模式就是将多个API整合到一个门面类中，简化调用过程。
 */
@Slf4j
public class ImageSearchApiFacade {

    /**
     * 以图搜图
     * @param imageUrl
     * @return
     */
    public static List<ImageSearchResult> searchImage(String imageUrl) {
        // 获取以图搜图的页面地址 (Step 1)
        String imagePageUrl = GetImagePageUrlApi.getImagePageUrl(imageUrl);
        // 获取图片列表页面地址 (Step 2)
        String imageFirstUrl = GetImageFirstUrlApi.getImageFirstUrl(imagePageUrl);
        // 获取图片列表 (Step 3)
        List<ImageSearchResult> imageList = GetImageListApi.getImageList(imageFirstUrl);
        return imageList;
    }

    public static void main(String[] args) {
        // 测试以图搜图功能
        String imageUrl = "https://www.codefather.cn/logo.png";
        List<ImageSearchResult> resultList = searchImage(imageUrl);
        System.out.println("结果列表" + resultList);
    }
}
