package com.coolstar.extendlayoutlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by jiguangxing on 2016/5/6.
 */
public class CommonDemoAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private int[] itemPicIds ={R.mipmap.i1,R.mipmap.i2,R.mipmap.i3,R.mipmap.i4,
                                R.mipmap.i5,R.mipmap.i6,R.mipmap.i7,R.mipmap.i8
    };

    public CommonDemoAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemPicIds.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.item_adapter,null);
            viewHolder = new ViewHolder();
            viewHolder.initView(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.updateData(position);
        return convertView;
    }


    private class ViewHolder{
        private ImageView iPic;

        private void initView(View view){
            iPic= (ImageView) view.findViewById(R.id.item_pic);
        }

        private void updateData(int position){
            iPic.setImageResource(itemPicIds[position]);
        }
    }
}
