package network;

import java.util.List;

import model.Brewery;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://api.openbrewerydb.org/breweries/"; // ?q=me

//    "https://www.holidaylistprovider/"
    //endpoints country=ca or country=us

    //use "." to indicate that Retrofit call should the
    // base URL as it is without adding any endpoints
    @GET(".")
    Call<List<Brewery>> retrieveResponse();


//    //fetch single object
//    //use this if provider gives you only single JSON object
//    @GET(".")
//    Call<Brewery> retrieveResponse();



}
