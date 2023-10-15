package com.yangliu233.blog.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.NonNull;

/**
 * 博客管理业务对象 b_blog
 *
 * @author ruoyi
 * @date 2023-10-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BlogBo extends BaseEntity {

    /**
     *
     */
    @NotBlank(message = "不能为空", groups = { EditGroup.class })
    private String blogId;

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 文章类型内容（富文本）
     */
    @NotBlank(message = "文章类型内容（富文本）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * 文章分类id
     */
    @NotBlank(message = "文章分类id不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "标签id，使用逗号分隔不能为空", groups = { AddGroup.class, EditGroup.class })
    private List<String> tagIds;


}
