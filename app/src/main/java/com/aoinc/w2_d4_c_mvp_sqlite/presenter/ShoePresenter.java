package com.aoinc.w2_d4_c_mvp_sqlite.presenter;

import com.aoinc.w2_d4_c_mvp_sqlite.model.Shoe;
import com.aoinc.w2_d4_c_mvp_sqlite.model.db.ShoeDatabaseHelper;

public class ShoePresenter implements ShoeShopPresenterContract.ShoeShopPresenter {

    private ShoeShopPresenterContract.ShoeShopView shoeShopView;
    private ShoeDatabaseHelper shoeDatabaseHelper;

    public ShoePresenter(ShoeShopPresenterContract.ShoeShopView shoeShopView) {
        this.shoeShopView = shoeShopView;
        shoeDatabaseHelper = new ShoeDatabaseHelper(shoeShopView.getContext());
    }

    @Override
    public void getAllShoes() {
        // starting a new thread
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    shoeShopView.displayShoes(shoeDatabaseHelper.getAllShoesFromDatabase());
//                    shoeShopView.displaySuccess("YAY!");
                } catch (Exception e) {
                    e.printStackTrace();
                    shoeShopView.displayError((e.getMessage()));
                }
            }
        }.start();
    }

    @Override
    public void insertShoe(Shoe newShoe) {
        // starting a new thread
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    shoeDatabaseHelper.insertNewShoeIntoDatabase(newShoe);
                    shoeShopView.displaySuccess(newShoe.getShoeModel() + "added!!");
                } catch (Exception e) {
                    e.printStackTrace();
                    shoeShopView.displayError((e.getMessage()));
                }
            }
        }.start();
    }

    @Override
    public void deleteShoe(Shoe deleteShoe) {
        // starting a new thread
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    shoeDatabaseHelper.deleteShoeFromDatabase(deleteShoe);
                    shoeShopView.displaySuccess(deleteShoe.getShoeModel() + "deleted :(");
                } catch (Exception e) {
                    e.printStackTrace();
                    shoeShopView.displayError((e.getMessage()));
                }
            }
        }.start();
    }
}
