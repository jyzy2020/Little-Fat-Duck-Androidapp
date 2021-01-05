package com.example.littlefatduckg1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> faqList;
    private HashMap<String, List<String>> faqDes;

    public ExpandableListViewAdapter(Context context, List<String> faqList, HashMap<String, List<String>> faqDes) {
        this.context = context;
        this.faqList = faqList;
        this.faqDes = faqDes;
    }

    @Override
    public int getGroupCount() {
        return this.faqList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.faqDes.get(this.faqList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.faqList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.faqDes.get(this.faqList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String faqListTitle=(String)getGroup(i);
        if (view==null){
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.faq_list,null);
        }
        if(b) {
            view.setBackgroundResource(R.drawable.square_border_orangebckgrd);
        }else{
            view.setBackgroundResource(R.drawable.square_edit_text_border);
        }
        TextView faqTxt=view.findViewById(R.id.faq_txt);
        faqTxt.setText(faqListTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String faqListDes=(String)getChild(i, i1);
        if (view==null){
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.faq_description,null);
        }

        TextView faqDes=view.findViewById(R.id.faq_des);
        faqDes.setText(faqListDes);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
