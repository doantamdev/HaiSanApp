package huflit.edu.haisanapp.USER;

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

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.RoleViewHolder> {
    ArrayList<Role> lstRole;
    Context context;
    RoleCallback roleCallback;

    public RoleAdapter(ArrayList<Role> lstRole) {
        this.lstRole = lstRole;
    }

    public void setCallback(RoleCallback callback) {
        this.roleCallback = callback;
    }

    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Nạp layout cho View biểu diễn phần tử role
        View roleView = inflater.inflate(R.layout.rolelayoutitem, parent, false);
        RoleViewHolder viewHolder = new RoleViewHolder(roleView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        // lấy từng item của dữ liệu
        Role item = lstRole.get(position);
        // gán vào item của view
        holder.tvName.setText(String.valueOf(position+1)+" - "+ item.getName());
        // bat su kien
        holder.ivDelete.setOnClickListener(view -> roleCallback.onItemDeleteClicked(item, position));
        holder.ivEdit.setOnClickListener(view -> roleCallback.onItemEditClicked(item, position));
    }

    @Override
    public int getItemCount() {
        return lstRole.size();
    }

    class RoleViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivEdit;
        ImageView ivDelete;
        public RoleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivEdit= itemView.findViewById(R.id.ivEdit);
            ivDelete= itemView.findViewById(R.id.ivDelete);
        }
    }

    public interface RoleCallback{
        void onItemDeleteClicked(Role role, int position);
        void onItemEditClicked(Role role, int position);
    }
}
