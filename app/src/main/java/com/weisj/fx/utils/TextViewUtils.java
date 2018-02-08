package com.weisj.fx.utils;

import android.graphics.Paint;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * ============================================================
 * <p/>
 * 版 权 ： 百变美金 版权所有 (c) 2015
 * <p/>
 * 作 者 :
 * <p/>
 * 版 本 ： 3.7
 * <p/>
 * 创建日期 ： 2015-8-15 上午11:42:24
 * <p/>
 * 描 述 ：textview.settext 判空或null
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 **/
public class TextViewUtils {
    public static void setText(TextView tv, String content) {
        try {
            if (content != null && !content.trim().equals("null")) {
                tv.setText(content);
            } else {
                tv.setText("");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static float computeMaxStringWidth(String strings, Paint p) {
        return p.measureText(strings);
    }

    public static void setTextAndScale(TextView tv, String content, int textWidth) {
        try {
            if (content != null && !content.trim().equals("null")) {
                TextPaint paint = tv.getPaint();
                float size = computeMaxStringWidth(content, paint);
                while (size > textWidth - 10) {
                    float textSize = paint.getTextSize();
                    paint.setTextSize(textSize - 1);
                    size = computeMaxStringWidth(content, paint);
                }
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, paint.getTextSize());
                tv.setText(content);
            } else {
                tv.setText("");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public static String sprStringMoney(String str) {
        try {
            double money = Double.valueOf(str);
            money = money * 100;
            if (money % 100 != 0) {
                if (str.contains(".")) {
                    int index = str.indexOf(".");
                    if (str.length() > index + 3) {
                        return str.substring(0, index + 3);
                    }
                }
                return str;
            }else{
                return sprStringMoneyToInt(str);
            }
        } catch (NumberFormatException e) {
            return str;
        }
    }

    /**
     *
     */
    public static String sprStringMoney(String str, int count) {
        if (str.contains(".")) {
            int index = str.indexOf(".");
            if (str.length() > index + count + 1) {
                return str.substring(0, index + count + 1);
            }
        }
        return str;
    }

    /**
     *
     */
    public static String sprStringMoneyToInt(String str) {
        if (str.contains(".")) {
            int index = str.indexOf(".");
            if (str.length() > index) {
                return str.substring(0, index);
            }
        }
        return str;
    }

    public static void setText(TextView tv, String content, String defaultStr) {
        try {
            if (content != null && !content.trim().equals("null")) {
                tv.setText(content);
            } else {
                tv.setText(defaultStr);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setText(EditText tv, String content) {
        try {
            if (content != null && !content.trim().equals("null")) {
                tv.setText(content);
            } else {
                tv.setText("");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setTextAndleftOther(TextView tv, String content,
                                           String leftStr) {
        try {
            if (content != null && !content.trim().equals("null")) {
                tv.setText(leftStr + content);
            } else {
                tv.setText(leftStr);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setTextAndrightOther(TextView tv, String content,
                                            String rightStr) {
        try {
            if (content != null && !content.trim().equals("null")) {
                tv.setText(content + rightStr);
            } else {
                tv.setText(rightStr);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setTextAndallOther(TextView tv, String content,
                                          String leftStr, String rightStr) {
        try {
            if (content != null && !content.trim().equals("null")) {
                tv.setText(leftStr + content + rightStr);
            } else {
                tv.setText("");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setTextAndallOtherIsZero(TextView tv, String content,
                                                String leftStr, String rightStr) {
        try {
            if (content != null && !content.trim().equals("null")) {
                tv.setText(leftStr + content + rightStr);
            } else {
                tv.setText(leftStr + "0" + rightStr);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 已750为比例
     *
     * @param tv
     * @param size
     */
    public static void setTextSize(TextView tv, int size) {
        double ratio = SystemConfig.getScreenWidth() / 750.0;
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (size * ratio));
    }

    /**
     * 已750为比例
     *
     * @param tv
     * @param size
     */
    public static void setTextSize(EditText tv, int size) {
        double ratio = SystemConfig.getScreenWidth() / 750.0;
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (size * ratio));
    }
}
