package com.SyndicG5.Adapters;

import android.annotation.SuppressLint;
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
import com.google.android.material.imageview.ShapeableImageView;
import com.syndicg5.networking.models.Appartement;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.repository.apiRepository;

import java.util.ArrayList;

import io.reactivex.schedulers.Schedulers;

@RequiresApi(api = Build.VERSION_CODES.N)
public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesAdapterHolder> {

    private ArrayList<Appartement> GamesList = new ArrayList<>();
    private Context context;
    private apiRepository repository;

    public GamesAdapter(Context context, apiRepository repository) {
        this.context = context;
        this.repository=repository;
    }

    @NonNull
    @Override
    public GamesAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GamesAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.template_balance_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GamesAdapterHolder holder, int position) {
        Appartement appartement = GamesList.get(position);
    }

    @Override
    public int getItemCount() {
        return GamesList.size();
    }

    public void setList(ArrayList<Appartement> GamesList) {
        this.GamesList = GamesList;
        notifyDataSetChanged();
    }

    public class GamesAdapterHolder extends RecyclerView.ViewHolder {
        public TextView  PitchesName,pitch_location;
        public ShapeableImageView PitchesImage;

        public GamesAdapterHolder(View itemView) {
            super(itemView);
            PitchesName = itemView.findViewById(R.id.pitch_name);
            pitch_location = itemView.findViewById(R.id.pitch_location);
            PitchesImage = itemView.findViewById(R.id.pitche_img);
        }
    }
}
