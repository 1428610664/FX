package com.weisj.fx.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemGoodDetailRecommendAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.GoodDetail;
import com.weisj.fx.bean.GoodDetailImageBean;
import com.weisj.fx.bean.ShareData;
import com.weisj.fx.presenter.GoodDetailPresenter;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.view.MyRatingBar;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.view.bgabanner.BGABanner;
import com.weisj.fx.view.dialog.ShareViewDialog;
import com.weisj.fx.viewinterface.IGoodDetailView;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class GoodDetailActivity extends BaseActivity implements IGoodDetailView, AbPullToRefreshView.OnHeaderRefreshListener, View.OnClickListener {
    private BGABanner bgaBanner;
    private LinearLayout goodQuality, goodManufacturers;
    private TextView goodSendTime, goodname, goodDesc, goodSendCity, goodSendMoney, goodPrice, goodDelPrice, goodCommission, goodPoint, goodPerson;
    private RecyclerView recyclerView;
    private GoodDetailPresenter presenter;
    private AbPullToRefreshView refreshView;
    private GoodDetail goodDetail;
    private MyRatingBar goodBar;
    private int state = 0;
    private com.weisj.fx.view.AlertDialog alertDialog;
    private View good_del_linear;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_good_detail, null);
        state = getIntent().getIntExtra("good_state", 0);
        initView(view);
        presenter = new GoodDetailPresenter(this, this);
        presenter.getData(true);
        return view;
    }

    private void initView(View view) {
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        view.findViewById(R.id.good_share).setOnClickListener(this);
        view.findViewById(R.id.good_image_linear).setOnClickListener(this);
        view.findViewById(R.id.kefu).setOnClickListener(this);
        view.findViewById(R.id.good_more).setOnClickListener(this);
        view.findViewById(R.id.good_point).setOnClickListener(this);
        good_del_linear = view.findViewById(R.id.good_del_linear);
        good_del_linear.setVisibility(View.GONE);
        refreshView.setLoadMoreEnable(false);
        refreshView.setOnHeaderRefreshListener(this);
        bgaBanner = (BGABanner) view.findViewById(R.id.bagbanner_container);
        bgaBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ImageLoaderUtils.getInstance().display((ImageView) view, (String) model);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        goodQuality = (LinearLayout) view.findViewById(R.id.good_quality);
        goodManufacturers = (LinearLayout) view.findViewById(R.id.good_manufacturers);
        goodSendTime = (TextView) view.findViewById(R.id.good_send_time);
        goodname = (TextView) view.findViewById(R.id.good_name);
        goodDesc = (TextView) view.findViewById(R.id.good_desc);
        goodSendCity = (TextView) view.findViewById(R.id.good_send_city);
        goodSendMoney = (TextView) view.findViewById(R.id.good_send_money);
        goodPrice = (TextView) view.findViewById(R.id.good_price);
        goodDelPrice = (TextView) view.findViewById(R.id.good_del_price);
        goodCommission = (TextView) view.findViewById(R.id.good_commission);
        goodPerson = (TextView) view.findViewById(R.id.good_detail_person);
        goodPoint = (TextView) view.findViewById(R.id.good_detail_point);
        view.findViewById(R.id.question_image).setOnClickListener(this);
        goodBar = (MyRatingBar) view.findViewById(R.id.good_detail_bar);
        goodPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (state == 0) {
            view.findViewById(R.id.good_state_1).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.good_state_2).setVisibility(View.VISIBLE);
            view.findViewById(R.id.click_desc).setOnClickListener(this);
        }

    }

    @Override
    public String setTitleStr() {
        return "详情";
    }

    @Override
    public void getRefreshData() {
        presenter.getData(true);
    }

    @Override
    public int getGoodId() {
        return getIntent().getIntExtra("goodId", 1);
    }

    @Override
    public void getData(GoodDetail goodDetail) {
        this.goodDetail = goodDetail;
        if (goodDetail.getData().getImages() != null && goodDetail.getData().getImages().size() > 0) {
            bgaBanner.setData(goodDetail.getData().getImages(), null);
        }
        if (goodDetail.getData().getDirectSupply() > 0) {
            goodManufacturers.setVisibility(View.VISIBLE);
        } else {
            goodManufacturers.setVisibility(View.GONE);
        }
        if (goodDetail.getData().getIsCertified() > 0) {
            goodQuality.setVisibility(View.VISIBLE);
        } else {
            goodQuality.setVisibility(View.GONE);
        }
        TextViewUtils.setText(goodname, goodDetail.getData().getGoodsName());
        TextViewUtils.setText(goodDesc, goodDetail.getData().getDescribe());
        if (goodDetail.getData().getDelMoney().equals("0") || goodDetail.getData().getDelMoney().equals("0.0") || goodDetail.getData().getDelMoney().equals("0.00")) {
            good_del_linear.setVisibility(View.GONE);
        } else {
            good_del_linear.setVisibility(View.VISIBLE);
        }
        TextViewUtils.setText(goodDelPrice, goodDetail.getData().getDelMoney());
//        TextViewUtils.setTextAndrightOther(goodSendCity, goodDetail.getData().getSendCity(), "发货");
        TextViewUtils.setTextAndleftOther(goodPrice, goodDetail.getData().getPrice(), "原价:￥");
        TextViewUtils.setTextAndrightOther(goodSendTime, goodDetail.getData().getSendHour(), "小时内发货");
        TextViewUtils.setTextAndrightOther(goodPoint, goodDetail.getData().getGoodsPoint(), "分");
        TextViewUtils.setTextAndrightOther(goodPerson, String.valueOf(goodDetail.getData().getCommentNum()), "人评价");
        TextViewUtils.setTextAndallOtherIsZero(goodCommission, SystemConfig.moneymulti(goodDetail.getData().getCommission()), "银票", "");
        try {
            goodBar.setPoint(Integer.valueOf(TextViewUtils.sprStringMoneyToInt(goodDetail.getData().getGoodsPoint())));
        } catch (Exception e) {
            e.printStackTrace();
            goodBar.setPoint(5);
        }
        if (goodDetail.getData().getFreight() != null && goodDetail.getData().getFreight().equals("0")) {
            goodSendMoney.setText("包邮");
        } else {
            TextViewUtils.setTextAndallOtherIsZero(goodSendMoney, goodDetail.getData().getFreight(), "运费", "元");
        }
        if (goodDetail.getData().getGoodsList() != null && goodDetail.getData().getGoodsList().size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new ItemGoodDetailRecommendAdapter(this, goodDetail.getData().getGoodsList()));
        }
        refreshView.onHeaderRefreshFinish();
    }

    private void showAlert(String str) {
        if (alertDialog == null) {
            alertDialog = new com.weisj.fx.view.AlertDialog(this);
            alertDialog.setTitle("说明");
            alertDialog.setMessage(str);

            alertDialog.setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    }
            );
            alertDialog.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.setCancelVisible(true);
        }
        alertDialog.show();

    }

    @Override
    public void getImageData(GoodDetailImageBean goodDetailImageBean) {

    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public int getActivityId() {
        return getIntent().getIntExtra("activity_id", 0);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.good_share:
                if (goodDetail != null) {
                    if ((state > 0 && goodDetail.getData().getSharePic() != null) || (state == 0 && goodDetail.getData().getImages() != null && goodDetail.getData().getImages().size() > 0)) {
                        ImageLoaderUtils.getInstance().display(state == 0 ? goodDetail.getData().getImages().get(0) : goodDetail.getData().getSharePic(), new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                ShareData shareData = new ShareData(loadedImage, goodDetail.getData().getImages(), goodDetail.getData().getShareDes(), state == 0 ? goodDetail.getData().getShareTitle() : goodDetail.getData().getActivityName(), goodDetail.getData().getWebsite(), getGoodId(), 0);
                                new ShareViewDialog(GoodDetailActivity.this, shareData).show();
                            }

                            @Override
                            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                                ShareData shareData = new ShareData(BitmapFactory.decodeResource(GoodDetailActivity.this.getResources(), R.mipmap.icon_share_sf), goodDetail.getData().getShareDes(), state == 0 ? goodDetail.getData().getShareTitle() : goodDetail.getData().getActivityName(), goodDetail.getData().getWebsite(), getGoodId(), 0);
                                new ShareViewDialog(GoodDetailActivity.this, shareData).show();
                            }
                        });
                    } else {
                        ShareData shareData = new ShareData(BitmapFactory.decodeResource(GoodDetailActivity.this.getResources(), R.mipmap.icon_share_sf), goodDetail.getData().getShareDes(), state == 0 ? goodDetail.getData().getShareTitle() : goodDetail.getData().getActivityName(), goodDetail.getData().getWebsite(), getGoodId(), 0);
                        new ShareViewDialog(GoodDetailActivity.this, shareData).show();
                    }
                }
                break;
            case R.id.good_image_linear:
                Intent intent = new Intent(this, GoodImageDetailActivity.class);
                intent.putExtra("goodId", getGoodId());
                startActivity(intent);
                break;

            case R.id.kefu:

                if (goodDetail != null && goodDetail.getData().getBrandCellphone() != null) {
                    AlertDialog ad = new android.app.AlertDialog.Builder(this).create();
                    ad.setCanceledOnTouchOutside(true);
                    ad.setCancelable(true);
                    ad.show();
                    //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
                    Window window = ad.getWindow();
                    ad.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                    window.setContentView(R.layout.popwin_kefu);

                    TextView messageView = (TextView) window.findViewById(R.id.message);
                    messageView.setText(goodDetail.getData().getBrandCellphone());
                    messageView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + goodDetail.getData().getBrandCellphone());
                            intent.setData(data);
                            startActivity(intent);

                        }
                    });
                } else {
                    Toast.makeText(this, "正在加载数据中...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.good_more:
                intent = new Intent(this, SearchListActivity.class);
                startActivity(intent);
                break;

            case R.id.good_point:
                intent = new Intent(this, GoodPointActivity.class);
                intent.putExtra("good_id", getGoodId());
                startActivity(intent);
                break;

            case R.id.click_desc:
                if (goodDetail != null && goodDetail.getData().getActivityRule() != null) {
                    showAlert(goodDetail.getData().getActivityRule());
                } else {
                    Toast.makeText(this, "数据加载中···", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.question_image:
                intent = new Intent(this, QuestionActivity.class);
                startActivity(intent);
                break;
        }
    }
}
