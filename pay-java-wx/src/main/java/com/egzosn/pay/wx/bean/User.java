package com.egzosn.pay.wx.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liutf
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("xml")
public class User {

    @XStreamAlias("user_name")
    private String userName;

    private String email;

}
