package com.exampple.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.exampple.sqlite.databinding.ItemEmpBinding;

import java.util.ArrayList;

public class EmployeeAdaptor extends RecyclerView.Adapter<EmployeeAdaptor.MyViewHolder> {
    ArrayList<Emp> listdata;
    Context context;

    public EmployeeAdaptor(ArrayList<Emp> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemEmpBinding itemEmpBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_emp, parent, false);
        EmployeeAdaptor.MyViewHolder viewHolder = new EmployeeAdaptor.MyViewHolder(itemEmpBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Emp emp = listdata.get(position);
        holder.itemEmpBinding.setEmp(emp);

        holder.itemEmpBinding.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(context, "emp", null, 1);
                dbHelper.deleterEntry(emp.name);

                listdata = dbHelper.readEmployees();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ItemEmpBinding itemEmpBinding;

        public MyViewHolder(@NonNull ItemEmpBinding itemView) {
            super(itemView.getRoot());
            itemEmpBinding = itemView;
        }
    }
}
