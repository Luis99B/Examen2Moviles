package mx.itesm.luisbodart_gerareyes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "active";
    private JuegoFragment juego;
    private PeliculaFragment peli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        juego = JuegoFragment.newInstance("par1","par2");
        peli = new PeliculaFragment();
    }

    private void setActiveFragment(Fragment nvo) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.container, nvo, TAG_FRAGMENT);
        transaction.commit();
    }
    private void removeActiveFragment() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(TAG_FRAGMENT);
        if (fragment != null){
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
        }
    }

    public void swapToJuegoFragment(View v) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(TAG_FRAGMENT);
        removeActiveFragment();

        if(fragment != juego) {
            setActiveFragment(juego);
        }
    }

    public void swapToPeliculaFragment(View v) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(TAG_FRAGMENT);
        removeActiveFragment();

        if(fragment != peli) {
            setActiveFragment(peli);
        }
    }
}