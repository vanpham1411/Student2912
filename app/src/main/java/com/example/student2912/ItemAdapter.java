package com.example.student2912;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<StudentItem> listInSearch;
    List<StudentItem> list;
    private SearchFilter searchFilter;

    public ItemAdapter(Context context, List<StudentItem> list) {
        this.context = context;
        this.list = list;
        this.listInSearch = new ArrayList<>(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.student_item,parent, false);
            viewHolder = new ViewHolder();
            viewHolder.email = view.findViewById(R.id.txt_email);
            viewHolder.hoten = view.findViewById(R.id.txt_hoten);
            viewHolder.mssv = view.findViewById(R.id.txt_mssv);
            view.setTag(viewHolder);

        }
        else viewHolder = (ViewHolder) view.getTag();
        StudentItem studentItem = list.get(i);

        viewHolder.hoten.setText(studentItem.hoten);
        viewHolder.mssv.setText(studentItem.mssv);
        viewHolder.email.setText(studentItem.email);
        notifyDataSetChanged();
        return view;
    }

    @Override
    public Filter getFilter() {
       if(searchFilter == null){
           searchFilter = new SearchFilter();
       }
       return searchFilter;
    }

    private class SearchFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint == null || constraint.length() == 0) {
                results.values = listInSearch;
                results.count = listInSearch.size();
            }else {
                ArrayList<StudentItem> filterlist = new ArrayList<>();
                for (StudentItem j: listInSearch){
                    if(j.getHoten().toLowerCase().contains(constraint) || j.getMssv().contains(constraint)){
                        filterlist.add(j);
                    }
                }
                results.values = filterlist;
                results.count = filterlist.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(results.count == 0){
                notifyDataSetChanged();
            }else {
                list = (ArrayList<StudentItem>) results.values;
                notifyDataSetChanged();
            }
        }
    }

    public void remove(int position){
        list.remove (position);
        notifyDataSetChanged ();
    }
    public void add(StudentItem std){
        list.add(std);
        notifyDataSetChanged();
    }
    public void update(StudentItem studentItem, int position){
        list.get(position).setNgaysinh(studentItem.getNgaysinh());
        list.get(position).setEmail(studentItem.getEmail());
        list.get(position).setDiachi(studentItem.getDiachi());
        list.get(position).setHoten(studentItem.getHoten());
        notifyDataSetChanged();
        Log.v("tag/","thay doi: "+studentItem.getMssv());
    }

    private class ViewHolder {
        TextView hoten;
        TextView mssv;
        TextView email;
    }

}
