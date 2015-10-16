package com.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hahong on 15-10-16.
 */
public class expandableListDemo_two extends Activity {
    /**
     * Called when the activity is first created.
     */
    private ExpandableListView expandableListView_one;
    private List<Map<String, String>> gruops;
    private List<List<Map<String, String>>> childs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        expandableListView_one = (ExpandableListView) findViewById(R.id.expandableListView);
        //创建二个一级条目标题
        Map<String, String> title_1 = new HashMap<String, String>();
        Map<String, String> title_2 = new HashMap<String, String>();

        title_1.put("group", "移动开发");
        title_2.put("group", "男人的需求");

        //创建一级条目容器
        gruops = new ArrayList<Map<String, String>>();

        gruops.add(title_1);
        gruops.add(title_2);

        //创建二级条目内容

        //内容一
        Map<String, String> content_1 = new HashMap<String, String>();
        Map<String, String> content_2 = new HashMap<String, String>();

        content_1.put("child", "ANDROID");
        content_2.put("child", "IOS");

        List<Map<String, String>> childs_1 = new ArrayList<Map<String, String>>();
        childs_1.add(content_1);
        childs_1.add(content_2);

        //内容二
        Map<String, String> content_3 = new HashMap<String, String>();
        Map<String, String> content_4 = new HashMap<String, String>();
        Map<String, String> content_5 = new HashMap<String, String>();

        content_3.put("child", "金钱");
        content_4.put("child", "权力");
        content_5.put("child", "女人");
        List<Map<String, String>> childs_2 = new ArrayList<Map<String, String>>();
        childs_2.add(content_3);
        childs_2.add(content_4);
        childs_2.add(content_5);

        //存放两个内容, 以便显示在列表中
        childs = new ArrayList<List<Map<String, String>>>();
        childs.add(childs_1);
        childs.add(childs_2);

        //创建ExpandableList的Adapter容器
/**
 * 使用SimpleExpandableListAdapter显示ExpandableListView
 * 参数1.上下文对象Context
 * 参数2.一级条目目录集合
 * 参数3.一级条目对应的布局文件 （expandablelistview_groups.xml文件
 * 参数4.fromto，就是map中的key，指定要显示的对象
 * 参数5.与参数4对应，指定要显示在groups中的id
 * 参数6.二级条目目录集合
 * 参数7.二级条目对应的布局文件
 * 参数9.与参数8对应，指定要显示在childs中的id
 */
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, gruops, R.layout.expandablelistview_groups, new String[]{"group"}, new int[]{R.id.textGroup},
                childs, R.layout.expandablelistview_child, new String[]{"child"}, new int[]{R.id.textChild}
        );

        //加入列表
        expandableListView_one.setAdapter(adapter);
        expandableListView_one.setOnChildClickListener(listener);
    }

    private ExpandableListView.OnChildClickListener listener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
// TODO Auto-generated method stub
            toast("点击了Group" + gruops.get(groupPosition) + "Child" + childs.get(groupPosition).get(childPosition));
            return false;
        }
    };

    private void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}