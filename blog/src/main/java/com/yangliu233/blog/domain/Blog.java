package com.yangliu233.blog.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客管理对象 b_blog
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("b_blog")
public class Blog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "blog_id")
    private String blogId;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章类型内容（富文本）
     */
    private String content;
    /**
     * 文章分类id
     */
    private String categoryId;
    /**
     * 文字数量(单位：字)
     */
    private String contentNum;
    /**
     * 文字占用的大小(单位：byte)
     */
    private String contentSize;
    /**
     * 文章类型内容（文字）
     */
    private String contentText;
    /**
     * 文章封面(url)
     */
    private String cover;
    /**
     * 标签id，使用逗号分隔
     */
    private String tagId;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
