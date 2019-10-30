package com.echronos.epoandroid.module_cart.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonsdk.base.bean.HttpResult;
import okhttp3.RequestBody;


/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2019/10/30 18:02
 * 描    述：
 * =============================================
 */
public interface ShoppingCartAllContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        /**
         * 添加商品到购物车
         *
         * @param sale_id
         * @param number
         * @return
         */
        Observable<HttpResult> postAddGoodsToCart(String sale_id,  String number);

        /**
         * 删除购物车商品数量
         *
         * @param snap_ids
         * @return
         */
        Observable<HttpResult> postDeleteGoods(List<String> snap_ids);

        /**
         * 修改购物车商品数量
         *
         * @param snap_id
         * @param num
         * @return
         */
        Observable<HttpResult> postUpdateCartSnapNum( String snap_id,  String num);

        /**
         * 购物车列表
         *
         * @param cat_type
         * @return
         */
        Observable<HttpResult> getCartList( String cat_type);

        /**
         * 推荐的商品
         *
         * @param page
         * @param pagesize
         * @return
         */
        Observable<HttpResult> getGoodsListTuiJian( int page, int pagesize);

        /**
         * 添加商品到收藏夹
         *
         * @param type
         * @param snap_ids
         * @return
         */
        Observable<HttpResult> postSaleToCollection( String type,  List<String> snap_ids);

    }
}
