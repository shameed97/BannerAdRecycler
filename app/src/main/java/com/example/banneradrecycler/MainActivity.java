package com.example.banneradrecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ITEM_PER_AD = 4;
    public static final String TEST_ID = "ca-app-pub-3940256099942544/6300978111";
    private RecyclerView recyclerView;
    private List<Object> recylerItems = new ArrayList<>();
    private RecyclerAdapater recyclerAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getFruits();
        getBannerAd();
        loadBannerAd();
        recyclerAdapater = new RecyclerAdapater(recylerItems, this);
        recyclerView.setAdapter(recyclerAdapater);
    }

    private void getFruits() {

        List<String> fruitNames = Arrays.asList(getResources().getStringArray(R.array.fruits_name));
        List<String> fruitCalories = Arrays.asList(getResources().getStringArray(R.array.calories_list));
        int count = 0;
        for (String fruitName : fruitNames) {
            recylerItems.add(new Fruit(fruitName, fruitCalories.get(count)));
            count++;
        }
    }

    private void getBannerAd() {
        for (int i = 0; i < recylerItems.size(); i += ITEM_PER_AD) {
            final AdView ad = new AdView(this);
            ad.setAdSize(AdSize.BANNER);
            ad.setAdUnitId(TEST_ID);
            recylerItems.add(i, ad);
        }
    }

    private void loadBannerAd() {
        for (int i = 0; i < recylerItems.size(); i++) {
            Object item = recylerItems.get(i);

            if (item instanceof AdView) {
                final AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }
}
