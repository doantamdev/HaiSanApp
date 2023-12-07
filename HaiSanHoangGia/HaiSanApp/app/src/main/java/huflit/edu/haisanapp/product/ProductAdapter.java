package huflit.edu.haisanapp.product;

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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    ArrayList<Product> lstPro;
    Context context;

     ProCallback proCallback;

    public ProductAdapter(ArrayList<Product> lstPro) {
        this.lstPro = lstPro;
    }

    public void setProCallback(ProCallback proCallback) {
        this.proCallback = proCallback;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử user
        View proView = inflater.inflate(R.layout.layoutproducts, parent, false);
        //
        ProductViewHolder viewHolder = new ProductViewHolder(proView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product item = lstPro.get(position);
        holder.imPro.setImageBitmap(Utils.convertProToBitmapFromAssets(context, item.getImagepro()));
        holder.tvName.setText(item.getName() + " - "+ item.catename);
        holder.tvSoluong.setText(item.getSoluong());
        holder.tvGia.setText(item.getGia());
        // bat su kien
        holder.ivDeletePro.setOnClickListener(view -> proCallback.onItemDeleteClicked(item,holder.getAdapterPosition()));
        holder.ivEditPro.setOnClickListener(view -> proCallback.onItemEditClicked(item,holder.getAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return lstPro.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imPro;
        TextView tvName,tvSoluong,tvGia;
        ImageView ivEditPro;
        ImageView ivDeletePro;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imPro = itemView.findViewById(R.id.ivProImage);
            tvName = itemView.findViewById(R.id.tvNamePro);
            tvSoluong = itemView.findViewById(R.id.tvSoluong);
            tvGia = itemView.findViewById(R.id.tvGia);
            ivEditPro= itemView.findViewById(R.id.ivEditPro);
            ivDeletePro= itemView.findViewById(R.id.ivDeletePro);
        }
    }

    public interface ProCallback{
        void onItemDeleteClicked(Product product, int position);
        void onItemEditClicked(Product product, int position);
    }
}
