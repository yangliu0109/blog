package com.yangliu233.blog.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客标签业务对象 b_tag
 *
 * @author ruoyi
 * @date 2023-10-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TagBo extends BaseEntity {

    /**
     * 
     */
    @NotBlank(message = "不能为空", groups = { EditGroup.class })
    private String tagId;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tagName;


}
