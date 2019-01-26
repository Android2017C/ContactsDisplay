package com.example.sreenureddy.contactstestapp;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ContactModel> contactModelArrayList;
    SparseBooleanArray mSparseBooleanArray;
    LayoutInflater mInflater;

    public ContactsAdapter(Context context, ArrayList<ContactModel> contactModelArrayList) {

        this.context = context;
        this.contactModelArrayList = contactModelArrayList;
        mInflater = LayoutInflater.from(context);
        mSparseBooleanArray = new SparseBooleanArray();
        contactModelArrayList = new ArrayList<ContactModel>();
    }

    public ArrayList<ContactModel> getCheckedItems() {
        ArrayList<ContactModel> mTempArry = new ArrayList<ContactModel>();
        for(int i=0;i<contactModelArrayList.size();i++) {
            if(mSparseBooleanArray.get(i)) {
                mTempArry.add(contactModelArrayList.get(i));
            }
        }
        return mTempArry;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return contactModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contacts_items, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvnumber = (TextView) convertView.findViewById(R.id.number);
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.chkEnable);

            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvname.setText(contactModelArrayList.get(position).getName());
        holder.tvnumber.setText(contactModelArrayList.get(position).getNumber());

        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setChecked(mSparseBooleanArray.get(position));


        holder.mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);

        return convertView;
    }

    CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
        }
    };

    private class ViewHolder {

        protected TextView tvname, tvnumber;
        protected CheckBox mCheckBox;

    }
}