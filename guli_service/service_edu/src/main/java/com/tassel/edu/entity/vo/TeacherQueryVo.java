package com.tassel.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: zsy
 * @date: 2020/12/6 10:09
 */
@Data
public class TeacherQueryVo {
    @ApiModelProperty(value = "教师名称, 模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1-高级讲师 2-首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin; // 注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
