package me.jessyan.armscomponent.commonsdk.base.bean;

/**
 * @Author :hexingbo
 * @Date :2019/8/4
 * @FileName： HttpResult
 * @Describe :
 */
public class HttpResult<T> extends MyHttpResult {

    /**
     * errcode : 0
     * msg : ok
     * data : {"access_token":"c21eeddc-98fa-4efb-b045-21c3aa9bdbaf"}
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "errcode=" + getErrcode() +
                ", msg='" + getMsg() + '\'' +
                "data=" + data +
                '}';
    }
}