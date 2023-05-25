package com.example.suhu;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Data> dataList;

    public DataAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Data data = dataList.get(position);
        // Format suhu dengan satu desimal dan satuan °C
        String suhuText = String.format("Suhu: %.1f °C", data.getSuhu());

        // Membuat objek SpannableString untuk tampilan suhu
        SpannableString spannableSuhuString = new SpannableString(suhuText);

        // Mengatur ukuran font untuk teks "Suhu:"
        spannableSuhuString.setSpan(new RelativeSizeSpan(1.0f), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Mengatur ukuran font untuk nilai suhu
        spannableSuhuString.setSpan(new RelativeSizeSpan(1.5f), 6, suhuText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Mengaplikasikan SpannableString ke TextView
        holder.tvSuhu.setText(spannableSuhuString);
        String alkoholText = String.format("Alkohol: %.1f %%", data.getAlkohol());
        SpannableString spannableAlkoholString = new SpannableString(alkoholText);

        // Setting a larger font size for "Alkohol:"
        spannableAlkoholString.setSpan(new RelativeSizeSpan(1.0f), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Setting a smaller font size for the alcohol value
        spannableAlkoholString.setSpan(new RelativeSizeSpan(1.5f), 9, alkoholText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Applying the SpannableString to the TextView
        holder.tvKadarAlkohol.setText(spannableAlkoholString);
        holder.tvKondisi.setText(data.getStatus());
        String waktu = data.getWaktu();
        String[] waktuSplit = waktu.split(" ");
        String tanggal = waktuSplit[1];
        String jam = waktuSplit[2];
        holder.tvTanggal.setText(tanggal);
        holder.tvJam.setText(jam);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSuhu, tvKadarAlkohol, tvKondisi, tvJam, tvTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSuhu = itemView.findViewById(R.id.tv_suhu);
            tvKadarAlkohol = itemView.findViewById(R.id.tv_alkohol);
            tvKondisi = itemView.findViewById(R.id.tv_kondisi);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvJam = itemView.findViewById(R.id.tv_jam);
        }
    }
}
