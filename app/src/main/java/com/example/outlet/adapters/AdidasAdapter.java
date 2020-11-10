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

public class AdidasAdapter extends RecyclerView.Adapter<AdidasAdapter.ProductViewHolder> {

    private final ArrayList<Product> productList;

    public AdidasAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.adidas_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        Product product = productList.get(viewType);
        ProductViewHolder viewHolder = new ProductViewHolder(view);
        viewHolder.tvOriginPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.tvOriginPrice.setText(String.valueOf(product.getOriginalPrice()));
        viewHolder.tvCurrentPrice.setText(String.valueOf(product.getCurrentPrice()));
        viewHolder.tvName.setText(product.getName());
        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.newyorker.de/ru/products/#/detail/" + product.getProductId() + "/001?custom=sale"));
            view1.getContext().startActivity(intent);
        });
        Picasso.get()
                .load(product.getImagePath())
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
                .load(product.getImagePath())
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
            imageView = itemView.findViewById(R.id.imageAdidas);
            tvCurrentPrice = itemView.findViewById(R.id.tvCurrentPrice);
            tvOriginPrice = itemView.findViewById(R.id.tvOrigPrice);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
//package com.example.outlet.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.outlet.R;
//import com.example.outlet.models.Product;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//public class AdidasAdapter extends BaseAdapter {
//    Context context;
//    private ArrayList<Product> products;
//    private LayoutInflater lInflater;
//
//    public AdidasAdapter(Context ctx, ArrayList<Product> products){
//        context = ctx;
//        this.products = products;
//        lInflater = (LayoutInflater) ctx
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        return products.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return products.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = convertView;
//        if (view == null) {
//            view = lInflater.inflate(R.layout.adidas_list_item, parent, false);
//        }
//        Product product = (Product)getItem(position);
//        ((TextView) view.findViewById(R.id.tvName)).setText(product.getName());
//        ((TextView) view.findViewById(R.id.tvCurrentPrice)).setText(product.getCurrentPrice());
//        ((TextView) view.findViewById(R.id.tvOrigPrice)).setText(product.getOriginalPrice());
//        ImageView imageView = (ImageView)view.findViewById(R.id.imageAdidas);
//        Picasso.get()
//                .load(product.getImagePath())
//                .placeholder(R.drawable.ic_menu_manage)
//                .error(R.drawable.ic_menu_camera)
//                .fit()
//                .into(imageView);
//        return view;
//    }
//}
