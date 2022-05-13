package com.SyndicG5.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Depense;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.repository.apiRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppartementAdapter extends RecyclerView.Adapter<AppartementAdapter.AppartementAdapterHolder> {

    private ArrayList<Appartement> AppartementList = new ArrayList<>();
    private Context context;
    public AppartementAdapter(Context context) {
        this.context=context;
    }

    @Inject
    apiRepository apiRepository;

    @NonNull
    @Override
    public AppartementAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppartementAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.template_balance_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppartementAdapterHolder holder, int position) {
        Appartement appartement = AppartementList.get(position);
        holder.transactionContact.setText("Appartement "+appartement.getNumero().toString());
        holder.transactionDate.setText("Etage "+appartement.getEtage().toString());
        holder.transactionAmount.setText("0 dh");
        holder.transactionAmount.setTextColor(Color.GREEN);
        holder.client_name_shortcut.setVisibility(View.VISIBLE);
        holder.client_name_shortcut.setText(appartement.getNumero().toString());
       /* Glide.with(context)
                .load(balance.getImmeuble().getPhoto())
                .into(holder.client_image);
        if (balance.getImmeuble().getPhoto() != null) {
            holder.client_name_shortcut.setVisibility(View.GONE);
            holder.client_image.setVisibility(View.VISIBLE);
        }*/

    }

    @Override
    public int getItemCount() {
        return AppartementList.size();
    }

    public void setList(ArrayList<Appartement> AppartementList) {
        this.AppartementList = AppartementList;
        notifyDataSetChanged();
    }

    public class AppartementAdapterHolder extends RecyclerView.ViewHolder {
        public TextView transactionContact, transactionAmount, transactionDate, client_name_shortcut;
        public ShapeableImageView client_image;

        public AppartementAdapterHolder(View itemView) {
            super(itemView);
            transactionContact = itemView.findViewById(R.id.transactionContact);
            transactionAmount = itemView.findViewById(R.id.transactionAmount);
            transactionDate = itemView.findViewById(R.id.transactionDate);
            client_name_shortcut = itemView.findViewById(R.id.client_name_shortcut);
            client_image = itemView.findViewById(R.id.client_image);
        }
    }
}