package me.jessyan.armscomponent.commonsdk.base.bean;

/**
 * @Author :hexingbo
 * @Date :2019/8/4
 * @FileName： MyHttpResult
 * @Describe :
 */
public class MyHttpResult {
    /**
     * errcode : 0
     * msg : ok
     * data : {"access_token":"c21eeddc-98fa-4efb-b045-21c3aa9bdbaf"}
     */

    private int errcode;
    private String msg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "errcode=" + errcode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
