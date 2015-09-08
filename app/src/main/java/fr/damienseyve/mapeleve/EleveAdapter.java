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
public class EleveAdapter extends BaseAdapter{

    protected Context context;
    protected List<Eleve> listeEleve;
    protected View itemView;
    protected TextView tvPrenomEleve;
    protected TextView tvNomEleve;
    protected TextView tvAdresseEleve;
    protected TextView tvCpEleve;
    protected TextView tvVilleEleve;
    protected TextView tvTelEleve;


    public EleveAdapter(Context _context, List<Eleve> _listeEleve)
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
        itemView = inflater.inflate(R.layout.affichage_planning_listview, null);

        // On recupere les composants de la vue
        tvPrenomEleve = (TextView)itemView.findViewById(R.id.tvPrenomEleve);
        tvNomEleve = (TextView)itemView.findViewById(R.id.tvNomEleve);
        /*tvAdresseEleve = (TextView)itemView.findViewById(R.id.tvAdresseEleve);
        tvCpEleve = (TextView)itemView.findViewById(R.id.tvCpEleve);
        tvVilleEleve = (TextView)itemView.findViewById(R.id.tvVilleEleve);
        tvTelEleve = (TextView)itemView.findViewById(R.id.tvTelEleve);*/

        // On met a jour la ListView
        Eleve e = listeEleve.get(position);
        tvPrenomEleve.setText(e.getPrenomEleve());
        tvNomEleve.setText(e.getNomEleve());
        tvAdresseEleve.setText(e.getAdresseEleve());
        tvCpEleve.setText(e.getCpEleve());
        tvVilleEleve.setText(e.getVilleEleve());
        tvTelEleve.setText(e.getTelEleve());

        return itemView;
    }
}
