package com.example.personalinformationso.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.personalinformationso.R;
import com.example.personalinformationso.activity.DetailPersonalActivity;
import com.example.personalinformationso.listener.ItemClickListener;
import com.example.personalinformationso.model.PersonalModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.StationViewHolder>  implements Filterable {
    private List<PersonalModel> mPersonalList;
    private List<PersonalModel> mFilteredPersonalList;
    private LayoutInflater inflater;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public PersonalAdapter(Context context, List<PersonalModel> stations){
        inflater = LayoutInflater.from(context);
        this.mPersonalList = stations;
        this.mFilteredPersonalList = stations;
        mContext=context;
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_personal,parent,false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        PersonalModel personal = mFilteredPersonalList.get(position);
        holder.setData(personal,position);
    }

    @Override
    public int getItemCount() {
        return mFilteredPersonalList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty() || searchString.length()<3) {

                    mFilteredPersonalList = mPersonalList;

                } else {

                    ArrayList<PersonalModel> tempFilteredList = new ArrayList<>();

                    for (PersonalModel personal : mPersonalList) {
                        // search for personal name, city, country
                        if (personal.getFullName().toLowerCase().contains(searchString)
                        || personal.getCityName().contains(searchString)
                        || personal.getCountryName().contains(searchString)) {
                            tempFilteredList.add(personal);
                        }
                    }

                    mFilteredPersonalList = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredPersonalList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredPersonalList = (ArrayList<PersonalModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class StationViewHolder extends RecyclerView.ViewHolder {
        TextView txtFullName;
        ImageView avatar;
        TextView txtTitle;
        TextView txtCountry;
        TextView txtCity;

        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFullName = itemView.findViewById(R.id.textViewFullName);
            avatar = itemView.findViewById(R.id.imageViewAvatar);
            txtTitle = itemView.findViewById(R.id.textViewTitle);
            txtCountry = itemView.findViewById(R.id.textViewCountry);
            txtCity = itemView.findViewById(R.id.textViewCity);
        }

        @SuppressLint("SetTextI18n")
        public void setData(final PersonalModel personal, final int position) {
            this.txtTitle.setText(personal.getJobTitle());
            if(personal.getAvatar() != null){
                Glide.with(mContext).load(personal.getAvatar()).into(avatar);
            }
            this.txtFullName.setText(personal.getFullName());
            this.txtCountry.setText(personal.getCountryName());
            this.txtCity.setText(personal.getCityName());
            itemView.setOnClickListener(v -> mItemClickListener.onItemClick(personal,position));
        }
    }
}
