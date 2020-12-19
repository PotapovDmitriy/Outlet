package com.example.outlet.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.outlet.R;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView adidasImage = root.findViewById(R.id.imageAdidas);
        ImageView newYorkerImage = root.findViewById(R.id.imageNewYorker);
        ImageView hmImage = root.findViewById(R.id.imageHM);
        ImageView bershkaImage = root.findViewById(R.id.imageBershka);

//        Обрабокта банера для адидаса
        Picasso.get()
                .load("https://grandhall.md/sites/default/files/2020-01/640x360.jpg")
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_not_found)
                .fit()
                .into(adidasImage);
        adidasImage.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.adidas.ru/"));
            view1.getContext().startActivity(intent);
        });
//         Обработка банера для ньюЙоркера
        Picasso.get()
                .load("https://i.ytimg.com/vi/B-2c1FiPjwU/maxresdefault.jpg")
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_not_found)
                .fit()
                .into(newYorkerImage);
        newYorkerImage.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.newyorker.de/ru/men/"));
            view1.getContext().startActivity(intent);
        });
//        Обработка банера для H&M
        Picasso.get()
                .load("https://i.pinimg.com/736x/0f/aa/5f/0faa5f748bfb9434a471e4dfd493345a.jpg")
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_not_found)
                .fit()
                .into(hmImage);
        hmImage.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www2.hm.com/ru_ru/sale.html"));
            view1.getContext().startActivity(intent);
        });
//        Обработка банера для Bershka
        Picasso.get()
//                .load("https://pbs.twimg.com/media/CluCtOOUsAEmxuh.jpg")
                .load("https://www.aksiya.az/medias/article/big/1420/75595283-447759226168420-1347337223762848103-n.png")
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_not_found)
                .fit()
                .into(bershkaImage);
        bershkaImage.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bershka.com/ru/page/promo_woman.html"));
            view1.getContext().startActivity(intent);
        });
        return root;
    }
}