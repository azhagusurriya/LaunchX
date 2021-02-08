package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.launchx.OnBrewerylistClickListener;
import com.example.launchx.R;

import java.util.ArrayList;

import model.Brewery;

import static android.content.ContentValues.TAG;

public class BrewerlistAdapter extends RecyclerView.Adapter<BrewerlistAdapter.BrewerlistViewHolder> {
//    private final String TAG = this.getClass().getCanonicalName();

    private Context context;
    private ArrayList<Brewery> breweries;
    private OnBrewerylistClickListener clickListener;

    public BrewerlistAdapter(Context context, ArrayList<Brewery> breweries, OnBrewerylistClickListener clickListener){
        this.context = context;
        this.breweries = breweries;
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public BrewerlistAdapter.BrewerlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclelist_item, parent, false);
        return new BrewerlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrewerlistViewHolder holder, int position) {
        holder.bind(breweries.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return breweries.size();
    }


    public static class BrewerlistViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvCountry;
        TextView tvPostalCode;
        TextView tvWebsite;
        OnBrewerylistClickListener clickListener;

        public BrewerlistViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvPostalCode = itemView.findViewById(R.id.tvPostalCode);
            tvWebsite = itemView.findViewById(R.id.tvWebsite);
            

        }
        public void bind(Brewery brewery, OnBrewerylistClickListener clickListener){
            tvName.setText(brewery.getName());
            tvCountry.setText(brewery.getCountry());
            tvPostalCode.setText(brewery.getPostalCode());
            tvWebsite.setText(brewery.getWebsite());


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Item is Clicked ");
            }
        });

        }
        
    }

}
