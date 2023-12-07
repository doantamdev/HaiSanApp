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

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.CateViewHolder> {
    ArrayList<Cate> lstCate;

    Context context;
    CateCallback cateCallback;

    public CateAdapter(ArrayList<Cate> lstCate) {
        this.lstCate = lstCate;
    }

    public void setCallback(CateCallback callback)
    {
        this.cateCallback = callback;
    }

    @NonNull
    @Override
    public CateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử user
        View cateView = inflater.inflate(R.layout.layoutcate, parent, false);
        CateViewHolder viewHolder = new CateViewHolder(cateView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CateViewHolder holder, int position) {
        // lấy từng item của dữ liệu
        Cate item = lstCate.get(position);
        // gán vào item của view
        holder.imCate.setImageBitmap(Utils.convertCateToBitmapFromAssets(context, item.getImageCate()));
        holder.tvName.setText(item.getNameCate());
        // bat su kien
        holder.ivDelete.setOnClickListener(view -> cateCallback.onItemDeleteClicked(item, position));
        holder.ivEdit.setOnClickListener(view -> cateCallback.onItemEditClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstCate.size();
    }


    class CateViewHolder extends RecyclerView.ViewHolder {
        ImageView imCate;
        TextView tvName;
        ImageView ivEdit;
        ImageView ivDelete;


        public CateViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameCate);
            ivEdit = itemView.findViewById(R.id.ivEditCate);
            ivDelete = itemView.findViewById(R.id.ivDeleteCate);
            imCate = itemView.findViewById(R.id.ivImageCate);
        }
    }

    public interface CateCallback {
        void onItemDeleteClicked(Cate cate, int position);
        void onItemEditClicked(Cate cate, int position);
    }
}
