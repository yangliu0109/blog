package com.yangliu233.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 博客分类视图对象 b_category
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Data
@ExcelIgnoreUnannotated
public class CategoryVo {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private String categoryId;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称")
    private String name;


}
