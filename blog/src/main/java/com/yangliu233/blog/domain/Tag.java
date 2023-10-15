package com.yangliu233.blog.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客标签对象 b_tag
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("b_tag")
public class Tag extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "tag_id")
    private String tagId;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
