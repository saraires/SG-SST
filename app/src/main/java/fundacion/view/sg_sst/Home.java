package fundacion.view.sg_sst;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Home extends AppCompatActivity {

    SharedPreferences preferences;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        int rol = Integer.parseInt(preferences.getString("Rol", ""));

        switch (rol) {
            case 0: {
                fragment = new FragmentAdmin();
                showFragment();
                break;
            }
            case 1: {
                fragment = new FragmentEmployee();
                showFragment();
                break;
            }
            case 2:
                fragment = new FragmentSupervisor();
                showFragment();
                break;
            case 3:
                fragment = new FragmentPresident();
                showFragment();
                break;
        }

        showFragment();
    }

    private void showFragment() {
        Fragment fr = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_home, fr)
                .setReorderingAllowed(true).addToBackStack(null)
                .commit();
    }
}