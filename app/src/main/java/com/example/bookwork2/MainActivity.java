package com.example.bookwork2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bookwork2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private ActivityMainBinding binding;

    private HomeViewModel homeViewModel;
    private SearchViewModel searchViewModel;

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;

    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        bottomNavigationView = binding.bottomNavView;
        fragmentManager = getSupportFragmentManager();

        bottomNavigationView.setOnItemSelectedListener(this);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.setLifecycleOwner(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutContainer, homeFragment)
                        .commit();
                return true;

            case R.id.nav_search:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutContainer, searchFragment)
                        .commit();
                return true;

            case R.id.nav_profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutContainer, profileFragment)
                        .commit();
                return true;
        }
        return false;
    }
}