package com.aoinc.w2_d4_c_mvp_sqlite.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.ListView;
import android.widget.Toast;

import com.aoinc.w2_d4_c_mvp_sqlite.R;
import com.aoinc.w2_d4_c_mvp_sqlite.model.Shoe;
import com.aoinc.w2_d4_c_mvp_sqlite.presenter.ShoePresenter;
import com.aoinc.w2_d4_c_mvp_sqlite.presenter.ShoeShopPresenterContract.*;
import com.aoinc.w2_d4_c_mvp_sqlite.view.adapter.ShoeItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShoeShopView, ShoeItemAdapter.ShoeItemDelegate {

    // ShoeShopPresenter is interface, ShoePresenter is class that implements ShoeShopPresenter
    private ShoeShopPresenter shoePresenter;

    public ListView shoeListView;
    private ShoeItemAdapter shoeItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoeListView = findViewById(R.id.shoe_listView);

        shoePresenter = new ShoePresenter(this);
//        shoePresenter.getAllShoes();

        for (int i = 0; i < 3; i++) {
            shoePresenter.insertShoe(new Shoe("Nike ", 10, 199.99));
            shoePresenter.insertShoe(new Shoe("Adidas ", 13, 94.95));
            shoePresenter.insertShoe(new Shoe("Vans ", 11, 120.86));
            shoePresenter.insertShoe(new Shoe("Skechers ", 9, 39.99));
        }

        shoePresenter.getAllShoes();
    }

    @Override
    public void displayShoes(List<Shoe> allShoes) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Log.d("TAG_X", String.valueOf(allShoes.size()));
                shoeItemAdapter = new ShoeItemAdapter(MainActivity.this, allShoes);
                shoeListView.setAdapter(shoeItemAdapter);
            }
        });

    }

    @Override
    public void SelectShoeItem(Shoe selectedShoe) {
        Toast.makeText(this, "Yeah, Toast!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError(String errorMessage) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                new AlertDialog
                        .Builder(new ContextThemeWrapper(MainActivity.this, R.style.Theme_W2_d4_C_MVP_SQLIte))
                        .setTitle("Database Error")
                        .setMessage(errorMessage)
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create()
                        .show();
            }
        });
    }

    @Override
    public void displaySuccess(String successMessage) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

//                new AlertDialog
//                        .Builder(new ContextThemeWrapper(MainActivity.this, R.style.Theme_W2_d4_C_MVP_SQLIte))
//                        .setTitle("Database Success")
//                        .setMessage(successMessage)
//                        .setNegativeButton("Thanks!", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        }).create()
//                        .show();
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }
}