package mx.itesm.luisbodart_gerareyes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JuegoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JuegoFragment extends Fragment implements  Handler.Callback, JuegoAdapter.onItemListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Handler handler;
    private JuegoAdapter adapter;
    RecyclerView rv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JuegoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JuegoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JuegoFragment newInstance(String param1, String param2) {
        JuegoFragment fragment = new JuegoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        this.handler = new Handler(Looper.getMainLooper(), this);
        requestThread();

        List<GameObject> list = new ArrayList<GameObject>();
        this.adapter = new JuegoAdapter(list, getContext(), this);

    }



    public void requestThread() {
        JuegoRequest req = new JuegoRequest("https://bitbucket.org/itesmguillermorivas/partial2/raw/a6640dfc9d3f47462e6b2d009ff9e5dee292f6fa/videojuegos.json", this.handler);
        req.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_juego, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recyclerV);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(this.adapter);
        return view;
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        this.adapter.updateJSON(msg.obj.toString());
        return false;
    }

    @Override
    public void onItemClick(int pos) {
        GameObject elements = this.adapter.getElementList(pos);
        //Toast.makeText(this, , Toast.LENGTH_SHORT).show();
    }
}