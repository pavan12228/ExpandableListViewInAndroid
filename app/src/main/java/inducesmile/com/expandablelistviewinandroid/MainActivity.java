package inducesmile.com.expandablelistviewinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ActionBarActivity {

    private ExpandableListView expandableListView;
    private List<String>parentHeaderInformation;
    private List<String> stringList;
     private static final String Root_url="http://api.androidhive.info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stringList=new ArrayList<String>();
        parentHeaderInformation = new ArrayList<String>();
        parentHeaderInformation.add("Movies");
        parentHeaderInformation.add("movies");
        parentHeaderInformation.add("movies");
         insertitems();
        HashMap<String, List<String>> allChildItems = returnGroupedChildItems();
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);
        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(getApplicationContext(), parentHeaderInformation, allChildItems);

        expandableListView.setAdapter(expandableListViewAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                     if(childPosition==0){
                         startActivity(new Intent(getApplicationContext(),ChildHome.class));
                     }




                return false;
            }
        });
    }

    private void insertitems() {

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Root_url).build();

        DetailsAPI api = adapter.create(DetailsAPI.class);
              api.Mymeth(new Callback<JsonArray>() {
                  @Override
                  public void success(JsonArray jsonElements, Response response) {


                      for (int i = 0; i < jsonElements.size(); i++) {

                       JsonObject jsonObject= jsonElements.get(i).getAsJsonObject();
                                   String tittle= jsonObject.get("title").getAsString();
                                   stringList.add(tittle);
                      }




                  }

                  @Override
                  public void failure(RetrofitError error) {
                      Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                  }
              });




    }

    private HashMap<String, List<String>> returnGroupedChildItems(){

        HashMap<String, List<String>> childContent = new HashMap<String, List<String>>();

        childContent.put(parentHeaderInformation.get(0), stringList);
        childContent.put(parentHeaderInformation.get(1), stringList);
        childContent.put(parentHeaderInformation.get(2), stringList);
        return childContent;
    }


}
