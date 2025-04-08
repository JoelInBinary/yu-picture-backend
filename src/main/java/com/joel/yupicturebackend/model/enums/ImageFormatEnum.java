package com.joel.yupicturebackend.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ImageFormatEnum {
    JPEG("jpeg", "image/jpeg"),
    JPG("jpg", "image/jpeg"),
    PNG("png", "image/png"),
    WEBP("webp", "image/webp");

    private final String suffix;
    private final String mimeType;

    ImageFormatEnum(String suffix, String mimeType) {
        this.suffix = suffix;
        this.mimeType = mimeType;
    }

    public static List<String> getAllowedSuffixes() {
        return Arrays.stream(values())
                .map(ImageFormatEnum::getSuffix)
                .collect(Collectors.toList());
    }

    // 未来可扩展获取MIME类型的方法
    public String getSuffix() {
        return suffix;
    }

    public String getMimeType() {
        return mimeType;
    }
}