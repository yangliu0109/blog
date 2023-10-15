package com.yangliu233.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 博客标签视图对象 b_tag
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Data
@ExcelIgnoreUnannotated
public class TagVo {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private String tagId;

    /**
     * 标签名称
     */
    @ExcelProperty(value = "标签名称")
    private String tagName;


}
