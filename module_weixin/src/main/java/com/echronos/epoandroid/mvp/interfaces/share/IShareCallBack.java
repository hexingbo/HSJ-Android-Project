package com.echronos.epoandroid.mvp.interfaces.share;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29
 * 描    述：分享回调接口
 * =============================================
 */
public interface IShareCallBack {

  void onComplete(Object o);

  void onError(Exception e);

  void onCancel();

}
