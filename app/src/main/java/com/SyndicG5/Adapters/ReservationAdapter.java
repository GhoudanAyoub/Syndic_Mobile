package com.SyndicG5.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.SyndicG5.R;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.syndicg5.networking.models.Reservation;
import com.syndicg5.networking.repository.apiRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationAdapterHolder> {

    private ArrayList<Reservation> ReservationList = new ArrayList<>();
    private Context context;
    private apiRepository repository;
    private ReservationListener listener;

    public ReservationAdapter(Context context, apiRepository repository, ReservationListener listener) {
        this.context = context;
        this.repository=repository;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ReservationAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReservationAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.template_balance_item, parent, false));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ReservationAdapterHolder holder, int position) {
        Reservation Reservation = ReservationList.get(position);

        holder.ReservationName.setText(Reservation.getPitch().getName());
        if(Reservation.getPitch().getComplexe()!=null)holder.pitch_location.setText(Reservation.getPitch().getComplexe().getLocation());
        if (Reservation.getMatchDate()!=null){
            holder.reservation_date.setText(new SimpleDateFormat("dd/MM/yyyy").format(Reservation.getMatchDate()));
            holder.reservation_date.setVisibility(View.VISIBLE);
        }
        holder.pitch_description.setText(Reservation.getPitch().getDescripton());
        Glide.with(context)
                .load(Reservation.getPitch().getPhoto())
                .placeholder(R.drawable.img_placeholder)
                .into(holder.ReservationImage);
        holder.itemView.setOnClickListener(view -> {
            listener.onReservationClicked(Reservation);
        });
    }

    @Override
    public int getItemCount() {
        return ReservationList.size();
    }

    public void setList(ArrayList<Reservation> ReservationList) {
        this.ReservationList = ReservationList;
        notifyDataSetChanged();
    }

    public class ReservationAdapterHolder extends RecyclerView.ViewHolder {
        public TextView ReservationName,pitch_location,pitch_description,reservation_date;
        public ShapeableImageView ReservationImage;

        public ReservationAdapterHolder(View itemView) {
            super(itemView);
            ReservationName = itemView.findViewById(R.id.pitch_name);
            pitch_location = itemView.findViewById(R.id.pitch_location);
            pitch_description = itemView.findViewById(R.id.pitch_description);
            reservation_date = itemView.findViewById(R.id.reservation_date);
            ReservationImage = itemView.findViewById(R.id.pitche_img);
        }
    }
    public interface ReservationListener {
        void onReservationClicked(Reservation Reservation);
    }
}
