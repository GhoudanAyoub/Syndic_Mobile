package com.SyndicG5.Adapters;

import static com.syndicg5.networking.utils.Commun.getClientNameInitials;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.R;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Resident;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.repository.apiRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.schedulers.Schedulers;

public class ResidentAdapter extends RecyclerView.Adapter<ResidentAdapter.ResidentAdapterHolder> {

    private ArrayList<Resident> ResidentList = new ArrayList<>();
    private Context context;
    private apiRepository repository;

    public ResidentAdapter(Context context, apiRepository repository) {
        this.context = context;
        this.repository=repository;
    }

    @NonNull
    @Override
    public ResidentAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResidentAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.template_balance_item, parent, false));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ResidentAdapterHolder holder, int position) {
        Resident resident = ResidentList.get(position);

        holder.transactionContact.setText(resident.getNom() + " " + resident.getPrenom());
        holder.transactionDate.setText(resident.getEmail());
        holder.transactionAmount.setText(resident.getVille());
        holder.transactionAmount.setTextColor(Color.GRAY);
        holder.client_name_shortcut.setText(getClientNameInitials(resident.getNom()));
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
        List<Appartement> appartements = getAppartementByResident(resident.getId());
        if (appartements != null) {
            holder.residentImage.setVisibility(View.GONE);
            if(appartements.get(0).getDebut()!=null)holder.dateEntrer.setText("Entrer:\n"+new SimpleDateFormat("yyyy-MM-dd").format(appartements.get(0).getDebut()));
            if(appartements.get(0).getFin()!=null)holder.dateSortie.setText("Sortie:\n"+new SimpleDateFormat("yyyy-MM-dd").format(appartements.get(0).getFin()));
            holder.AppartmentNum.setText("Appartement : "+appartements.get(0).getNumero()+"");
        } else {
            holder.residentName.setText("Appartement Vide");
            holder.residentImage.setVisibility(View.GONE);
        }
    }

    public List<Appartement> getAppartementByResident(int id){
        return new ArrayList<>(repository.getAppartementByResident(id)
                .subscribeOn(Schedulers.io()).blockingGet());

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
        public TextView transactionContact, transactionAmount, transactionDate, client_name_shortcut, residentName,AppartmentNum,dateEntrer,dateSortie;
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
            AppartmentNum = itemView.findViewById(R.id.AppartmentNum);
            dateEntrer = itemView.findViewById(R.id.dateEntrer);
            dateSortie = itemView.findViewById(R.id.dateSortie);
            residentImage = itemView.findViewById(R.id.residentImage);
        }
    }
}