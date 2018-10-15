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
public class MchApplyInfo {

    private String version;
    private String cert_sn;
    private String mch_id;
    private String nonce_str;
    private String sign_type;
    private String sign;
    private String business_code;
    private String id_card_copy;
    private String id_card_national;
    private String id_card_name;
    private String id_card_number;
    private String id_card_valid_time;
    private String account_number;
    private String account_name;
    private String account_bank;
    private String bank_address_code;
    private String bank_name;
    private String store_address_code;
    private String store_street;
    private String store_longitude;
    private String store_latitude;
    private String store_entrance_pic;
    private String indoor_pic;
    private String address_certification;
    private String merchant_shortname;
    private String service_phone;
    private String business;
    private String product_desc;
    private String qualifications;
    private String rate;
    private String business_addition_desc;
    private String business_addition_pics;
    private String contact;
    private String contact_phone;
    private String contact_email;

}
