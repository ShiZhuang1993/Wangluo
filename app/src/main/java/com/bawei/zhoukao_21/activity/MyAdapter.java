package com.bawei.zhoukao_21.activity;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bawei.zhoukao_21.bean.WeatherBean;

import java.util.ArrayList;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/9 21:13
 */

public class MyAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<WeatherBean.ResultBean> arrayList;

    public MyAdapter(Context context, ArrayList<WeatherBean.ResultBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getGroupCount() {
        return arrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayList.get(groupPosition).getChild_list().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayList.get(groupPosition).getChild_list().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String city = arrayList.get(groupPosition).getCity();
        View view = get_group_TextView(city);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String s = arrayList.get(groupPosition).getChild_list().get(childPosition).getCity();
        View view = get_child_TextView(s);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private View get_group_TextView(String text) {
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(5, 5, 5, 5);
        tv.setTextSize(25);
        tv.setText(text);
        return tv;
    }

    private View get_child_TextView(String text) {
        TextView tv = new TextView(context);
        tv.setPadding(5, 5, 5, 5);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20);
        tv.setText(text);
        return tv;
    }
}
