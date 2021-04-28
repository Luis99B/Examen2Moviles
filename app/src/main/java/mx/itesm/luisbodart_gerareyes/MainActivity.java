package mx.itesm.luisbodart_gerareyes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements  Handler.Callback, JuegoAdapter.onItemListener  {

    private Handler handler;
    private Adapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}