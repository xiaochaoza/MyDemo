package com.fzzz.mydemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-30
 * update:
 */
public class AddressInfoBean implements Serializable {

    public List<Province> provinces;

    public static class Province {
        /**
         * label : 11
         * value : 北京市
         * children : [{"label":"1101","value":"市辖区","children":[{"label":"110101","value":"东城区"},{"label":"110102","value":"西城区"},{"label":"110105","value":"朝阳区"},{"label":"110106","value":"丰台区"},{"label":"110107","value":"石景山区"},{"label":"110108","value":"海淀区"},{"label":"110109","value":"门头沟区"},{"label":"110111","value":"房山区"},{"label":"110112","value":"通州区"},{"label":"110113","value":"顺义区"},{"label":"110114","value":"昌平区"},{"label":"110115","value":"大兴区"},{"label":"110116","value":"怀柔区"},{"label":"110117","value":"平谷区"},{"label":"110118","value":"密云区"},{"label":"110119","value":"延庆区"}]}]
         */
        public String label;
        public String name;
        public List<City> cities;

        public static class City {
            /**
             * label : 1101
             * value : 市辖区
             * children : [{"label":"110101","value":"东城区"},{"label":"110102","value":"西城区"},{"label":"110105","value":"朝阳区"},{"label":"110106","value":"丰台区"},{"label":"110107","value":"石景山区"},{"label":"110108","value":"海淀区"},{"label":"110109","value":"门头沟区"},{"label":"110111","value":"房山区"},{"label":"110112","value":"通州区"},{"label":"110113","value":"顺义区"},{"label":"110114","value":"昌平区"},{"label":"110115","value":"大兴区"},{"label":"110116","value":"怀柔区"},{"label":"110117","value":"平谷区"},{"label":"110118","value":"密云区"},{"label":"110119","value":"延庆区"}]
             */
            public String label;
            public String name;
            public List<County> counties;

            public static class County {
                /**
                 * label : 110101
                 * value : 东城区
                 */
                public String label;
                public String name;

            }
        }
    }
}
