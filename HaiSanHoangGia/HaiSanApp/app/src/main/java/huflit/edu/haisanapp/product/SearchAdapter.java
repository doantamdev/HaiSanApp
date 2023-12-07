package huflit.edu.haisanapp.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import huflit.edu.haisanapp.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchProductViewHolder> implements Filterable {
    ArrayList<Product> lstPro;
    ArrayList<Product> lstProOld;
    Context context;

    SearchCallback searchCallback;

    public SearchAdapter(ArrayList<Product> lstPro) {
        this.lstPro = lstPro;
        this.lstProOld=lstPro;

    }
    public void setShowCallback(SearchCallback searchCallback) {
        this.searchCallback = searchCallback;
    }

    @NonNull
    @Override
    public SearchProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử user
        View showView = inflater.inflate(R.layout.showpro, parent, false);
        //
        SearchProductViewHolder viewHolder = new SearchProductViewHolder(showView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchProductViewHolder holder, int position) {
        Product item = lstPro.get(position);
        holder.imProShow.setImageBitmap(Utils.convertProToBitmapFromAssets(context, item.getImagepro()));
        holder.tvNameShow.setText(item.getName());
        holder.tvGiaShow.setText(item.getGia());

        holder.tvAddtoCartShow.setOnClickListener(v -> searchCallback.onItemClick(item,holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return lstPro.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty())
                {
                    lstPro = lstProOld;
                }
                else {
                    ArrayList<Product> list = new ArrayList<>();
                    for (Product product: lstProOld){
                        if(product.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(product);
                        }
                    }
                    lstPro = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values=lstPro;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                lstPro = (ArrayList<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    class SearchProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imProShow;
        TextView tvNameShow,tvGiaShow,tvAddtoCartShow;
        public SearchProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imProShow = itemView.findViewById(R.id.ivShowAnhPro);
            tvNameShow = itemView.findViewById(R.id.tvShowNamePro);
            tvGiaShow = itemView.findViewById(R.id.tvShowGia);
            tvAddtoCartShow = itemView.findViewById(R.id.addToCartShow);
        }
    }
    public interface SearchCallback{
        void onItemClick(Product product, int position);
    }
}
