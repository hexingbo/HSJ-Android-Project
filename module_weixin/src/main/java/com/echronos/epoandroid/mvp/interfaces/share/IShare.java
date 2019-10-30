package com.echronos.epoandroid.mvp.interfaces.share;

import com.echronos.epoandroid.mvp.model.entity.share.ShareContent;

/**
 * 分享接口
 *
 * @author bobomee.
 *         wbwjx115@gmail.com
 */
public interface IShare {

  void share(ShareContent shareContent, IShareCallBack iShareCallBack);

}
