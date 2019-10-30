package com.echronos.epoandroid.mvp.model.entity.share;


import com.echronos.epoandroid.mvp.interfaces.share.ContentType;
import com.echronos.epoandroid.mvp.interfaces.share.ShareType;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29
 * 描    述：分享内容包装
 * =============================================
 */
public class ShareContent {

  private ContentType contentType;

  private ShareType shareType;

  private String title;

  private String content;

  private String url;

  private String imageUrl;

  private String musicUrl;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ContentType getContentType() {
    return contentType;
  }

  public void setContentType(ContentType contentType) {
    this.contentType = contentType;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getMusicUrl() {
    return musicUrl;
  }

  public void setMusicUrl(String musicUrl) {
    this.musicUrl = musicUrl;
  }

  public ShareType getShareType() {
    return shareType;
  }

  public void setShareType(ShareType shareType) {
    this.shareType = shareType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
