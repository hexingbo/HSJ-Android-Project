package com.echronos.epoandroid.me.mvp.model.entity;

import java.io.Serializable;

/**
 * @Author :hexingbo
 * @Date :2019/3/17
 * @FileName： UserInfo
 * @Describe :
 */
public class UserInfo implements Serializable {

    /**
     * {
     * "attention_num": 566,
     * "fans_num": 37,
     * "product_num": 6,
     * "productbrand_num": 0,
     * "shopnum": 37,
     * "address": "湖北",
     * "nickname": "李强",
     * "gongsi": "华南城",
     * "head_url": "http://image.huacaigou.com/media%2Fhead%2F1313%2Fhsj_upload_eachb.jpg",
     * "loginname": "wechat20190125095840",
     * "selfmemberid": 1313,
     * "sex": "1",
     * "industry": "",
     * "age": null,
     * "doorHeader": ""
     * }
     */

    private String attention_num;
    private String fans_num;
    private String product_num;
    private String productbrand_num;
    private String shopnum;
    private String address;
    private String nickname;
    private String gongsi;
    private String head_url;
    private String loginname;
    private String selfmemberid;
    private String sex;
    private String industry;
    private String age;
    private String doorHeader;
    private String city;
    private String county;
    private String province;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getShopnum() {
        return shopnum;
    }

    public void setShopnum(String shopnum) {
        this.shopnum = shopnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDoorHeader() {
        return doorHeader;
    }

    public void setDoorHeader(String doorHeader) {
        this.doorHeader = doorHeader;
    }

    public String getAttention_num() {
        return attention_num;
    }

    public void setAttention_num(String attention_num) {
        this.attention_num = attention_num;
    }

    public String getFans_num() {
        return fans_num;
    }

    public void setFans_num(String fans_num) {
        this.fans_num = fans_num;
    }

    public String getGongsi() {
        return gongsi;
    }

    public void setGongsi(String gongsi) {
        this.gongsi = gongsi;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProduct_num() {
        return product_num;
    }

    public void setProduct_num(String product_num) {
        this.product_num = product_num;
    }

    public String getProductbrand_num() {
        return productbrand_num;
    }

    public void setProductbrand_num(String productbrand_num) {
        this.productbrand_num = productbrand_num;
    }

    public String getSelfmemberid() {
        return selfmemberid;
    }

    public void setSelfmemberid(String selfmemberid) {
        this.selfmemberid = selfmemberid;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "attention_num='" + attention_num + '\'' +
                ", fans_num='" + fans_num + '\'' +
                ", product_num='" + product_num + '\'' +
                ", productbrand_num='" + productbrand_num + '\'' +
                ", shopnum='" + shopnum + '\'' +
                ", address='" + address + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gongsi='" + gongsi + '\'' +
                ", head_url='" + head_url + '\'' +
                ", loginname='" + loginname + '\'' +
                ", selfmemberid='" + selfmemberid + '\'' +
                ", sex='" + sex + '\'' +
                ", industry='" + industry + '\'' +
                ", age='" + age + '\'' +
                ", doorHeader='" + doorHeader + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
