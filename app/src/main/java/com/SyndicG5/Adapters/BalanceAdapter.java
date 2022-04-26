package com.SyndicG5.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.syndicg5.networking.models.Balance;

import java.util.ArrayList;
import java.util.List;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder> {

    private List<Balance> BalanceList = new ArrayList<>();
    private Context context;

    public BalanceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BalanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BalanceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.template_balance_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BalanceViewHolder holder, int position) {
        Balance balance = BalanceList.get(position);
        holder.transactionContact.setText(balance.getDescription());
        holder.transactionAmount.setText(balance.getOutcome()!=0.0?balance.getOutcome().toString():balance.getIncome().toString());
        holder.transactionAmount.setTextColor(balance.getIncome()>0.0? Color.GREEN:Color.RED);
    }

    @Override
    public int getItemCount() {
        return BalanceList.size();
    }

    public void setList(List<Balance> BalanceList) {
        this.BalanceList = BalanceList;
        notifyDataSetChanged();
    }

    public class BalanceViewHolder extends RecyclerView.ViewHolder {
        public TextView transactionContact,transactionAmount;
        public ShapeableImageView client_image;
        public BalanceViewHolder(View itemView) {
            super(itemView);
            transactionContact = itemView.findViewById(R.id.transactionContact);
            transactionAmount = itemView.findViewById(R.id.transactionAmount);
            client_image = itemView.findViewById(R.id.client_image);
        }
    }
}