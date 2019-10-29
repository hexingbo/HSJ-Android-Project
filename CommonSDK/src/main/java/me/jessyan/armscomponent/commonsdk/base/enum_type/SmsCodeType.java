package me.jessyan.armscomponent.commonsdk.base.enum_type;

/**
 * @Author :hexingbo
 * @Date :2019/9/9
 * @FileName： SmsCodeType
 * @Describe : 1,注册验证码 ；2，登陆验证码 ；3，微信绑定时获取验证码；4找回密码；5修改登录密码
 */
public enum SmsCodeType {
    /**
     * 1、注册验证码
     */
    SmsCodeType_Register,
    /**
     * 2、登陆验证码
     */
    SmsCodeType_Login,
    /**
     * 3、微信绑定验证码
     */
    SmsCodeType_WX_Bind,
    /**
     * 4、找回密码
     */
    SmsCodeType_FindLoginPwd,
    /**
     * 5、修改登录密码
     */
    SmsCodeType_UpdateLoginPwd,
}
