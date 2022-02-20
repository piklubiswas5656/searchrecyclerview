package simple.searchrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

import simple.searchrecycleview.Adapter.adapter;
import simple.searchrecycleview.Modal.modal;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapter(datastoree());
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<modal> datastoree() {
        ArrayList<modal> holder = new ArrayList<>();
        modal no1 = new modal();
        no1.setTitle("good");
        no1.setDes("i am good boy");
        holder.add(no1);

        modal no2 = new modal();
        no2.setTitle("Very good");
        no2.setDes("i am Very good boy");
        holder.add(no2);

        return holder;

    }

    //for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        MenuItem item = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) item.getActionView();

//        final SearchView searchView1 =(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newtext) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newtext) {
                adapter.getFilter().filter(newtext);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
//

}