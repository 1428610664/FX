package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-06-07.
 */

public class ProvinceCityBean2 {
    private int district_id;
    private String country_name;
    private List<ProvinceListBean> province_list;

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public List<ProvinceListBean> getProvince_list() {
        return province_list;
    }

    public void setProvince_list(List<ProvinceListBean> province_list) {
        this.province_list = province_list;
    }

    public static class ProvinceListBean {
        /**
         * district_id : 476
         * province_name : 东莞馆
         * area_list : [{"district_id":449,"area_name":"东莞区","city_list":[{"district_id":84,"city_name":"东莞"}]}]
         */

        private int district_id;
        private String province_name;
        private List<AreaListBean> area_list;

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public List<AreaListBean> getArea_list() {
            return area_list;
        }

        public void setArea_list(List<AreaListBean> area_list) {
            this.area_list = area_list;
        }

        public static class AreaListBean {
            /**
             * district_id : 449
             * area_name : 东莞区
             * city_list : [{"district_id":84,"city_name":"东莞"}]
             */

            private int district_id;
            private String area_name;
            private List<CityListBean> city_list;

            public int getDistrict_id() {
                return district_id;
            }

            public void setDistrict_id(int district_id) {
                this.district_id = district_id;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public List<CityListBean> getCity_list() {
                return city_list;
            }

            public void setCity_list(List<CityListBean> city_list) {
                this.city_list = city_list;
            }

            public static class CityListBean {
                /**
                 * district_id : 84
                 * city_name : 东莞
                 */

                private int district_id;
                private String city_name;

                public int getDistrict_id() {
                    return district_id;
                }

                public void setDistrict_id(int district_id) {
                    this.district_id = district_id;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }
            }
        }
    }
}
