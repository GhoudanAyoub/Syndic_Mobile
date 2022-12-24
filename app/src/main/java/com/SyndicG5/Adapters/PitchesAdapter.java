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
import com.syndicg5.networking.models.Pitches;
import com.syndicg5.networking.models.Pitches;
import com.syndicg5.networking.repository.apiRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class PitchesAdapter extends RecyclerView.Adapter<PitchesAdapter.PitchesAdapterHolder> {

    private ArrayList<Pitches> PitchesList = new ArrayList<>();
    private Context context;
    private apiRepository repository;
    private PitcherListener listener;

    public PitchesAdapter(Context context, apiRepository repository, PitcherListener listener) {
        this.context = context;
        this.repository=repository;
        this.listener=listener;
    }

    @NonNull
    @Override
    public PitchesAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PitchesAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.template_balance_item, parent, false));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull PitchesAdapterHolder holder, int position) {
        Pitches Pitches = PitchesList.get(position);

        holder.PitchesName.setText(Pitches.getName());
        if(Pitches.getComplexe()!=null)holder.pitch_location.setText(Pitches.getComplexe().getLocation());
        holder.pitch_description.setText(Pitches.getDescripton());
        Glide.with(context)
                .load(Pitches.getPhoto())
                .placeholder(R.drawable.img_placeholder)
                .into(holder.PitchesImage);
        holder.itemView.setOnClickListener(view -> {
            listener.onPitcherClicked(Pitches);
        });
    }

    @Override
    public int getItemCount() {
        return PitchesList.size();
    }

    public void setList(ArrayList<Pitches> PitchesList) {
        this.PitchesList = PitchesList;
        notifyDataSetChanged();
    }

    public class PitchesAdapterHolder extends RecyclerView.ViewHolder {
        public TextView  PitchesName,pitch_location,pitch_description;
        public ShapeableImageView PitchesImage;

        public PitchesAdapterHolder(View itemView) {
            super(itemView);
            PitchesName = itemView.findViewById(R.id.pitch_name);
            pitch_location = itemView.findViewById(R.id.pitch_location);
            pitch_description = itemView.findViewById(R.id.pitch_description);
            PitchesImage = itemView.findViewById(R.id.pitche_img);
        }
    }
    public interface PitcherListener {
        void onPitcherClicked(Pitches Pitches);
    }
}
