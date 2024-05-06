package com.zzsmart.qomo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采集文件标签
 * </p>
 *
 * @author WangBo
 * @since 2024-04-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("qomo_casc_collect_file_tag")
@ApiModel(value = "CollectFileTag对象", description = "采集文件标签")
public class CollectFileTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件ID
     */
    @ApiModelProperty("文件ID")
    private Integer fileId;

    /**
     * 文件编码
     */
    @ApiModelProperty("文件编码")
    private String fileCode;

    /**
     * 图号
     */
    @ApiModelProperty("图号")
    private String figureNo;

    /**
     * 型号
     */
    @ApiModelProperty("型号")
    private String modelNo;

    /**
     * 产品编号
     */
    @ApiModelProperty("产品编号")
    private String productCode;

    /**
     * 批次号
     */
    @ApiModelProperty("批次号")
    private String batchNo;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    private String createBy;

    /**
     * create time
     */
    @ApiModelProperty("create time")
    private LocalDateTime createTime;

    private String updateBy;

    /**
     * update time
     */
    @ApiModelProperty("update time")
    private LocalDateTime updateTime;
}
