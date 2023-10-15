package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试对象 test
 *
 * @author ruoyi
 * @date 2023-09-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("test")
public class Test extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * test1
     */
    @TableId(value = "aaa")
    private String aaa;
    /**
     * test2
     */
    private String bbb;

}
