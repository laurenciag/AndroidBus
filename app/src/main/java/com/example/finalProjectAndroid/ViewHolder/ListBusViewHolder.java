package com.example.finalProjectAndroid.ViewHolder;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalProjectAndroid.Entity.Bus;
import com.example.finalProjectAndroid.R;
import com.example.finalProjectAndroid.Util.BusUtil;

import java.util.ArrayList;
import java.util.List;

public class ListBusViewHolder extends RecyclerView.ViewHolder {
    public TextView busCode, busMake;
    public CardView detailBus;

    public ListBusViewHolder(@NonNull View itemView) {
        super(itemView);
        this.busCode = itemView.findViewById(R.id.busCode);
        this.busMake = itemView.findViewById(R.id.busMake);
        this.detailBus = itemView.findViewById(R.id.detailBus);
    }
}
