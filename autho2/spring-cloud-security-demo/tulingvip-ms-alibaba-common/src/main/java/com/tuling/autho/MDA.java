package com.tuling.autho;

/**
 * Created by smlz on 2019/12/29.
 */
public class MDA {


    //通过网关请求认证服务器 token
    public static final String AUTH_SERVER_URL = "http://gateway-server/oauth/token";

    public static final String checkTokenUrl = "http://auth-server/oauth/check_token";


//
//    public static final String ORDER_SERVER_CREATEORDER = "http://gateway-server/order/saveOrder";
//
//    public static final String ORDER_SERVER_DETAIL = "http://gateway-server/order/selectOrderInfoByIdAndUserName";
//
//    public static final String GET_PRODUCT_INFO = "http://gateway-server/product/selectProductInfoById/";

    public static final String CLIENT_ID = "portal_app";

    public static final String CLIENT_SECRET = "portal_app";

    public static final String TOKEN_INFO_KEY = "portal-token-info-key";

}
