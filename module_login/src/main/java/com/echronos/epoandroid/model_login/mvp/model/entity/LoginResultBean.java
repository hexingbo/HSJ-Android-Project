package com.echronos.epoandroid.model_login.mvp.model.entity;

import java.io.Serializable;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/28
 * 描    述：LoginResultBean
 * =============================================
 */
public class LoginResultBean implements Serializable {

    private String memberid;
    private String openid;
    private String access_token;
    private String head;
    private String name;
    private String phone;
    private String account_type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    @Override
    public String toString() {
        return "LoginResultBean{" +
                "memberid='" + memberid + '\'' +
                ", openid='" + openid + '\'' +
                ", access_token='" + access_token + '\'' +
                ", head='" + head + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", account_type=" + account_type +
                '}';
    }
}
