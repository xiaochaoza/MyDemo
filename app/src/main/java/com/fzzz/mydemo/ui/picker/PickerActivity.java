package com.fzzz.mydemo.ui.picker;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.JsonUtil;
import com.fzzz.framework.utils.TimeUtil;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.bean.AddressInfoBean;
import com.fzzz.mydemo.utils.ToastUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-30
 * update:
 */
@Route(path = Constants.PATH_APP_PICKER)
public class PickerActivity extends BaseActivity {

    private List<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_picker;
    }

    @OnClick({R.id.bt_pick1, R.id.bt_pick2, R.id.bt_pick3, R.id.bt_pick4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_pick1://时间选择器
                TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        ToastUtil.show(TimeUtil.getLongTime(date));
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.bt_pick2://省市区三级联动菜单
                initJson();

                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String opt1tx = options1Items.size() > 0 ?
                                options1Items.get(options1) : "";

                        String opt2tx = options2Items.size() > 0
                                && options2Items.get(options1).size() > 0 ?
                                options2Items.get(options1).get(options2) : "";

                        String opt3tx = options2Items.size() > 0
                                && options3Items.get(options1).size() > 0
                                && options3Items.get(options1).get(options2).size() > 0 ?
                                options3Items.get(options1).get(options2).get(options3) : "";
                        ToastUtil.show(opt1tx + opt2tx + opt3tx);
                    }
                }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {

                    }
                }).build();

                pvOptions.setPicker(options1Items, options2Items, options3Items);
                pvOptions.show();
                break;
            case R.id.bt_pick3:
                break;
            case R.id.bt_pick4:
                break;
        }
    }

    private void initJson() {
        String address = JsonUtil.getStringFromFile(this, "address.json");
        AddressInfoBean addressInfoBean = new Gson().fromJson(address, AddressInfoBean.class);

        List<AddressInfoBean.Province> provinces = addressInfoBean.provinces;
        for (int i = 0; i < provinces.size(); i++) {
            //添加省份
            options1Items.add(provinces.get(i).name);
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_countyList = new ArrayList<>();//该省的所有地区列表（第三极）

            Logger.d(provinces.get(i).name);
            List<AddressInfoBean.Province.City> cities = provinces.get(i).cities;
            for (int j = 0; j < cities.size(); j++) {
                //添加城市
                cityList.add(cities.get(j).name);
                ArrayList<String> city_countyList = new ArrayList<>();//该城市的所有地区列表

                List<AddressInfoBean.Province.City.County> counties = provinces.get(i).cities.get(j).counties;
                List<String> countyList = new ArrayList<>();
                for (int c = 0; c < counties.size(); c++) {
                    //添加地区
                    countyList.add(counties.get(c).name);
                }
                city_countyList.addAll(countyList);
                province_countyList.add(city_countyList);
            }
            //添加城市
            options2Items.add(cityList);
            //添加地区
            options3Items.add(province_countyList);
        }
    }
}
