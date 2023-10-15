package com.yangliu233.blog.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客分类对象 b_category
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("b_category")
public class Category extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "category_id")
    private String categoryId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
