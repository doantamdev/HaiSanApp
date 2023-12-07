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

public class ShowProductAdapter extends RecyclerView.Adapter<ShowProductAdapter.ShowProductViewHolder> {
    ArrayList<Product> lstPro;

    Context context;

    ShowCallback showCallback;
    private IClickAddtoCartListener iClickAddtoCartListener;
    public void setData(ArrayList<Product> list, IClickAddtoCartListener listener){
        this.lstPro = list;
        this.iClickAddtoCartListener = listener;
        notifyDataSetChanged();
    }

    public interface IClickAddtoCartListener{
        void onClickAddtoCart(TextView tvAddtoCart,Product product);
    }

    public ShowProductAdapter(ArrayList<Product> lstPro) {
        this.lstPro = lstPro;
    }

    public void setShowCallback(ShowCallback showCallback) {
        this.showCallback = showCallback;
    }

    @NonNull
    @Override
    public ShowProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử user
        View showView = inflater.inflate(R.layout.showpro, parent, false);
        //
        ShowProductViewHolder viewHolder = new ShowProductViewHolder(showView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowProductViewHolder holder, int position) {
        Product item = lstPro.get(position);
        holder.imProShow.setImageBitmap(Utils.convertProToBitmapFromAssets(context, item.getImagepro()));
        holder.tvNameShow.setText(item.getName());
        holder.tvGiaShow.setText(item.getGia());
        holder.imProShow.setOnClickListener(v -> showCallback.onItemClick(item,holder.getAdapterPosition()));

        holder.tvAddtoCartShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!item.isAddtoCart()) {
                    iClickAddtoCartListener.onClickAddtoCart(holder.tvAddtoCartShow, item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstPro.size();
    }

    class ShowProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imProShow;
        TextView tvNameShow,tvGiaShow,tvAddtoCartShow;
        public ShowProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imProShow = itemView.findViewById(R.id.ivShowAnhPro);
            tvNameShow = itemView.findViewById(R.id.tvShowNamePro);
            tvGiaShow = itemView.findViewById(R.id.tvShowGia);
            tvAddtoCartShow = itemView.findViewById(R.id.addToCartShow);
        }
    }
    public interface ShowCallback{
        void onItemClick(Product product, int position);
    }
}
