package com.weisj.fx.manager.impl;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.weisj.fx.bean.ExpressInfo;
import com.weisj.fx.bean.LogisticsData;
import com.weisj.fx.bean.OrderLogisticsBean;
import com.weisj.fx.manager.ILogisticsManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class LogisticsManger implements ILogisticsManager {
    @Override
    public void getOrderDetail(int orderId, final IOnManagerListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sell_member_id", PersonMessagePreferencesUtils.getUid());
        map.put("order_brand_id", String.valueOf(orderId));
        OkHttpClientManager.postAsyn(Urls.getoneorderbyid, map, new OkHttpClientManager.ResultCallback<OrderLogisticsBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getoneorderbyid);
            }

            @Override
            public void onResponse(OrderLogisticsBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getoneorderbyid);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getoneorderbyid);
                }
            }
        });
    }

    @Override
    public void getLogisticsData(String comeFrom, String number, final IOnManagerListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("queryNo", number);
        OkHttpClientManager.postAsyn(Urls.expressInfo, map, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.expressInfo);
            }

            @Override
            public void onResponse(String response) {
                if (response != null) {
                    LogisticsData data = jsonData(response);
                    if (data != null) {
                        listener.onSuccess(data, Urls.expressInfo);
                    }else{
                        listener.onFail(new RuntimeException("null"), Urls.expressInfo);
                    }
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.expressInfo);
                }
            }
        });
    }

    private LogisticsData jsonData(String str){
        try {
            JSONObject jsonObject = new JSONObject(str);
            LogisticsData data = new LogisticsData();
            data.setMessage(jsonObject.getString("message"));
            JSONObject result = jsonObject.getJSONObject("result");
            LogisticsData.DataResult dataResult = new LogisticsData.DataResult();
            dataResult.setBno(result.getString("bno"));
            dataResult.setState(result.getInt("state"));
            dataResult.setStateMsg(result.getString("stateMsg"));
            if (dataResult.getState() == 0){
                data.setResult(dataResult);
                return data;
            }
            JSONArray details = result.getJSONArray("details");
            List<LogisticsData.DataEntity> detailsData = new ArrayList<>();
            for (int i = 0; i<details.length();i++){
                LogisticsData.DataEntity dataEntity = new LogisticsData.DataEntity();
                dataEntity.setTime(details.getJSONObject(i).getString("time"));
                dataEntity.setContext(details.getJSONObject(i).getString("context"));
                detailsData.add(dataEntity);
            }
            dataResult.setDetails(detailsData);
            data.setResult(dataResult);
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
