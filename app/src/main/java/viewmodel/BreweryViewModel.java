package viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import model.Brewery;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreweryViewModel extends ViewModel {

    private final String TAG = this.getClass().getCanonicalName();
    private static final BreweryViewModel ourInstance = new BreweryViewModel();
    public MutableLiveData<List<Brewery>> breweryLiveList = new MutableLiveData<>();

    public static BreweryViewModel getInstance(){
        return ourInstance;
    }

    private BreweryViewModel(){}

    public void fetchBreweryData(){
        Call<List<Brewery>> call = RetrofitClient.getInstance().getApi().retrieveResponse();

        try{
            call.enqueue(new Callback<List<Brewery>>() {
                @Override
                public void onResponse(Call<List<Brewery>> call, Response<List<Brewery>> response) {
                    List<Brewery> breweries = response.body();
                    breweryLiveList.postValue(breweries);
                }

                @Override
                public void onFailure(Call<List<Brewery>> call, Throwable t) {
                    Log.e(TAG, "Error while fetching data" + t.getLocalizedMessage());
                }
            });
        }catch (Exception ex){
            Log.e(TAG, ex.getLocalizedMessage());
            Log.e(TAG, ex.toString());
        }
    }


}
