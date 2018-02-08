package com.weisj.fx.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by BBMJ on 2016/8/3.
 */
public class LocationBean {


    /**
     * code : 1
     * msg : 操作成功!
     * data : {"hotDistrictList":[{"districtId":8,"districtName":"安徽","districtType":1,"topDistrict":4,"parentDistrict":4,"letter":null},{"districtId":9,"districtName":"福建","districtType":1,"topDistrict":4,"parentDistrict":4,"letter":null},{"districtId":10,"districtName":"甘肃","districtType":1,"topDistrict":5,"parentDistrict":5,"letter":null}],"allDistrictList":[{"type":"A","typeCityList":["安阳","安康","阿拉尔","阿坝","安庆","安顺","阿克苏","阿拉善盟","鞍山","阿里"]},{"type":"B","typeCityList":["白城","包头","巴中","蚌埠","保亭","保定","毕节","北海","巴彦淖尔盟","北京","本溪","白沙","滨州","百色","博尔塔拉","保山","亳州","白山","宝鸡","澳门","白银","巴音郭楞"]},{"type":"C","typeCityList":["崇左","长沙","郴州","朝阳","滁州","潮州","昌吉","楚雄","常德","池州","昌都","澄迈县","承德","长治","长春","赤峰","巢湖","昌江","沧州","常州","成都"]},{"type":"D","typeCityList":["大连","大理","大庆","定安县","德州","大同","德阳","迪庆","定西","达州","德宏","儋州","大兴安岭","丹东","东莞","东方","东营"]},{"type":"E","typeCityList":["恩施","鄂尔多斯","鄂州"]},{"type":"F","typeCityList":["福州","阜新","抚州","抚顺","佛山","防城港","阜阳"]},{"type":"G","typeCityList":["甘孜","赣州","甘南","果洛","贵港","广元","广安","广州","贵阳","固原","桂林"]},{"type":"H","typeCityList":["海北","哈密","河池","海口","黄石","呼伦贝尔","海西","红河","黑河","淮南","邯郸","合肥","河源","哈尔滨","黄冈","海南","鹤岗","怀化","汉中","淮北","淮安","菏泽","湖州","海东","和田","贺州","鹤壁","衡阳","黄南","葫芦岛","黄山","衡水","杭州","惠州","呼和浩特"]},{"type":"J","typeCityList":["焦作","济南","九江","济宁","嘉兴","酒泉","揭阳","吉林","济源","荆州","佳木斯","景德镇","锦州","晋中","金昌","江门","荆门","鸡西","吉安","晋城","嘉峪关","金华"]},{"type":"K","typeCityList":["克拉玛依","开封","喀什","克孜勒苏","昆明"]},{"type":"L","typeCityList":["临夏","漯河","拉萨","柳州","娄底","乐山","林芝","辽阳","兰州","临高县","连云港","临汾","临沂","辽源","来宾","六安","乐东","廊坊","聊城","丽水","陇南","洛阳","泸州","龙岩","临沧","凉山","六盘水","陵水","莱芜","吕梁","丽江"]},{"type":"M","typeCityList":["茂名","牡丹江","马鞍山","眉山","绵阳","梅州"]},{"type":"N","typeCityList":["宁德","南充","南宁","宁波","南平","怒江","南昌","南阳","南京","那曲","内江","南通"]},{"type":"P","typeCityList":["普洱","平凉","平顶山","攀枝花","濮阳","莆田","萍乡","盘锦"]},{"type":"Q","typeCityList":["黔西南","庆阳","潜江","秦皇岛","黔南","琼中","清远","曲靖","泉州","齐齐哈尔","黔东南","琼海","衢州","钦州","青岛","七台河"]},{"type":"R","typeCityList":["日照","日喀则"]},{"type":"S","typeCityList":["绍兴","汕头","四平","商洛","三明","三门峡","随州","山南","双鸭山","上海","遂宁","沈阳","韶关","石家庄","石河子","深圳","十堰","苏州","上饶","石嘴山","宿迁","朔州","汕尾","三亚","松原","神农架林区","邵阳","商丘","宿州","绥化"]},{"type":"T","typeCityList":["屯昌县","图木舒克","泰州","天津","通化","台湾","铁岭","铜陵","铜仁","泰安","台州","天水","通辽","太原","铜川","吐鲁番","天门","唐山"]},{"type":"W","typeCityList":["五指山","潍坊","乌兰察布市","文山","无锡","吴忠","芜湖","武汉","文昌","威海","温州","武威","乌海","梧州","渭南","五家渠","万宁","乌鲁木齐"]},{"type":"X","typeCityList":["仙桃","徐州","香港","湘西","咸阳","信阳","襄樊","新余","忻州","西安","湘潭","兴安盟","新乡","咸宁","邢台","西宁","锡林郭勒盟","西双版纳","厦门","许昌","孝感","宣城"]},{"type":"Y","typeCityList":["阳泉","阳江","延边","玉林","银川","伊犁","岳阳","营口","扬州","玉树","玉溪","永州","榆林","伊春","宜昌","鹰潭","宜宾","盐城","运城","烟台","云浮","益阳","延安","宜春","雅安"]},{"type":"Z","typeCityList":["郑州","自贡","镇江","舟山","张掖","淄博","肇庆","昭通","张家口","驻马店","资阳","遵义","枣庄","重庆","湛江","漳州","珠海","周口","张家界","中山","株洲","中卫"]}]}
     */

    private String code;
    private String msg;
    private DataEntity data;

    public static LocationBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), LocationBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * districtId : 8
         * districtName : 安徽
         * districtType : 1
         * topDistrict : 4
         * parentDistrict : 4
         * letter : null
         */

        private List<HotDistrictListEntity> hotDistrictList;
        /**
         * type : A
         * typeCityList : ["安阳","安康","阿拉尔","阿坝","安庆","安顺","阿克苏","阿拉善盟","鞍山","阿里"]
         */

        private List<AllDistrictListEntity> allDistrictList;

        public static DataEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public void setHotDistrictList(List<HotDistrictListEntity> hotDistrictList) {
            this.hotDistrictList = hotDistrictList;
        }

        public void setAllDistrictList(List<AllDistrictListEntity> allDistrictList) {
            this.allDistrictList = allDistrictList;
        }

        public List<HotDistrictListEntity> getHotDistrictList() {
            return hotDistrictList;
        }

        public List<AllDistrictListEntity> getAllDistrictList() {
            return allDistrictList;
        }

        public static class HotDistrictListEntity {
            private int districtId;
            private String districtName;
            private int districtType;
            private int topDistrict;
            private int parentDistrict;
            private Object letter;

            public static HotDistrictListEntity objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), HotDistrictListEntity.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public void setDistrictId(int districtId) {
                this.districtId = districtId;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public void setDistrictType(int districtType) {
                this.districtType = districtType;
            }

            public void setTopDistrict(int topDistrict) {
                this.topDistrict = topDistrict;
            }

            public void setParentDistrict(int parentDistrict) {
                this.parentDistrict = parentDistrict;
            }

            public void setLetter(Object letter) {
                this.letter = letter;
            }

            public int getDistrictId() {
                return districtId;
            }

            public String getDistrictName() {
                return districtName;
            }

            public int getDistrictType() {
                return districtType;
            }

            public int getTopDistrict() {
                return topDistrict;
            }

            public int getParentDistrict() {
                return parentDistrict;
            }

            public Object getLetter() {
                return letter;
            }
        }

        public static class AllDistrictListEntity {
            private String type;
            private List<String> typeCityList;

            public static AllDistrictListEntity objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), AllDistrictListEntity.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setTypeCityList(List<String> typeCityList) {
                this.typeCityList = typeCityList;
            }

            public String getType() {
                return type;
            }

            public List<String> getTypeCityList() {
                return typeCityList;
            }
        }
    }
}
