package com.my.netty.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: lp
 * @Date: 2018/7/2 13:29
 * @Description: 公共的返回结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 使用构建者模式来使用bean
public class JsonResult implements java.io.Serializable  {

    private static final long serialVersionUID = -6798134478062697315L;
    /**
     * 状态码
     */
    private String status = ResultStatus.SUCCESS.getStatus();
    /**
     * 内容
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

}
