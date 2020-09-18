package com.example.finalProjectAndroid.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalProjectAndroid.Entity.Bus;
import com.example.finalProjectAndroid.Fragment.DetailBusFragment;
import com.example.finalProjectAndroid.HomePage;
import com.example.finalProjectAndroid.R;
import com.example.finalProjectAndroid.ViewHolder.ListBusViewHolder;
import com.example.finalProjectAndroid.services.BusService;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<ListBusViewHolder> {


    public List<Bus> buses;
    Fragment fragment;
    FragmentManager fragmentManager;
    ViewGroup viewGroup;

    public BusAdapter(List<Bus> buses, FragmentManager fragmentManager) {
        this.buses = buses;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ListBusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewGroup = parent;
        return new ListBusViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_detail_bus, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListBusViewHolder holder, int position) {
        holder.busCode.setText(buses.get(position).getCode());
        holder.busMake.setText(buses.get(position).getMake());

        holder.detailBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),buses.get(position).getId(), Toast.LENGTH_SHORT).show();

                Fragment fragmentDetail = new DetailBusFragment(viewGroup.getContext(),buses.get(position).getId());
                fragmentManager.beginTransaction().replace(R.id.layoutFragment, fragmentDetail).commit();
                //lempar dataya pake bundle( kirim di constructor fragment aja)

                Log.d("Bus Adapter","test");

            }
        });

    }

    @Override
    public int getItemCount() {
        return buses.size();
    }


}

