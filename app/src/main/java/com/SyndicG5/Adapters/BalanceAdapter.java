package com.SyndicG5.Adapters;

import static com.syndicg5.networking.utils.Commun.getClientNameInitials;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.R;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.syndicg5.networking.models.Depense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder> {

    private List<Depense> BalanceList = new ArrayList<>();
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
        Depense balance = BalanceList.get(position);
        holder.transactionContact.setText(balance.getDescription());
        holder.transactionDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(balance.getDate()));
        holder.transactionAmount.setText(balance.getMontant().toString());
        holder.transactionAmount.setTextColor(Color.GREEN);
        holder.client_name_shortcut.setVisibility(View.VISIBLE);
        holder.client_name_shortcut.setText(getClientNameInitials(balance.getImmeuble().getNom()));
        Glide.with(context)
                .load(balance.getImmeuble().getPhoto())
                .into(holder.client_image);
        if (balance.getImmeuble().getPhoto() != null) {
            holder.client_name_shortcut.setVisibility(View.GONE);
            holder.client_image.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return BalanceList.size();
    }

    public void setList(List<Depense> BalanceList) {
        this.BalanceList = BalanceList;
        notifyDataSetChanged();
    }

    public class BalanceViewHolder extends RecyclerView.ViewHolder {
        public TextView transactionContact, transactionAmount, transactionDate, client_name_shortcut;
        public ShapeableImageView client_image;

        public BalanceViewHolder(View itemView) {
            super(itemView);
            transactionContact = itemView.findViewById(R.id.transactionContact);
            transactionAmount = itemView.findViewById(R.id.transactionAmount);
            transactionDate = itemView.findViewById(R.id.transactionDate);
            client_name_shortcut = itemView.findViewById(R.id.client_name_shortcut);
            client_image = itemView.findViewById(R.id.client_image);
        }
    }
}