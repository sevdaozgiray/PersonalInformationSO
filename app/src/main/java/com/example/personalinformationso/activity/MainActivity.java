package com.example.personalinformationso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.personalinformationso.R;
import com.example.personalinformationso.adapter.PersonalAdapter;
import com.example.personalinformationso.api.Services;
import com.example.personalinformationso.listener.ItemClickListener;
import com.example.personalinformationso.model.PersonalModel;
import com.example.personalinformationso.utils.ApiClient;
import com.example.personalinformationso.utils.Const;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    Services services;
    PersonalAdapter adapter;
    List<PersonalModel> list = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    Spinner spinnerCity;
    Spinner spinnerCountry;
    private String selectedCity;
    private String selectedCountry;
    List<String> spinnerCityData = new ArrayList<>();
    List<String> spinnerCountryData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =  findViewById(R.id.recyclerView);
        spinnerCity =  findViewById(R.id.spinnerCity);
        spinnerCountry =  findViewById(R.id.spinnerCountry);
        swipeRefreshLayout= findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);
        adapter = new PersonalAdapter(this, list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setItemClickListener(this);
        getData();
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapterCity, View v, int i, long lng) {
                selectedCity =  adapterCity.getItemAtPosition(i).toString();
                adapter.getFilter().filter(selectedCity);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
            }
        });
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapterCountry, View v, int i, long lng) {
                selectedCountry =  adapterCountry.getItemAtPosition(i).toString();
                adapter.getFilter().filter(selectedCountry);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
            }
        });

    }

    private void getData() {

        services = ApiClient.createService(Services.class);
        Call<List<PersonalModel>> call = services.getPersonalList();

        call.enqueue(new Callback<List<PersonalModel>>() {
            @Override
            public void onResponse(Call<List<PersonalModel>> call, Response<List<PersonalModel>> response) {
                if(swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);

                if (response.body() != null){
                    list.addAll(response.body());
                    spinnerCountryData.add("");
                    spinnerCityData.add("");
                    for(int i=0; i<response.body().size(); i++){
                        spinnerCityData.add(response.body().get(i).getCityName());
                        spinnerCountryData.add(response.body().get(i).getCountryName());
                    }
                    getSpinner();
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<PersonalModel>> call, Throwable t) {
                if(swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    private void getSpinner() {

        ArrayAdapter<String> spinnerArrayCityAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        spinnerCityData);
        spinnerArrayCityAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinnerCity.setAdapter(spinnerArrayCityAdapter);

        ArrayAdapter<String> spinnerArrayCountryAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        spinnerCountryData);
        spinnerArrayCountryAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(spinnerArrayCountryAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(PersonalModel personal, int position) {
        Intent i = new Intent(this, DetailPersonalActivity.class);
        i.putExtra(Const.DETAIL_PERSONAL, personal);
        startActivity(i);
    }

    @Override
    public void onRefresh() {
        list.clear();
        getData();
    }
}
