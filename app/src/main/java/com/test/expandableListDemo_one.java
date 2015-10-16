package com.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class expandableListDemo_one extends Activity {
    /**
     * Called when the activity is first created.
     */

    //定义两个List用来控制Group和Child中的String;

    private List<String> groupArray;//组列表
    private List<List<String>> childArray;//子列表
    private ExpandableListView expandableListView_one;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);  //设置为无标题
        setContentView(R.layout.main);
        expandableListView_one = (ExpandableListView) findViewById(R.id.expandableListView);
        groupArray = new ArrayList<String>();
        childArray = new ArrayList<List<String>>();
        //expandableListView_one.setGroupIndicator(null);
        /*-第一季-*/
        initdate();
        expandableListView_one.setAdapter(new ExpandableListViewaAdapter(expandableListDemo_one.this));
        expandableListView_one.setOnChildClickListener(listener);

        /*-第二季-*/
//        groupArray.add("移动开发");
//        List<String> arrayList = new ArrayList<String>();
//        arrayList.add("Android");
//        arrayList.add("IOS");
//        arrayList.add("Windows Phone");
//        //组循环
//        for(int index=0;index<groupArray.size();++index)
//        {
//          childArray.add(arrayList);
//        }
//        expandableListView_one.setAdapter(new ExpandableListViewaAdapter(ExpandableListViewDemo.this));

    }

    private ExpandableListView.OnChildClickListener listener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            // TODO Auto-generated method stub
            toast("点击了Group" + groupArray.get(groupPosition) + "Child" + childArray.get(groupPosition).get(childPosition));
            return false;
        }
    };

    private void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    class ExpandableListViewaAdapter extends BaseExpandableListAdapter {
        Activity activity;

        public ExpandableListViewaAdapter(Activity a) {
            activity = a;
        }

        /*-----------------Child */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return childArray.get(groupPosition).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            String string = childArray.get(groupPosition).get(childPosition);

            return getGenericView(string);
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            // TODO Auto-generated method stub
            return childArray.get(groupPosition).size();
        }

        /* ----------------------------Group */
        @Override
        public Object getGroup(int groupPosition) {
            // TODO Auto-generated method stub
            return getGroup(groupPosition);
        }

        @Override
        public int getGroupCount() {
            // TODO Auto-generated method stub
            return groupArray.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            // TODO Auto-generated method stub
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {

            String string = groupArray.get(groupPosition);
            return getGenericView(string);
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
        }

        private TextView getGenericView(String string) {
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            TextView textView = new TextView(activity);
            textView.setLayoutParams(layoutParams);

            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

            textView.setPadding(40, 0, 0, 0);
            textView.setText(string);
            return textView;
        }
    }

    private void initdate() {
        addInfo("语言", new String[]{"Oracle", "Java", "Linux", "Jquery"});
        addInfo("男人的需求", new String[]{"金钱", "事业", "权力", "女人", "房子", "车", "球"});
    }

    private void addInfo(String group, String[] child) {

        groupArray.add(group);

        List<String> childItem = new ArrayList<String>();

        for (int index = 0; index < child.length; index++) {
            childItem.add(child[index]);
        }
        childArray.add(childItem);
    }
}