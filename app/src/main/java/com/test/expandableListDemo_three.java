package com.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hahong on 15-10-16.
 */
public class expandableListDemo_three extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            //设置组视图的图片
            int[] logos = new int[] { R.drawable.icon, R.drawable.icon,R.drawable.icon};
            //设置组视图的显示文字
            private String[] generalsTypes = new String[] { "魏", "蜀", "吴" };
            //子视图显示文字
            private String[][] generals = new String[][] {
                    { "夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修" },
                    { "马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云" },
                    { "吕蒙", "陆逊", "孙权", "周瑜", "孙尚香" }

            };
            //子视图图片
            public int[][] generallogos = new int[][] {
                    { R.drawable.icon, R.drawable.icon,
                            R.drawable.icon, R.drawable.icon,
                            R.drawable.icon, R.drawable.icon },
                    { R.drawable.icon, R.drawable.icon,
                            R.drawable.icon, R.drawable.icon,
                            R.drawable.icon, R.drawable.icon },
                    { R.drawable.icon, R.drawable.icon, R.drawable.icon,
                            R.drawable.icon, R.drawable.icon } };

            //自己定义一个获得文字信息的方法
            TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, 64);
                TextView textView = new TextView(
                        expandableListDemo_three.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                textView.setTextColor(Color.WHITE);
                return textView;
            }


            //重写ExpandableListAdapter中的各个方法
            @Override
            public int getGroupCount() {
                // TODO Auto-generated method stub
                return generalsTypes.length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                // TODO Auto-generated method stub
                return generalsTypes[groupPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                // TODO Auto-generated method stub
                return groupPosition;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                // TODO Auto-generated method stub
                return generals[groupPosition].length;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                // TODO Auto-generated method stub
                return generals[groupPosition][childPosition];
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                // TODO Auto-generated method stub
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded,
                                     View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                LinearLayout ll = new LinearLayout(
                        expandableListDemo_three.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(expandableListDemo_three.this);
                logo.setImageResource(logos[groupPosition]);
                logo.setPadding(50, 0, 0, 0);
                ll.addView(logo);
                TextView textView = getTextView();
                textView.setTextColor(Color.WHITE);
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);

                return ll;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition,
                                     boolean isLastChild, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                LinearLayout ll = new LinearLayout(
                        expandableListDemo_three.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView generallogo = new ImageView(
                        expandableListDemo_three.this);
                generallogo
                        .setImageResource(generallogos[groupPosition][childPosition]);
                ll.addView(generallogo);
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition)
                        .toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public boolean isChildSelectable(int groupPosition,
                                             int childPosition) {
                // TODO Auto-generated method stub
                return true;
            }

        };

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(adapter);


        //设置item点击的监听器
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(
                        expandableListDemo_three.this,
                        "你点击了Group" + adapter.getGroup(groupPosition) + "Child" + adapter.getChild(groupPosition, childPosition),
                        Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }


}