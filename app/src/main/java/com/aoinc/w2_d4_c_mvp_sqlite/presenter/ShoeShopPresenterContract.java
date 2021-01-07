package com.aoinc.w2_d4_c_mvp_sqlite.presenter;

import android.content.Context;

import com.aoinc.w2_d4_c_mvp_sqlite.model.Shoe;

import java.util.List;

public interface ShoeShopPresenterContract {
    public interface ShoeShopPresenter {
        void getAllShoes();
        void insertShoe(Shoe newShoe);
        void deleteShoe(Shoe deleteShoe);
    }

    interface ShoeShopView {
        void displayShoes(List<Shoe> allShoes);
        void displayError(String errorMessage);
        void displaySuccess(String successMessage);
        Context getContext();
    }
}
