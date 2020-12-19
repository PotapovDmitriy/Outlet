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

public class OutletAdapter extends RecyclerView.Adapter<OutletAdapter.ProductViewHolder> {

    private final ArrayList<Product> productList;
    View view;

    public OutletAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(layoutIdForListItem, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(view);
        viewHolder.tvOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return viewHolder;
    }

    private void  bind(ProductViewHolder holder){
        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(holder.tvLink.getText().toString()));
            view1.getContext().startActivity(intent);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvCurrentPrice.setText(new StringBuilder().append(product.getSalePrice()).append("₽").toString());
        holder.tvOriginPrice.setText(new StringBuilder().append(product.getOriginPrice()).append("₽").toString());
        holder.tvName.setText(product.getName());
        holder.tvLink.setText(product.getURL());

        Picasso.get()
                .load(product.getImageURL())
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_not_found)
                .fit()
                .into(holder.imageView);
        bind(holder);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tvCurrentPrice, tvOriginPrice, tvName, tvLink;
        ImageView imageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            tvCurrentPrice = itemView.findViewById(R.id.tvCurrentPrice);
            tvOriginPrice = itemView.findViewById(R.id.tvOrigPrice);
            tvName = itemView.findViewById(R.id.tvName);
            tvLink = itemView.findViewById(R.id.tvLink);
        }
    }
}
