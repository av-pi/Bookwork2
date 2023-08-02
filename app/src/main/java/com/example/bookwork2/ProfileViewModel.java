package com.example.bookwork2;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class ProfileViewModel extends AndroidViewModel {

    private BookRepository repository;
    private MutableLiveData<Boolean> _isLoggedIn = new MutableLiveData<>();
    private LiveData<Boolean> isLoggedIn = _isLoggedIn;

    private MutableLiveData<String> _name = new MutableLiveData<>();
    private LiveData<String> name = _name;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        this.repository = new BookRepository(application);
    }

    private void setUI(GoogleSignInAccount account) {
        if (account == null) {
            //binding.btnSignIn.setVisibility(View.VISIBLE);
            _isLoggedIn.setValue(false);
        } else {
            //binding.btnSignIn.setVisibility(View.GONE);
            //Toast.makeText(this, "Signed in as " + account.getEmail(), Toast.LENGTH_LONG).show();
            _isLoggedIn.setValue(true);

        }
    }

    public void setUI() {

    }

    public LiveData<String> getName() {
        return name;
    }

    public void setName(String name) {
        _name.setValue(name);
    }

    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        _isLoggedIn.setValue(isLoggedIn);
    }
}
