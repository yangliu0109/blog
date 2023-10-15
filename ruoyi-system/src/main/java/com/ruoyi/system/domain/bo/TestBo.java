package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试业务对象 test
 *
 * @author ruoyi
 * @date 2023-09-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TestBo extends BaseEntity {

    /**
     * test1
     */
    @NotBlank(message = "test1不能为空", groups = { EditGroup.class })
    private String aaa;

    /**
     * test2
     */
    @NotBlank(message = "test2不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bbb;


}
