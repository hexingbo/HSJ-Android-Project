package com.echronos.epoandroid.mvp.manager;

import android.content.Context;
import android.graphics.Bitmap;

import com.echronos.epoandroid.mvp.interfaces.share.ContentType;
import com.echronos.epoandroid.mvp.interfaces.share.IShare;
import com.echronos.epoandroid.mvp.interfaces.share.IShareCallBack;
import com.echronos.epoandroid.mvp.interfaces.share.ShareType;
import com.echronos.epoandroid.mvp.model.entity.share.ShareContent;
import com.echronos.epoandroid.util.BitmapUtil;
import com.echronos.epoandroid.util.ShareUtil;
import com.echronos.epoandroid.util.ThreadUtil;
import com.echronos.epoandroid.util.WxLSUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/29
 * 描    述：微信分享
 * =============================================
 */
public class WechatShareManager implements IShare {

    private static final int THUMB_SIZE = 116;

    private static IWXAPI mIWXAPI;
    public static Context context;

    private static IShareCallBack mIShareCallBack;

    /**
     * friends 分享到对话:
     */
    public static final int WEIXIN_SHARE_TYPE_TALK = SendMessageToWX.Req.WXSceneSession;

    /**
     * friends TimeLine 分享到朋友圈:
     */
    public static final int WEIXIN_SHARE_TYPE_FRENDS = SendMessageToWX.Req.WXSceneTimeline;

    public WechatShareManager(Context activity, String wechatAppId) {
        context = activity;
        WxLSUtil.init(context, wechatAppId);
        mIWXAPI = WxLSUtil.getmIWXAPI();

    }

    @Override
    public void share(ShareContent shareContent, IShareCallBack iShareCallBack) {
//    WXAuthHandlerActivity.TYPE_LOGIN=2;
        mIShareCallBack = iShareCallBack;

        if (null != mIWXAPI) {
            ContentType contentType = shareContent.getContentType();
            switch (contentType) {
                case TEXT:
                    shareText(shareContent);
                    break;
                case PICTURE:
                    sharePicture(shareContent);
                    break;
                case WEBPAG:
                    shareWebPage(shareContent);
                    break;
                case MUSIC:
                    shareMusic(shareContent);
                    break;
            }
        }
    }

    private void shareText(ShareContent shareContent) {

        String text = shareContent.getContent();
        //初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
        //用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //transaction字段用于唯一标识一个请求
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        //发送的目标场景， 可以选择发送到会话 WXSceneSession 或者朋友圈 WXSceneTimeline。 默认发送到会话。
        ShareType shareType = shareContent.getShareType();
        if (shareType == ShareType.WECHAT) {
            req.scene = WEIXIN_SHARE_TYPE_TALK;
        } else {
            req.scene = WEIXIN_SHARE_TYPE_FRENDS;
        }
        mIWXAPI.sendReq(req);
    }

    private void sharePicture(ShareContent shareContent) {
        WXImageObject imgObj = new WXImageObject();
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        ShareType shareType = shareContent.getShareType();
        if (shareType == ShareType.WECHAT) {
            req.scene = WEIXIN_SHARE_TYPE_TALK;
        } else {
            req.scene = WEIXIN_SHARE_TYPE_FRENDS;
        }
        sendShare(shareContent.getImageUrl(), req);
    }

    private void shareWebPage(final ShareContent shareContent) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = shareContent.getUrl();
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = shareContent.getTitle();
        msg.description = shareContent.getContent();

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        ShareType shareType = shareContent.getShareType();
        if (shareType == ShareType.WECHAT) {
            req.scene = WEIXIN_SHARE_TYPE_TALK;
        } else {
            req.scene = WEIXIN_SHARE_TYPE_FRENDS;
        }
        sendShare(shareContent.getImageUrl(), req);
    }

    private void shareMusic(ShareContent shareContent) {

        WXMusicObject music = new WXMusicObject();
        //Str1+"#wechat_music_url="+str2 ;str1是网页地址，str2是音乐地址。

        music.musicUrl = shareContent.getUrl() + "#wechat_music_url=" + shareContent.getMusicUrl();
        WXMediaMessage msg = new WXMediaMessage(music);
        msg.title = shareContent.getTitle();
        msg.description = shareContent.getContent();

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        ShareType shareType = shareContent.getShareType();
        if (shareType == ShareType.WECHAT) {
            req.scene = WEIXIN_SHARE_TYPE_TALK;
        } else {
            req.scene = WEIXIN_SHARE_TYPE_FRENDS;
        }
        sendShare(shareContent.getImageUrl(), req);
    }

    private void sendShare(final String imageUrl, final SendMessageToWX.Req req) {

        ThreadUtil.getShortPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap image = BitmapUtil.getBitmapFromUrl(imageUrl);
                    if (image != null) {
                        // todo image.length <= 10485760
                        if (req.message.mediaObject instanceof WXImageObject) {
                            req.message.mediaObject = new WXImageObject(image);
                        }
                        req.message.thumbData =
                                ShareUtil.bmpToByteArray(BitmapUtil.scaleCenterCrop(image, THUMB_SIZE, THUMB_SIZE));
                    }
                    // 就算图片没有了 尽量能发出分享
                    mIWXAPI.sendReq(req);
                } catch (Throwable throwable) {

                }
            }
        });
    }

    public static IShareCallBack getIShareCallBack() {
        return mIShareCallBack;
    }

    public static void setIShareCallBack(IShareCallBack iShareCallBack) {
        context = null;
        mIShareCallBack = iShareCallBack;
    }

}
