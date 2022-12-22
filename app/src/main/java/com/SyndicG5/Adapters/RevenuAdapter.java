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
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.models.Revenu;
import com.syndicg5.networking.repository.apiRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.schedulers.Schedulers;

public class RevenuAdapter extends RecyclerView.Adapter<RevenuAdapter.RevenuAdapterHolder> {

    private ArrayList<Revenu> RevenuList = new ArrayList<>();
    private Context context;

    public RevenuAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RevenuAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RevenuAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.template_balance_item, parent, false));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull RevenuAdapterHolder holder, int position) {
        Revenu Revenu = RevenuList.get(position);

    }

    @Override
    public int getItemCount() {
        return RevenuList.size();
    }

    public void setList(ArrayList<Revenu> RevenuList) {
        this.RevenuList = RevenuList;
        notifyDataSetChanged();
    }

    public class RevenuAdapterHolder extends RecyclerView.ViewHolder {
        public TextView  PitchesName,pitch_location;
        public ShapeableImageView PitchesImage;

        public RevenuAdapterHolder(View itemView) {
            super(itemView);
            PitchesName = itemView.findViewById(R.id.pitch_name);
            pitch_location = itemView.findViewById(R.id.pitch_location);
            PitchesImage = itemView.findViewById(R.id.pitche_img);
        }
    }
}
