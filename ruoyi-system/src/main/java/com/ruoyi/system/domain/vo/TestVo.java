package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 测试视图对象 test
 *
 * @author ruoyi
 * @date 2023-09-12
 */
@Data
@ExcelIgnoreUnannotated
public class TestVo {

    private static final long serialVersionUID = 1L;

    /**
     * test1
     */
    @ExcelProperty(value = "test1")
    private String aaa;

    /**
     * test2
     */
    @ExcelProperty(value = "test2")
    private String bbb;


}
