package com.tnite.jobwinner.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Sophia
 * @date 2025-04-08 22:03
 */
@Data
@TableName(value = "sophia_user")
public class SophiaUser {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @TableField(value = "name")
  private String name;

  //    @TableField(value = "name")
  private String myAge;
}