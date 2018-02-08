package com.weisj.fx.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.weisj.fx.R;
import com.weisj.fx.adapter.ConversionRateAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.ConversionRateBean;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.CustomBottomPopWindow;
import com.weisj.fx.view.LineChatView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BBMJ on 2016/8/8.
 */
public class ConversionrRateActivity extends BaseActivity implements View.OnClickListener {


    private ListView listView;
    private LinearLayout lineChat;
    private LinearLayout topline;
    int Greedcolor = 0xff1AE190;
    private int Type = 1;
    private CustomBottomPopWindow customBottomPopWindow;
    private TextView line_text;

    List<ConversionRateBean.DataEntity.ShareGoodsInfoDomainListEntity> listEntities = new ArrayList<>();
    @Override
    public View initView(Bundle savedInstanceState) {

        View view = this.getLayoutInflater().inflate(R.layout.activity_conversionrate,null);

        getRightIcon(true).setOnClickListener(this);

        setRightText("筛选",true);
        initView(view);

        return view;
    }


    private void initView( View view){

        listView = (ListView)view.findViewById(R.id.listview);
        lineChat = (LinearLayout)view.findViewById(R.id.lineChat);
        line_text = (TextView)view.findViewById(R.id.line_text);
        line_text.setText("昨日访问曲线图");
        Getbindbankcardinfo();



        List<String> list = new ArrayList<>();
        list.add("昨日");
        list.add("上一周");
        list.add("上一月");

         customBottomPopWindow = new CustomBottomPopWindow(this, list, new CustomBottomPopWindow.PopupListener() {
            @Override
            public void onItemClick(View v, int position, int flag) {
                switch (position){

                    case 0:
                        Type=1;
                        Getbindbankcardinfo();
                        break;
                    case 1:
                        Type=2;
                        Getbindbankcardinfo();
                        break;
                    case 2:
                        Type=3;
                        Getbindbankcardinfo();
                        break;
                }
            }
        });

    }


    @Override
    public String setTitleStr() {
        return "转化率";
    }



    // 1.21 获取会员绑定的银行卡信息
    private void Getbindbankcardinfo() {
        showLoading();
        final Map<String, String> recommendParams = new HashMap<String, String>();
        recommendParams.put("member_id", PersonMessagePreferencesUtils.getUid());
        recommendParams.put("type", String.valueOf(Type));
        OkHttpClientManager.postAsyn(Urls.getShareStatistics, recommendParams, new OkHttpClientManager.ResultCallback<ConversionRateBean>() {
            @Override
            public void onError(Request request, Exception e) {
                showLoadFinish();
            }

            @Override
            public void onResponse(ConversionRateBean response) {
                showLoadFinish();
                if (response.getCode().equals("1")) {

                        List<Integer> milliliter = new ArrayList<Integer>();
                        int Max = 1;
                        int Mid=1;
                        String[] days;
                    SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
                    switch (Type){
                            case 1:
                                line_text.setText("昨日访问曲线图");
                                lineChat.removeAllViews();
                                days = new String[] { "0", "3", "6", "9", "12",
                                        "15", "18", "21", "24" };
                                milliliter = new ArrayList<>();
                                if (response.getData().getShare_statistic_domain_list().size()<8){

                                    for (int i =0;i<8;i++){
                                        milliliter.add(1);
                                        Mid= 1;
                                        Max = 1;

                                    }
                                }else{
                                    for (int i=0;i<response.getData().getShare_statistic_domain_list().size();i++){
                                        milliliter.add(response.getData().getShare_statistic_domain_list().get(i).getAccess_count()+1);
                                        Mid=response.getData().getShare_statistic_domain_list().get(i).getAccess_count()+1;
                                        if(Max<Mid){
                                            Max = Mid;
                                        }
                                    }
                                }
                                lineChat.addView(new LineChatView(ConversionrRateActivity.this,milliliter,Greedcolor,2,days,Max));
                                break;


                            case 2:
                                line_text.setText("上一周访问曲线图");
                                days = new String[7];
                                int day =7-response.getData().getShare_statistic_domain_list().size();

                                lineChat.removeAllViews();
                                milliliter = new ArrayList<>();

                                if (response.getData().getShare_statistic_domain_list().size()>0) {
                                    if (response.getData().getShare_statistic_domain_list().size() < 7) {

                                        for (int i = 0; i < 7 - response.getData().getShare_statistic_domain_list().size(); i++) {
                                            milliliter.add(1);
                                            Mid = 1;
                                            Max = 1;
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.set(Calendar.MONTH, Integer.parseInt(response.getData().getShare_statistic_domain_list().get(0).getShare_date().substring(0, 2)) - 1);
                                            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(response.getData().getShare_statistic_domain_list().get(0).getShare_date().substring(3, 5)));

                                            calendar.add(Calendar.DAY_OF_MONTH, -day);

                                            day--;
                                            Date lastMonth = calendar.getTime();
                                            sf.format(lastMonth);
                                            days[i] = sf.format(lastMonth);
                                        }
                                        int j = 0;
                                        for (int k = 7 - response.getData().getShare_statistic_domain_list().size(); k < 7; k++) {

                                            milliliter.add(response.getData().getShare_statistic_domain_list().get(j).getAccess_count() + 1);
                                            Mid = response.getData().getShare_statistic_domain_list().get(j).getAccess_count() + 1;

                                            if (Max < Mid) {
                                                Max = Mid;
                                            }
                                            days[k] = response.getData().getShare_statistic_domain_list().get(j).getShare_date();
                                            j++;
                                        }

                                    } else {
                                        days = new String[7];
                                        for (int i = 0; i < 7; i++) {
                                            milliliter.add(response.getData().getShare_statistic_domain_list().get(i).getAccess_count() + 1);
                                            Mid = response.getData().getShare_statistic_domain_list().get(i).getAccess_count() + 1;
                                            if (Max < Mid) {
                                                Max = Mid;
                                            }
                                            days[i] = response.getData().getShare_statistic_domain_list().get(i).getShare_date();
                                        }
                                    }
                                }else {
                                    day =7;
                                    for (int i = 0; i < 7; i++) {
                                        milliliter.add(1);
                                        Mid = 1;
                                        Max = 1;
                                        Calendar calendar = Calendar.getInstance();

                                        calendar.add(Calendar.DAY_OF_MONTH, -day);

                                        day--;
                                        Date lastMonth = calendar.getTime();
                                        sf.format(lastMonth);
                                        days[i] = sf.format(lastMonth);
                                    }

                                }
                                lineChat.addView(new LineChatView(ConversionrRateActivity.this,milliliter,Greedcolor,2,days,Max));

                                break;


                            case 3:
                                line_text.setText("上一月访问曲线图");
                                days = new String[30];
                                int Thirday =30-response.getData().getShare_statistic_domain_list().size();

                                lineChat.removeAllViews();
                                milliliter = new ArrayList<>();

                                if (response.getData().getShare_statistic_domain_list().size()>0) {
                                if (response.getData().getShare_statistic_domain_list().size()<30){

                                    for (int i =0;i<30-response.getData().getShare_statistic_domain_list().size();i++){
                                        milliliter.add(1);
                                        Mid= 1;
                                        Max = 1;
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.set(Calendar.MONTH, Integer.parseInt(response.getData().getShare_statistic_domain_list().get(0).getShare_date().substring(0, 2))-1);
                                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(response.getData().getShare_statistic_domain_list().get(0).getShare_date().substring(3, 5)));

                                        calendar.add(Calendar.DAY_OF_MONTH, -Thirday);

                                        Thirday--;
                                        Date lastMonth = calendar.getTime();
                                        sf.format(lastMonth);
                                        days[i] = sf.format(lastMonth);
                                    }
                                    int j =0 ;
                                    for(int k=30-response.getData().getShare_statistic_domain_list().size();k<30;k++){

                                        milliliter.add(response.getData().getShare_statistic_domain_list().get(j).getAccess_count()+1);
                                        Mid=response.getData().getShare_statistic_domain_list().get(j).getAccess_count()+1;

                                        if(Max<Mid){
                                            Max = Mid;
                                        }
                                        days[k] = response.getData().getShare_statistic_domain_list().get(j).getShare_date();
                                        j++;
                                    }

                                }else{
                                    days = new String[30];
                                    for(int i=0;i<30;i++){
                                        milliliter.add(response.getData().getShare_statistic_domain_list().get(i).getAccess_count()+1);
                                        Mid=response.getData().getShare_statistic_domain_list().get(i).getAccess_count()+1;
                                        if(Max<Mid){
                                            Max = Mid;
                                        }
                                        days[i] = response.getData().getShare_statistic_domain_list().get(i).getShare_date();
                                    }
                                }  }else {
                                    day =30;
                                    for (int i = 0; i < 30; i++) {
                                        milliliter.add(1);
                                        Mid = 1;
                                        Max = 1;
                                        Calendar calendar = Calendar.getInstance();

                                        calendar.add(Calendar.DAY_OF_MONTH, -day);

                                        day--;
                                        Date lastMonth = calendar.getTime();
                                        sf.format(lastMonth);
                                        days[i] = sf.format(lastMonth);
                                    }

                                }
                                lineChat.addView(new LineChatView(ConversionrRateActivity.this,milliliter,Greedcolor,2,days,Max));
                                break;



                        }



                        listView.setAdapter(new ConversionRateAdapter<>(ConversionrRateActivity.this, response.getData().getShare_goods_info_domain_list()));



                    }


            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.root_head_right_icon:
                customBottomPopWindow.show(this);
                break;

        }
    }

    @Override
    public void onRootViewClick(View v) {
        super.onRootViewClick(v);

        if (v.getId() ==R.id.root_head_right_text){
            customBottomPopWindow.show(this);
        }

    }

    @Override
    public void getRefreshData() {

    }
}
