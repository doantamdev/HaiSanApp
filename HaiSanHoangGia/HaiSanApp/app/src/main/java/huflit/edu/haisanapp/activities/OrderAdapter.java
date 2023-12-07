package huflit.edu.haisanapp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huflit.edu.haisanapp.R;
import huflit.edu.haisanapp.product.Cart;
import huflit.edu.haisanapp.product.Product;
import huflit.edu.haisanapp.product.ProductAdapter;
import huflit.edu.haisanapp.product.Utils;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    ArrayList<Cart> lstCart;

    Context context;

   OrderCallback orderCallback;

    public OrderAdapter(ArrayList<Cart> lstCart) {
        this.lstCart = lstCart;
    }

    public void setOrderCallback(OrderCallback orderCallback) {
        this.orderCallback = orderCallback;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử user
        View proView = inflater.inflate(R.layout.cartlist, parent, false);
        //
        OrderViewHolder viewHolder = new OrderViewHolder(proView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Cart item = lstCart.get(position);
        holder.orderName.setText(item.getTensp());
        holder.orderSoluong.setText(item.getSoluong());
        holder.orderPrice.setText(item.getGiasp());
        // bat su kien
        holder.ivDeleteOrder.setOnClickListener(view -> orderCallback.onItemDeleteClicked(item,holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return lstCart.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView orderName,orderSoluong,orderPrice;
        ImageView ivDeleteOrder;
       public OrderViewHolder(@NonNull View itemView) {
           super(itemView);
           orderName=itemView.findViewById(R.id.orderName);
           orderSoluong=itemView.findViewById(R.id.orderSoluong);
           orderPrice=itemView.findViewById(R.id.orderPrice);
           ivDeleteOrder=itemView.findViewById(R.id.ivDeleteOrder);
       }
   }
    public interface OrderCallback{
        void onItemDeleteClicked(Cart cart, int position);
    }
}
