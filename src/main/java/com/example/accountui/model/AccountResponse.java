package com.example.accountui.model;

import java.util.List;

public class AccountResponse {
    private List<Account> data;

    public List<Account> getData() {
        return data;
    }

    public void setData(List<Account> data) {
        this.data = data;
    }
}