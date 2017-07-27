package com.example.myretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myretrofit.R;
import com.example.myretrofit.bean.Student;

import java.util.List;

/**
 * Created on 2017/7/27.
 * author ${yao}.
 */

public class Adapter extends BaseAdapter {
    private List<Student> list;
    private Context context;
    private LayoutInflater inflater;
    public Adapter(List<Student> list,Context context){
        this.list= list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.mian_listitem, null);
            holder = new ViewHolder();
            holder.list_tv_id = (TextView) convertView.findViewById(R.id.list_tv_id);
            holder.list_tv_content = (TextView) convertView.findViewById(R.id.list_tv_content);
            holder.list_tv_sender = (TextView) convertView.findViewById(R.id.list_tv_sender);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Student student = list.get(i);
        holder.list_tv_id.setText(""+i);
        holder.list_tv_content.setText(""+student.getName());
        holder.list_tv_sender.setText(""+student.getSex());
        return convertView;
    }
    class ViewHolder{
        TextView list_tv_content;
        TextView  list_tv_id;
        TextView  list_tv_sender;

    }
    public void changeCursor(List<Student> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
