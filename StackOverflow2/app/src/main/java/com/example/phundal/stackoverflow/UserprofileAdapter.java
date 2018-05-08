package com.example.phundal.stackoverflow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phundal.stackoverflow.model.UserProfileModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserprofileAdapter extends RecyclerView.Adapter<UserprofileAdapter.UserprofileViewHolder> {
    private List<UserProfileModel> userProfileModels;
    private Context context;

    public UserprofileAdapter(List<UserProfileModel> userProfileModels, Context context) {
        this.userProfileModels = userProfileModels;
        this.context = context;
    }

    @NonNull
    @Override
    public UserprofileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stack_profile_item, parent, false);
        return new UserprofileViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull UserprofileViewHolder holder, int position) {
        UserProfileModel userProfileModel = userProfileModels.get(position);
        holder.age.setText(context.getString(R.string.age, userProfileModel.getAge()));
        holder.username.setText(userProfileModel.getDisplay_name());
        holder.reputation.setText(context.getString(R.string.reputation, userProfileModel.getReputation()));
        if (userProfileModel.getBadeCounts() != null) {
            String bronzeString = context.getString(R.string.bronze, userProfileModel.getBadeCounts().getBronze());
            String silverString = context.getString(R.string.silver, userProfileModel.getBadeCounts().getSilver());
            String goldString = context.getString(R.string.gold, userProfileModel.getBadeCounts().getGold());
            holder.bronzeRep.setText(bronzeString);
            holder.silverRep.setText(silverString);
            holder.goldRep.setText(goldString);
        }
        holder.link.setText(userProfileModel.getLink());

        Picasso.get().load(userProfileModel.getProfile_image()).into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        if (userProfileModels != null) {
            return userProfileModels.size();
        }
        return 0;
    }

    class UserprofileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profileImage)
        public ImageView profileImage;
        @BindView(R.id.username)
        public TextView username;
        @BindView(R.id.age)
        public TextView age;
        @BindView(R.id.link)
        public TextView link;
        @BindView(R.id.reputation)
        public TextView reputation;
        @BindView(R.id.reputationContainer)
        public LinearLayout reputationContainer;
        @BindView(R.id.bronzeRep)
        public TextView bronzeRep;
        @BindView(R.id.silverRep)
        public TextView silverRep;
        @BindView(R.id.goldRep)
        public TextView goldRep;

        public UserprofileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
