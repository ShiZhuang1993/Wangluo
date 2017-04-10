package com.bawei.zhoukao_21.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.bawei.zhoukao_21.R;
import com.bawei.zhoukao_21.bean.WeatherBean;
import com.bawei.zhoukao_21.url.Url;
import com.bawei.zhoukao_21.utils.MyAsyncTask;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private ArrayList<WeatherBean.ResultBean> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initView() {
        mExpandableListView = (ExpandableListView) findViewById(R.id.main_explistview);
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                expand_group(groupPosition);
                return true;
            }
        });
    }

    private void initData() {
        MyAsyncTask asyncTask = new MyAsyncTask() {

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(s, WeatherBean.class);
                //解析完 获得相应的集合
                List<WeatherBean.ResultBean> resultList = weatherBean.getResult();
                //获得处理后 显示的集合
                mArrayList = dispose_list(resultList);
                MyAdapter myAdapter = new MyAdapter(MainActivity.this, mArrayList);
                mExpandableListView.setAdapter(myAdapter);

            }
        };
        asyncTask.execute(Url.url);
    }

    //处理 解析出来的集合
    private ArrayList<WeatherBean.ResultBean> dispose_list(List<WeatherBean.ResultBean> list) {

        //new 一个最后需要的集合
        ArrayList<WeatherBean.ResultBean> f_list = new ArrayList<WeatherBean.ResultBean>();

        //先添加省会城市 如北京 天津 辽宁等
        //集合中的数据是有序的 先是省会 然后是城市
        for (int i = 0; i < list.size(); ) {
            WeatherBean.ResultBean resultBean = list.get(i);
            int id = Integer.parseInt(resultBean.getParentid());
            //id为0的是省会城市
            if (id == 0) {
                resultBean.setChild_list(new ArrayList<WeatherBean.ResultBean>());
                f_list.add(resultBean);
                //加完后就删掉 因为数据比较多
                list.remove(i);
            } else if (id != 0) {
                //不是0 省会添加完毕 直接跳出
                break;
            } else {
                i++;
            }
        }

        //为省会添加 相应的城市
        for (int i = 0; i < f_list.size(); i++) {
            WeatherBean.ResultBean bean = f_list.get(i);
            int id = Integer.parseInt(bean.getCityid());

            //添加省会city的城市
            for (int k = 0; k < list.size(); ) {
                WeatherBean.ResultBean city_child = list.get(k);
                int child_id = Integer.parseInt(city_child.getParentid());

                if (child_id == id) {
                    bean.getChild_list().add(city_child);
                    list.remove(k);
                } else {
                    k++;
                }
            }
        }

        return f_list;
    }

    //设置 点开一个 关闭其它
    private void expand_group(int groupPosition) {
        for (int i = 0; i < mArrayList.size(); i++) {
            if (i == groupPosition) {
                //当前条目是否展开  展开则关闭  关闭则展开
                if (mExpandableListView.isGroupExpanded(groupPosition)) {
                    mExpandableListView.collapseGroup(i);
                    return;
                } else {
                    mExpandableListView.expandGroup(i);
                }
            } else {
                mExpandableListView.collapseGroup(i);
            }
        }
    }
}
