package com.egzosn.pay.wx.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liutf
 */
@Data
@Accessors(chain = true)
@XStreamAlias("xml")
public class SignTestBean {

    private String appid;
    private String mch_id;
    private String device_info;
    private String body;
    private String nonce_str;

}
