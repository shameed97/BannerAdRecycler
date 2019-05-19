package com.example.banneradrecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> recyclerItem = new ArrayList<>();
    private Context context;

    public static final int ITEM_FRUIT = 0;
    public static final int ITEM_BANEER = 1;


    public RecyclerAdapater(List<Object> recyclerItem, Context context) {
        this.recyclerItem = recyclerItem;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        switch (i) {
            case ITEM_FRUIT:
                View fruitView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruits_item_list, viewGroup, false);
                return new FuitName(fruitView);

            case ITEM_BANEER:

            default:
                View banner = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.banner_ad_card, viewGroup, false);
                return new BannerAd(banner);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        int ViewType = getItemViewType(position);

        switch (ViewType) {

            case ITEM_FRUIT:
                FuitName fuitName = (FuitName) viewHolder;
                Fruit fruit = (Fruit) recyclerItem.get(position);
                int ImgId = context.getResources().getIdentifier(fruit.getFruitName().toLowerCase(), "drawable", context.getPackageName());
                fuitName.fuitImage.setImageResource(ImgId);
                fuitName.fruitName.setText(fruit.getFruitName());
                fuitName.fruitCalories.setText(fruit.getFruitCalories());
                break;

            case ITEM_BANEER:

            default:
                BannerAd bannerAd = (BannerAd) viewHolder;
                AdView adView = (AdView) recyclerItem.get(position);
                ViewGroup adCardView = (ViewGroup) bannerAd.itemView;

                if (adCardView.getChildCount() > 0) {
                    adCardView.removeAllViews();
                }
                if (adCardView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adCardView.addView(adView);
        }

    }

    @Override
    public int getItemCount() {


        return recyclerItem.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position % MainActivity.ITEM_PER_AD == 0)
            return ITEM_BANEER;

        else
            return ITEM_FRUIT;
    }

    public static class FuitName extends RecyclerView.ViewHolder {

        private ImageView fuitImage;
        private TextView fruitName, fruitCalories;


        public FuitName(@NonNull View itemView) {
            super(itemView);
            fuitImage = itemView.findViewById(R.id.fruit_pics);
            fruitName = itemView.findViewById(R.id.fruit_names);
            fruitCalories = itemView.findViewById(R.id.calorie_names);
        }
    }

    public static class BannerAd extends RecyclerView.ViewHolder {

        public BannerAd(@NonNull View itemView) {
            super(itemView);
        }
    }
}
