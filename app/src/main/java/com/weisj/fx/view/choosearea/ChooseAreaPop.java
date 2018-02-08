package com.weisj.fx.view.choosearea;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.gson.Gson;
import com.weisj.fx.R;
import com.weisj.fx.bean.ProvinceCityBean;
import com.weisj.fx.bean.ProvinceCityBean2;
import com.weisj.fx.utils.SystemConfig;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by BBMJ on 2015/12/14.
 */
public class ChooseAreaPop extends Dialog implements OnWheelChangedListener, View.OnClickListener {


    private Activity activity;

    // wheelView控件 省会
    private WheelView mViewProvince;
    // wheelView控件 城市
    private WheelView mViewCity;
    // wheelView控件 区域
    private WheelView mViewArea;


    // 确定按钮
    private Button btn_confirm;

    // 当前省的名称
    protected String mCurrentProviceName = "", mCurrentProviceId;
    //当前市的名称
    protected String mCurrentCityName = "", mCurrentCityId;
    //当前区域的名称
    protected String mCurrentAreaName = "", mCurrentAreaId;


    private ArrayWheelAdapter<String> arrayProviceWheelAdapter;
    private ArrayWheelAdapter<String> arrayCityWheelAdapter;
    private ArrayWheelAdapter<String> arrayAreaWheelAdapter;

    private IDataCallback callback;

    public ProvinceCityBean provinceCityBean;
    private int flag = 0;
    private int visItem = 3;

    public ChooseAreaPop(Activity activity, int themeResId, IDataCallback callback) {
        super(activity, themeResId);

        this.activity = activity;
        this.callback = callback;
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的地位
        window.setWindowAnimations(R.style.mystyle); // 添加动画
        initProvinceDatas();
    }

    public ChooseAreaPop(Activity activity, int themeResId, IDataCallback callback, int flag) {
        super(activity, themeResId);

        this.activity = activity;
        this.callback = callback;
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的地位
        window.setWindowAnimations(R.style.mystyle); // 添加动画
        if (flag == 2) {
            visItem = 2;
        } else {
            this.flag = flag;
        }
        initProvinceDatas();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwin_address);

        setUpViews();
        setUpListener();
        setUpData();

    }

    /**
     * 设置布局控件
     */
    private void setUpViews() {
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewProvince.setWheelBackground(R.color.colorWhite);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewCity.setWheelBackground(R.color.colorWhite);
        mViewArea = (WheelView) findViewById(R.id.id_area);
        mViewArea.setWheelBackground(R.color.colorWhite);
        if (visItem == 2) {
            mViewArea.setVisibility(View.GONE);
        }
    }


    /**
     * 设置监听事件
     */
    private void setUpListener() {
        // TODO Auto-generated method stub
        // 添加change事件
        mViewProvince.addChangingListener(this);

        // 添加change事件
        mViewCity.addChangingListener(this);

        mViewArea.addChangingListener(this);
        btn_confirm.setOnClickListener(this);
    }


    /**
     * 设置数据
     */
    private void setUpData() {
        // TODO Auto-generated method stub


        String[] mProvinceDatas = new String[provinceCityBean.getProvince_list().size()];

        for (int i = 0; i < provinceCityBean.getProvince_list().size(); i++) {
            mProvinceDatas[i] = provinceCityBean.getProvince_list().get(i).getProvince_name();
        }


        arrayProviceWheelAdapter = new ArrayWheelAdapter<String>(activity,
                mProvinceDatas);
//        arrayProviceWheelAdapter.setTextSize(24);
        // arrayWheelAdapter.setTextColor(context.getResources().getColor(R.color.province_line_border));
        mViewProvince.setViewAdapter(arrayProviceWheelAdapter);
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewArea.setVisibleItems(7);
        updateCities();
    }


    private void initProvinceDatas() {
        if (flag == 0) {
            InputStream inputStream = activity.getResources().openRawResource(R.raw.regions);
            String rawTxt = SystemConfig.getRawString(inputStream);
            Gson gson = new Gson();
            provinceCityBean = gson.fromJson(rawTxt, ProvinceCityBean.class);
        } else {
            InputStream inputStream = activity.getResources().openRawResource(R.raw.district);
            String rawTxt = SystemConfig.getRawString(inputStream);
            Gson gson = new Gson();
            ProvinceCityBean2 provinceCityBean2 = gson.fromJson(rawTxt, ProvinceCityBean2.class);
            provinceCityBean = new ProvinceCityBean();
            provinceCityBean.setCountry_name(provinceCityBean2.getCountry_name());
            provinceCityBean.setRegion_id(provinceCityBean2.getDistrict_id());
            List<ProvinceCityBean.ProvinceListEntity> cityList = new LinkedList<>();
            provinceCityBean.setProvince_list(cityList);
            for (ProvinceCityBean2.ProvinceListBean item : provinceCityBean2.getProvince_list()) {
                ProvinceCityBean.ProvinceListEntity newItem = new ProvinceCityBean.ProvinceListEntity();
                newItem.setRegion_id(item.getDistrict_id());
                newItem.setProvince_name(item.getProvince_name());
                List<ProvinceCityBean.ProvinceListEntity.CityListEntity> cityList2 = new LinkedList<>();
                newItem.setCity_list(cityList2);
                cityList.add(newItem);
                for (ProvinceCityBean2.ProvinceListBean.AreaListBean item2 : item.getArea_list()) {
                    ProvinceCityBean.ProvinceListEntity.CityListEntity newItem2 = new ProvinceCityBean.ProvinceListEntity.CityListEntity();
                    newItem2.setRegion_id(item2.getDistrict_id());
                    newItem2.setCity_name(item2.getArea_name());
                    List<ProvinceCityBean.ProvinceListEntity.CityListEntity.AreaListEntity> cityList3 = new LinkedList<>();
                    newItem2.setArea_list(cityList3);
                    cityList2.add(newItem2);
                    for (ProvinceCityBean2.ProvinceListBean.AreaListBean.CityListBean item3 : item2.getCity_list()) {
                        ProvinceCityBean.ProvinceListEntity.CityListEntity.AreaListEntity newItem3 = new ProvinceCityBean.ProvinceListEntity.CityListEntity.AreaListEntity();
                        newItem3.setRegion_id(item3.getDistrict_id());
                        newItem3.setArea_name(item3.getCity_name());
                        cityList3.add(newItem3);
                    }
                }
            }
        }


    }

    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();


        String[] cities = new String[provinceCityBean.getProvince_list().get(pCurrent).getCity_list().size()];

        for (int i = 0; i < provinceCityBean.getProvince_list().get(pCurrent).getCity_list().size(); i++) {
            cities[i] = provinceCityBean.getProvince_list().get(pCurrent).getCity_list().get(i).getCity_name();
        }
        arrayCityWheelAdapter = new ArrayWheelAdapter<String>(activity, cities);
//        arrayCityWheelAdapter.setTextSize(24);
        // arrayWheelAdapter2.setTextColor(context.getResources().getColor(R.color.province_line_border));
        mViewCity.setViewAdapter(arrayCityWheelAdapter);
        mViewCity.setCurrentItem(0);

        mCurrentProviceName = provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getProvince_name();
        mCurrentProviceId = String.valueOf(provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getRegion_id());
        mCurrentCityName = provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getCity_name();
        mCurrentCityId = String.valueOf(provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getRegion_id());

        updateAreas(pCurrent);
    }


    private void updateAreas(int provicePositon) {
        int pCurrent = mViewCity.getCurrentItem();

        String[] areas = new String[provinceCityBean.getProvince_list().get(provicePositon).getCity_list().get(pCurrent).getArea_list().size()];

        for (int i = 0; i < provinceCityBean.getProvince_list().get(provicePositon).getCity_list().get(pCurrent).getArea_list().size(); i++) {
            areas[i] = provinceCityBean.getProvince_list().get(provicePositon).getCity_list().get(pCurrent).getArea_list().get(i).getArea_name();
        }
        arrayAreaWheelAdapter = new ArrayWheelAdapter<String>(activity, areas);
//        arrayAreaWheelAdapter.setTextSize(36);
        // arrayWheelAdapter2.setTextColor(context.getResources().getColor(R.color.province_line_border));
        mViewArea.setViewAdapter(arrayAreaWheelAdapter);
        mViewArea.setCurrentItem(0);

        if (provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getArea_list().size() > 0) {
            mCurrentAreaName = provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getArea_list().get(mViewArea.getCurrentItem()).getArea_name();
            mCurrentAreaId = String.valueOf(provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getArea_list().get(mViewArea.getCurrentItem()).getRegion_id());
        } else {
            mCurrentAreaName = "";
            mCurrentAreaId = null;
        }
//
    }

    public void showDialog(int i, int j, int n) {
//        mViewProvince.setCurrentItem(i);
//        mViewCity.setCurrentItem(j);
//        mViewArea.setCurrentItem(n);
        this.show();
        // 设置弹窗占满横屏
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); // 设置宽度
        this.getWindow().setAttributes(lp);
    }

    public void showDialog() {
        this.show();
        // 设置弹窗占满横屏
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); // 设置宽度
        this.getWindow().setAttributes(lp);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();

            mCurrentProviceName = provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getProvince_name();
            mCurrentProviceId = String.valueOf(provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getRegion_id());
        } else if (wheel == mViewCity) {
            updateAreas(mViewProvince.getCurrentItem());
            mCurrentCityName = provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getCity_name();
            mCurrentCityId = String.valueOf(provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getRegion_id());
        } else if (wheel == mViewArea) {
            if (provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getArea_list().size() > 0) {
                mCurrentAreaName = provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getArea_list().get(mViewArea.getCurrentItem()).getArea_name();
                mCurrentAreaId = String.valueOf(provinceCityBean.getProvince_list().get(mViewProvince.getCurrentItem()).getCity_list().get(mViewCity.getCurrentItem()).getArea_list().get(mViewArea.getCurrentItem()).getRegion_id());
            } else {
                mCurrentAreaName = "";
                mCurrentAreaId = null;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                callback.callback(mCurrentProviceName + "," + mCurrentCityName
                        + "," + mCurrentAreaName + "," + mCurrentProviceId + "," + mCurrentCityId
                        + "," + mCurrentAreaId
                );
                this.dismiss();

                break;


            default:
                break;
        }
    }
}
