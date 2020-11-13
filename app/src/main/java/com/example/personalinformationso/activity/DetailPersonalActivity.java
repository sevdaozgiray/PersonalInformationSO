package com.example.personalinformationso.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.personalinformationso.R;
import com.example.personalinformationso.model.PersonalModel;
import com.example.personalinformationso.utils.Const;


import org.parceler.Parcels;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author by Sevda$, Email xx@xx.com, Date on 13.11.2020$.
 * PS: Not easy to write code, please indicate.
 */


public class DetailPersonalActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.imageViewAvatar)
    ImageView imageViewAvatar;

    @BindView(R.id.textViewFullName)
    TextView textViewFullName;

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    @BindView(R.id.textViewDescription)
    TextView textViewDescription;

    @BindView(R.id.textViewCountry)
    TextView textViewCountry;

    @BindView(R.id.imageViewCity)
    ImageView imageViewCity;

    @BindView(R.id.textViewCityName)
    TextView textViewCityName;

    PersonalModel personal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_personal);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                personal= null;
            } else {
                personal= extras.getParcelable(Const.DETAIL_PERSONAL);
            }
        } else {
            personal= (PersonalModel) savedInstanceState.getSerializable(Const.DETAIL_PERSONAL);
        }

        init();



    }

    private void init() {
        toolbar.setTitle(personal.getFullName());
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        if(personal.getAvatar() != null){
            Glide.with(this).load(personal.getAvatar()).into(imageViewAvatar);
        }
        textViewFullName.setText(personal.getFullName());
        textViewTitle.setText(personal.getJobTitle());
        textViewDescription.setText(personal.getAbout());
        textViewCountry.setText(personal.getCountryName());
        textViewCityName.setText(personal.getCityName());
        if(personal.getCityPicture() != null){
            Glide.with(this).load(personal.getCityPicture()).into(imageViewCity);
        }
        toolbar.setNavigationOnClickListener(v -> onSupportNavigateUp());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
