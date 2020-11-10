package com.example.outlet.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.outlet.R;
import com.example.outlet.models.Product;
import java.util.ArrayList;

public class NewYorkerAdapter extends RecyclerView.Adapter<NewYorkerAdapter.ProductViewHolder> {

    private final ArrayList<Product> productList;
    private int counter;

    public NewYorkerAdapter(ArrayList<Product> productList) {
        this.productList = productList;
        counter = 0;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.newyorker_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        Product product = productList.get(counter);
        ProductViewHolder viewHolder = new ProductViewHolder(view);


        viewHolder.tvOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.tvOriginPrice.setText(String.valueOf(product.getOriginalPrice()));
        viewHolder.tvCurrentPrice.setText(String.valueOf(product.getCurrentPrice()));
        viewHolder.tvName.setText(product.getName());
        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.newyorker.de/ru/products/#/detail/" + product.getProductId() + "/001?custom=sale"));
            view1.getContext().startActivity(intent);
        });
        counter++;
        Picasso.get()
                .load("https://nyblobstoreprod.blob.core.windows.net/product-images-public/" + product.getImagePath())
                .placeholder(R.drawable.ic_menu_manage)
                .error(R.drawable.ic_menu_camera)
                .fit()
                .into(viewHolder.imageView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvCurrentPrice.setText(String.valueOf(product.getCurrentPrice()));
        holder.tvOriginPrice.setText(String.valueOf(product.getOriginalPrice()));
        holder.tvName.setText(product.getName());

        Picasso.get()
                .load("https://nyblobstoreprod.blob.core.windows.net/product-images-public/" + product.getImagePath())
                .placeholder(R.drawable.ic_menu_manage)
                .error(R.drawable.ic_menu_camera)
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tvCurrentPrice, tvOriginPrice, tvName;
        ImageView imageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewNewYorker);
            tvCurrentPrice = itemView.findViewById(R.id.tvNewYorkerCurrentPrice);
            tvOriginPrice = itemView.findViewById(R.id.tvNewYorkerOrigPrice);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
