package mx.itesm.luisbodart_gerareyes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JuegoAdapter extends RecyclerView.Adapter<JuegoAdapter.ViewHolder> {
    private List<GameObject> items;
    private onItemListener onItemListener;
    private LayoutInflater inflater;
    private Context context;


    public JuegoAdapter(List<GameObject> items, Context context, onItemListener onItemListener) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
        this.onItemListener = onItemListener;
    }

    @Override
    public int getItemCount() {
        return this.items == null ? 0 : this.items.size();
    }

    @Override
    public JuegoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = this.inflater.inflate(R.layout.juegorow, null);
        return new JuegoAdapter.ViewHolder(v, this.onItemListener);
    }

    @Override
    public void onBindViewHolder(final JuegoAdapter.ViewHolder holder, final int position) {
        holder.bindData(this.items.get(position));
    }

    public void setItems(List<GameObject> items) {
        this.items = items;
    }

    public void updateList (List<GameObject> newList) {
        if (newList != null && newList.size() > 0) {
            this.items.clear();
            this.items.addAll(newList);
            notifyDataSetChanged();
        }
    }

    public void updateJSON (String json) {
        try {
            JSONArray data = new JSONArray(json);
            List<GameObject> newList = new ArrayList<GameObject>();

            for (int i = 0; i < data.length(); i++) {
                JSONObject object = data.getJSONObject(i);
                String nombre = object.getString("nombre"),
                        anio = object.getString("anio");
                JSONArray JSONplataformas = (JSONArray) object.get("plataformas");
                String[] plataformas = new String[JSONplataformas.length()];
                for (int j=0;i<plataformas.length;i++){
                    plataformas[j] = JSONplataformas.getString(j);

                }
                newList.add(new GameObject(nombre, anio, plataformas));
            }
            updateList(newList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public GameObject getElementList (int pos) {
        if (this.items != null && pos > -1 && pos <= this.items.size() - 1) {
            return this.items.get(pos);
        } else {
            return null;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre,
                anio,
                plataformas;
        onItemListener onItemListener;

        ViewHolder(View itemView, onItemListener onItemListener) {
            super(itemView);
            this.onItemListener = onItemListener;
            this.nombre = itemView.findViewById(R.id.nombre);
            this.anio = itemView.findViewById(R.id.anio);
            this.plataformas = itemView.findViewById(R.id.plataformas);

            itemView.setOnClickListener(this);
        }

        void bindData(final GameObject item) {
            this.nombre.setText(item.getNombre());
            this.anio.setText(item.getAnio());
            this.plataformas.setText(Arrays.toString(item.getPlataformas()));;
        }

        @Override
        public void onClick(View v) {
            this.onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface onItemListener {
        void onItemClick(int pos);
    }
