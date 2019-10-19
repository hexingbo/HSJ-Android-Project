package me.jessyan.armscomponent.commonsdk.base.bean;

/**
 * @Author :hexingbo
 * @Date :2019/8/9
 * @FileName： MessageEventBean
 * @Describe :
 */
public class MessageEventBean<T> {
    private String value;
    private T content;

    public MessageEventBean(String value) {
        this.value = value;
    }

    public MessageEventBean(String value, T content) {
        this.value = value;
        this.content = content;
    }

    public String getValue() {
        return value;
    }

    public void setType(String value) {
        this.value = value;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}


