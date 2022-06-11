//package com.example.login;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.navigation.NavigationBarView;
//
//public class FragmentLayout extends AppCompatActivity {
//    BottomNavigationView bottomNavigation;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment_layout);
//        bottomNavigation = findViewById(R.id.bottom_navigation);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Dashboard()).commit();
//
//        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment selectedFragment = null;
//                switch (item.getItemId()){
//                    case R.id.navigation_dashboard:
//                        selectedFragment = new DashboardFragment();
//                        break;
//                    case R.id.nav_shopping:
//                        selectedFragment = new KeranjangFragment();
//                        break;
//                    case R.id.nav_logout:
//                        selectedFragment = new LogoutFragment();
//                        break;
//                }
//
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
//                return true;
//            }
//        });
//    }
//    }
