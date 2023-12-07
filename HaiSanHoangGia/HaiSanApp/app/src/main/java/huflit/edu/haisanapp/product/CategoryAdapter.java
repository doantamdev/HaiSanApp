package huflit.edu.haisanapp.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import huflit.edu.haisanapp.R;
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Categorys> categorys;

    public CategoryAdapter(ArrayList<Categorys> categorys) {
        this.categorys = categorys;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cate,parent,false);
        return new ViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cateName.setText(categorys.get(position).getName());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl ="tom";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cate_bg12));
                break;
            }
            case 1: {
                picUrl ="cua";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cart_bg1));
                break;
            }
            case 2: {
                picUrl ="ca";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cate_bg));
                break;
            }
            case 3: {
                picUrl ="so";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cart_bg2));
                break;
            }
            case 4: {
                picUrl ="oc";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cart_bg1));
                break;
            }
        }
        int drawbleResourceId= holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
        .load(drawbleResourceId)
                .into(holder.catePic);
    }

    @Override
    public int getItemCount() {
        return categorys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cateName;
        ImageView catePic;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cateName = itemView.findViewById(R.id.tvCategory);
            catePic = itemView.findViewById(R.id.anhCategory);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
