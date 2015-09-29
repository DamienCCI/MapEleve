package fr.damienseyve.mapeleve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stagiaire on 07/09/2015.
 */
public class EleveListAdapter extends BaseAdapter{

    protected Context context;
    protected List<Eleve> listeEleve;
    protected View itemView;
    protected TextView tvPrenomEleve;
    protected TextView tvNomEleve;


    public EleveListAdapter(Context _context, List<Eleve> _listeEleve)
    {
        this.context = _context;
        this.listeEleve = _listeEleve;
        itemView = null;
    }

    @Override
    public int getCount() {
        return listeEleve.size();
    }

    @Override
    public Object getItem(int position) {
        return listeEleve.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // On recupere un inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // On inflate le fichier xml pour creer les composants de la vue
        itemView = inflater.inflate(R.layout.affichage_eleve_listview, null);

        // On recupere les composants de la vue
        tvPrenomEleve = (TextView)itemView.findViewById(R.id.tvPrenomEleveListe);
        tvNomEleve = (TextView)itemView.findViewById(R.id.tvNomEleveListe);

        // On met a jour la ListView
        Eleve e = listeEleve.get(position);
        tvPrenomEleve.setText(e.getPrenomEleve());
        tvNomEleve.setText(e.getNomEleve());

        return itemView;
    }

    public void removeAt(int position){
        listeEleve.remove(position);
        notifyDataSetChanged();
    }
}
