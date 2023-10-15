package com.yangliu233.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import java.util.List;


/**
 * 博客管理视图对象 b_blog
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Data
@ExcelIgnoreUnannotated
public class BlogVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String blogId;

    /**
     * 文章标题
     */
    @ExcelProperty(value = "文章标题")
    private String title;

    /**
     * 文章类型内容（富文本）
     */
    @ExcelProperty(value = "文章类型内容", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "富=文本")
    private String content;

    /**
     * 文章分类id
     */
    @ExcelProperty(value = "文章分类id")
    private String categoryId;

    /**
     * 文字数量(单位：字)
     */
    @ExcelProperty(value = "文字数量(单位：字)")
    private String contentNum;

    /**
     * 文字占用的大小(单位：byte)
     */
    @ExcelProperty(value = "文字占用的大小(单位：byte)")
    private String contentSize;

    /**
     * 文章封面(url)
     */
    @ExcelProperty(value = "文章封面(url)")
    private String cover;
    /**
     * 文章类型内容（文字）
     */
    private String contentText;

    /**
     * 标签id，使用逗号分隔
     */
//    @ExcelProperty(value = "标签id，使用逗号分隔")
    private String tagId;

    private List<String> tagIds;

    /**
     * 标签id，使用逗号分隔
     */
    @ExcelProperty(value = "标签id")
    private String tagNames;

    @JsonFormat(pattern = "yyyy年MM月dd日 HH点mm分")
    private Date createTime;
}
