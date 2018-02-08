package com.weisj.fx.utils;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class Urls {
    public static final String IP = "http://119.23.175.61";
//    public static final String IP = "http://shop.fx-sf.com";

    public static final String imageUrl = "http://img.sfddj.com/";
    // 获取手机验证码
    public static final String getmoblieticket = IP + "/fxfront/member/getmoblieticket?appid=APPID";
    // 验证手机验证码
    public static final String checkticket = IP + "/fxfront/member/checkticket?appid=APPID";
    // 绑定手机号
    public static final String bindcellphonebypassword = IP + "/fxfront/member/bindcellphonebypassword?appid=APPID";
    // 会员注册
    public static final String register = IP + "/fxfront/member/register?appid=APPID";
    // 重新上传sn
    public static final String refreshmobilesn = IP + "/fxfront/member/refreshmobilesn?appid=APPID";
    // 忘记密码
    public static final String lostpassword = IP + "/fxfront/member/lostpassword?appid=APPID";
    // 会员登陆
    public static final String login = IP + "/fxfront/member/login?appid=APPID";
    // 个人信息中心
    public static final String membercenter = IP + "/fxfront/member/membercenter?appid=APPID";
    // 修改个人地区
    public static final String modifydistrict = IP + "/fxfront/member/modifydistrict?appid=APPID";

    //1.1 热门城市接口
    public static final String gethotregions = IP + "/fxfront/interface/gethotregions?appid=APPID";
    //1.11 添加收货人
    public static final String addconsignee = IP + "/fxfront/member/addconsignee?appid=APPID";
    //1.12 设置默认收货地址
    public static final String setdefaultconsignee = IP + "/fxfront/member/setdefaultconsignee?appid=APPID";
    //1.13 删除收货地址
    public static final String delconsignee = IP + "/fxfront/member/delconsignee?appid=APPID";
    //2.1 用户地址信息
    public static final String getAddress = IP + "/fxfront/order/address?appid=APPID";
    // 所有分类（大类）
    public static final String getcategorybytopfilter = IP + "/fxfront/interface/getcategorybytopfilter?appid=APPID";
    // 所有分类（小类）
    public static final String getcategorybychildfilter = IP + "/fxfront/interface/getcategorybychildfilter?appid=APPID";
    //    //2.1 分类查询商品接口
//    public static final String getgoodsbycategoryorder = IP + "/fxfront/interface/getgoodsbycategoryorder?appid=APPID";
    //2.1 分类查询商品接口
    public static final String getgoodsbycategoryorder = IP + "/fxfront/interface/getGoodsByCategoryDistrictBrand?appid=APPID";
    //2.1 分类查询商品接口
    public static final String getgoodsbycouponyorder = IP + "/fxfront/interface/getgoodsbycouponyorder?appid=APPID";
    //2.3 优惠券首页接口
    public static final String couponpage = IP + "/fxfront/interface/couponpage?appid=APPID";
    //2.3 按类别查单优惠券接口
    public static final String couponcategory = IP + "/fxfront/interface/couponcategory?appid=APPID";
    //2.5 商品详情app接口
    public static final String goodsdetailbyapp = IP + "/fxfront/interface/goodsdetailbyapp?appid=APPID";
    //2.5 商品详情app接口
    public static final String goodscontent = IP + "/fxfront/interface/goodscontent?appid=APPID";
    // 首页接口
    public static final String homepage = IP + "/fxfront/interface/homepage?appid=APPID";
    // 首页material
    public static final String material = IP + "/fx/material";

    // 2.8 导航条列表
    public static final String getmenu = IP + "/fxfront/interface/getmenu?appid=APPID";
    // 分享good url
    public static final String shareGoodUrl = IP + "/Shop/goods?goods_id=";
    // 查询我的订单
    public static final String myorders = IP + "/fxfront/shoporder/myorders?appid=APPID";
    // 申请商家接口
    public static final String apply_company = IP + "/fxfront/interface/apply_company?appid=APPID";
    // 闪屏广告
    public static final String beginad = IP + "/fxfront/interface/beginad?appid=APPID";
    // 查询商品和活动分享信息接口
    public static final String getsharerecordsbymemberid = IP + "/fxfront/share/getsharerecordsbymemberid?appid=APPID";
    // 我的收入
    public static final String getcommissionincome = IP + "/fxfront/member/getcommissionincome?appid=APPID";
    // 删除订单
    public static final String deleteorder = IP + "/fxfront/shoporder/deleteorder?appid=APPID";
    // 公告
    public static final String getarticlelist = IP + "/fxfront/interface/getarticlelist?appid=APPID";
    // 查询我的订单的订单详情
    public static final String getoneorderbyid = IP + "/fxfront/shoporder/getoneorderbyid?appid=APPID";
    // 浏览商品或活动的微信用户信息接口
    public static final String getwxinfobythreeid = IP + "/fxfront/share/getwxinfobythreeid?appid=APPID";
    // 查看物流
    public static final String checkLogisticsIp = "http://api.ickd.cn/?id=200267&secret=c604ddb38b30464d2d34f8f060d27867&ord=desc";
    //1.30设置支付密码
    public static final String changepaypassword = IP + "/fxfront/member/changepaypassword?appid=APPID";
    //修改密码
    public static final String changepassword = IP + "/fxfront/member/changepassword?appid=APPID";
    // 版本更新
    public static final String updateVersion = IP + "/fxfront/interface/getversion?appid=APPID";
    // 1.26 获取我的钱包中各种统计金额
    public static final String getallstatusmoney = IP + "/fxfront/member/getallstatusmoney?appid=APPID";
    // 1.21 获取会员绑定的银行卡信息
    public static final String getbindbankcardinfo = IP + "/fxfront/member/getbindbankcardinfo?appid=APPID";
    // 1.23 会员绑定银行卡
    public static final String bindbankcard = IP + "/fxfront/member/bindbankcard?appid=APPID";
    // 1.23 修改会员绑定银行卡
    public static final String editbankcard = IP + "/fxfront/member/editbankcard?appid=APPID";
    //1.28修改支付密码
    public static final String setpaypassword = IP + "/fxfront/member/setpaypassword?appid=APPID";
    //1.29忘记支付密码
    public static final String lostpaypassword = IP + "/fxfront/member/lostpaypassword?appid=APPID";
    //验证验证码
    public static final String checkTicket = IP + "/fxfront/member/checkticket?appid=APPID";
    // 1.24 会员申请提现余额
    public static final String withdrawusermoney = IP + "/fxfront/member/withdrawusermoney?appid=APPID";
    // 1.25 获取某年某月的账单信息
    public static final String getaccountbill = IP + "/fxfront/member/getaccountbill?appid=APPID";
    // 商品和活动分享的浏览次数兑换余额的接口
    public static final String exchange = IP + "/fxfront/share/exchange?appid=APPID";
    // 商品和活动的分享接口
    public static final String record_share = IP + "/fxfront/share/record_share?appid=APPID";
    // 修改个人昵称
    public static final String updateUsernName = IP + "/fxfront/member/modifynickname?appid=APPID";
    // 修改个人头像
    public static final String updateUserImage = IP + "/fxfront/member/uploadmemberpic?appid=APPID";
    // 修改个人性别
    public static final String updateUserSex = IP + "/fxfront/member/modifysex?appid=APPID";

    public static final String modifystaffid = IP + "/fxfront/member/modifystaffid?appid=APPID";

    public static final String uploadError = IP + "/fxfront/report/reporterror?appid=APPID";

    // 31.5 查看员工分享的转化率
    public static final String getShareStatistics = IP + "/fxfront/share/getsharestatistics?appid=APPID";
    // 获取全部区域
    public static final String getallregions = IP + "/fxfront/interface/getallregions?appid=APPID";
    // 获取该区域下品牌
    public static final String getbrandbydistrict = IP + "/fxfront/interface/getbrandbydistrict?appid=APPID";
    // 产品评价
    public static final String goodscomment = IP + "/fxfront/interface/goodscomment?appid=APPID";

    public static final String expressInfo = IP + "/fx/queryExpress";
    // 活动接口
    public static final String getAllGuessActivitys = IP + "/fxfront/guess/getAllGuessActivitys?appid=APPID";
    // 活动产品接口
    public static final String getOneGuessActivity = IP + "/fxfront/guess/getOneGuessActivity?appid=APPID";
    // 活动分享接口
    public static final String getAllShareActivitys = IP + "/fxfront/guess/getAllShareActivitys?appid=APPID";
    // 活动分享接口
    public static final String getOneShareActivity = IP + "/fxfront/guess/getOneShareActivity?appid=APPID";
    // 删除接口
    public static final String delShareRecord = IP + "/fxfront/share/delShareRecord?appid=APPID";
    // 高计提专区
    public static final String getHighCommissionGoodsList = IP + "/fxfront/interface/getHighCommissionGoodsList?appid=APPID";


}
