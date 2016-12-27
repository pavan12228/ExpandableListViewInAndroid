package inducesmile.com.expandablelistviewinandroid;

import com.google.gson.JsonArray;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Ravi on 27-12-2016.
 */
public interface DetailsAPI
{
    @GET("/json/movies.json")
    public void Mymeth(Callback<JsonArray> callback);
}
