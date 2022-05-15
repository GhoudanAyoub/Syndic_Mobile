package com.SyndicG5.Adapters;

import static com.syndicg5.networking.utils.Commun.getClientNameInitials;

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
import com.syndicg5.networking.models.Resident;

import java.util.ArrayList;

public class ResidentAdapter extends RecyclerView.Adapter<ResidentAdapter.ResidentAdapterHolder> {

    private ArrayList<Resident> ResidentList = new ArrayList<>();
    private Context context;

    public ResidentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ResidentAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResidentAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.template_balance_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ResidentAdapterHolder holder, int position) {
        Resident resident = ResidentList.get(position);

        holder.transactionContact.setText(resident.getNom() + " " + resident.getPrenom());
        holder.transactionDate.setText(resident.getEmail());
        holder.transactionAmount.setText(resident.getVille());
        holder.transactionAmount.setTextColor(Color.GRAY);
        holder.client_name_shortcut.setText(getClientNameInitials(resident.getNom()));
        holder.itemView.setOnClickListener(view -> {
        });

        if (resident.getPhoto() != null) {
            Glide.with(context)
                    .load(resident.getPhoto())
                    .into(holder.residentImage);
            holder.client_name_shortcut.setVisibility(View.GONE);
        } else
            holder.client_name_shortcut.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return ResidentList.size();
    }

    public void setList(ArrayList<Resident> ResidentList) {
        this.ResidentList = ResidentList;
        notifyDataSetChanged();
    }

    public class ResidentAdapterHolder extends RecyclerView.ViewHolder {
        public TextView transactionContact, transactionAmount, transactionDate, client_name_shortcut, residentName;
        public ShapeableImageView client_image;
        public LinearLayout hiddenView;
        public RelativeLayout cardView;
        public ImageView residentImage;

        public ResidentAdapterHolder(View itemView) {
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