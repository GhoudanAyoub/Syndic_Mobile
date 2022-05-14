package com.SyndicG5.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.R;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.repository.apiRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class AppartementAdapter extends RecyclerView.Adapter<AppartementAdapter.AppartementAdapterHolder> {

    private ArrayList<Appartement> AppartementList = new ArrayList<>();
    private Context context;

    public AppartementAdapter(Context context) {
        this.context = context;
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
        holder.transactionContact.setText("Appartement " + appartement.getNumero().toString());
        holder.transactionDate.setText("Etage " + appartement.getEtage().toString());
        holder.transactionAmount.setText("0 dh");
        holder.transactionAmount.setTextColor(Color.GREEN);
        holder.client_name_shortcut.setVisibility(View.VISIBLE);
        holder.client_name_shortcut.setText(appartement.getNumero().toString());
        holder.itemView.setOnClickListener(view -> {
            if (holder.hiddenView.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(holder.cardView,
                        new AutoTransition());
                holder.hiddenView.setVisibility(View.GONE);
            } else {

                TransitionManager.beginDelayedTransition(holder.cardView,
                        new AutoTransition());
                holder.hiddenView.setVisibility(View.VISIBLE);
            }
        });
        if (appartement.getResident() != null) {
            holder.residentName.setText(appartement.getResident().getNom());
            if (appartement.getResident().getPhoto()!=null)
                Glide.with(context)
                    .load(appartement.getResident().getPhoto())
                    .into(holder.residentImage);
            else
                holder.residentImage.setImageResource(R.drawable.ic_user);
        } else {
            holder.residentName.setText("Appartement Vide");
            holder.residentImage.setVisibility(View.GONE);
        }
        /* if (balance.getImmeuble().getPhoto() != null) {
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
        public TextView transactionContact, transactionAmount, transactionDate, client_name_shortcut, residentName;
        public ShapeableImageView client_image;
        public LinearLayout hiddenView;
        public RelativeLayout cardView;
        public ImageView residentImage;

        public AppartementAdapterHolder(View itemView) {
            super(itemView);
            transactionContact = itemView.findViewById(R.id.transactionContact);
            transactionAmount = itemView.findViewById(R.id.transactionAmount);
            transactionDate = itemView.findViewById(R.id.transactionDate);
            client_name_shortcut = itemView.findViewById(R.id.client_name_shortcut);
            client_image = itemView.findViewById(R.id.client_image);
            hiddenView = itemView.findViewById(R.id.hidden_view);
            cardView = itemView.findViewById(R.id.cardView);
            residentName = itemView.findViewById(R.id.residentName);
            residentImage = itemView.findViewById(R.id.residentImage);
        }
    }
}