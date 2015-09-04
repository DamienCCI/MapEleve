package fr.damienseyve.mapeleve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PlanningAdapter extends BaseAdapter {
    protected Context context;
    protected List<Planning> listeDeRdv;
    protected View itemView;
    protected TextView tvDatePlanning;
    protected TextView tvHeurePlanning;
    protected TextView tvSummaryPlanning;
    protected TextView tvLieuxPlanning;


    public PlanningAdapter(Context _context, List<Planning> _listeDeRdv)
    {
        this.context = _context;
        this.listeDeRdv = _listeDeRdv;
        itemView = null;
    }

    @Override
    public int getCount() {
        return listeDeRdv.size();
    }

    @Override
    public Object getItem(int position) {
        return listeDeRdv.get(position);
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
        itemView = inflater.inflate(R.layout.affichage_listview_planning, null);

        // On recupere les composants de la vue
        tvDatePlanning = (TextView)itemView.findViewById(R.id.tvDatePlanning);
        tvHeurePlanning = (TextView)itemView.findViewById(R.id.tvHeurePlanning);
        tvSummaryPlanning = (TextView)itemView.findViewById(R.id.tvSummaryPlanning);
        tvLieuxPlanning = (TextView)itemView.findViewById(R.id.tvLieuxPlanning);

        // On met a jour la ListView
        Planning p = listeDeRdv.get(position);
        tvDatePlanning.setText(p.getStartString());
        tvHeurePlanning.setText(p.getHeureDebString() + " - " + p.getHeureFinString());
        tvSummaryPlanning.setText(p.getSummary());
        tvLieuxPlanning.setText(p.getLieux());

        return itemView;
    }
}
