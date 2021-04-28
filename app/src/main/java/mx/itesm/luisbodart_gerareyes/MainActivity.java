package mx.itesm.luisbodart_gerareyes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
<<<<<<< HEAD
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;
=======
import android.view.View;
>>>>>>> cf6ab5a05f9b0654a5e7138c3fa4cb94cff65c10

public class MainActivity extends AppCompatActivity  implements  Handler.Callback, JuegoAdapter.onItemListener  {

    private Handler handler;
    private Adapter adapter;

<<<<<<< HEAD
=======
    private static final String TAG_FRAGMENT = "active";
    private JuegoFragment juego;
    private PeliculaFragment peli;

    @Override
>>>>>>> cf6ab5a05f9b0654a5e7138c3fa4cb94cff65c10
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        this.handler = new Handler(Looper.getMainLooper(), this);

    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        return false;
    }

    @Override
    public void onItemClick(int pos) {
        int pos = recyclerView.getChildLayoutPosition(v);
        Toast.makeText(this, data.get(pos), Toast.LENGTH_SHORT).show();
=======
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
>>>>>>> cf6ab5a05f9b0654a5e7138c3fa4cb94cff65c10
    }
}