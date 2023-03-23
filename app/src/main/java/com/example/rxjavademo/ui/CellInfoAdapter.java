package com.example.rxjavademo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rxjavademo.R;

import java.util.List;

public class CellInfoAdapter extends RecyclerView.Adapter<CellInfoAdapter.CellInfoViewHolder> {

    private final List<String> cellInformationList;

    public CellInfoAdapter(List<String> cellInformationList) {
        this.cellInformationList = cellInformationList;
    }

    @NonNull
    @Override
    public CellInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cellinfo_row_item, parent, false);
        return new CellInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CellInfoViewHolder holder, int position) {
        String cellInformation = cellInformationList.get(position);
        holder.tvCellInfoValue.setText(cellInformation);
    }

    @Override
    public int getItemCount() {
        return cellInformationList.size();
    }

    public static class CellInfoViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView tvCellInfoValue;

        public CellInfoViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvCellInfoValue = itemView.findViewById(R.id.tvCellInfoValue);
        }
    }
}
