package edu.zu.demo.graduation.bazzar.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;
import java.util.HashMap;

import edu.zu.demo.graduation.bazzar.Model.Items;
import edu.zu.demo.graduation.bazzar.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    ArrayList<String > headerList=new ArrayList<>();
    HashMap<String ,ArrayList<Items>> childList=new HashMap<>();
    Context mContext;

    public ExpandableListAdapter(ArrayList<String> header, HashMap<String, ArrayList<Items>> childList, Context mContext) {
        this.headerList = header;
        this.childList = childList;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return headerList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(headerList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(headerList.get(groupPosition)).get(childPosition);
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

        String headerTitle=(String) getGroup(groupPosition);
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parent_expandable_row, parent, false);
        TextView headerTitleTxt=convertView.findViewById(R.id.parentExpandable_header);
        headerTitleTxt.setText(headerTitle);

        if (isExpanded){
            headerTitleTxt.setTypeface(null, Typeface.BOLD);
            headerTitleTxt.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_down,0);
        }
        else {
            headerTitleTxt.setTypeface(null, Typeface.NORMAL);
            headerTitleTxt.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_right,0);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
      Items item=(Items)getChild(groupPosition,childPosition);
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_expandable_row, parent, false);
        TextView itemNameTxt=convertView.findViewById(R.id.child_itemNmae);
        TextView itemDescTxt=convertView.findViewById(R.id.child_item_desc);
        TextView itemPrice=convertView.findViewById(R.id.child_itemPrice);
        ImageView itemImage=convertView.findViewById(R.id.child_itemImage);
        final ElegantNumberButton itemQuantityBtn=convertView.findViewById(R.id.child_itemQuantityBtn);
       // int  itemQuantity=Integer.parseInt(itemQuantityBtn.getNumber());

        itemNameTxt.setText(item.getItemName());
        itemDescTxt.setText(item.getDesc());
        itemPrice.setText(item.getItemPrice()+"");

        itemImage.setImageResource(item.getImageId());
        itemImage.setScaleType( ImageView.ScaleType.CENTER_CROP);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
