package windupurnomo.com.demoapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CollapsingToolbarLayout ctb;
    private int mutedColor;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    /* Top toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* Bottom toolbar. */
//        Toolbar bottomToolbar = (Toolbar) findViewById(R.id.bottom_toolbar);
//        bottomToolbar.inflateMenu(R.menu.menu_bottom);

        /* Create and customize RecyclerView. */
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Add 8 cards
        MyAdapter adapter = new MyAdapter(new String[8]);
        recyclerView.setAdapter(adapter);

        /* Collapsing toolbar */
        ctb = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        /* Define the image */
        ImageView image = (ImageView) findViewById(R.id.image);
        /* Decode bitmap from the image */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        /* Generate palette from the image bitmap */
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                mutedColor = palette.getMutedColor(R.attr.colorPrimaryDark);
       				/* Set toolbar color from the palette */
                ctb.setContentScrimColor(mutedColor);
            }
        });

        /* NavigationView */
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        // On click of menu icon on toolbar
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // On click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // Set item in checked state
                menuItem.setChecked(true);

            //TODO: handle navigation

            //Closing drawer on item click
                drawerLayout.closeDrawers();
                return true;
            }
        });

        /* Floating Action Button. */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Do something awesome?",
                        Snackbar.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                Toast.makeText(this, "You open settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_copy:
                Toast.makeText(this, "You will copy something", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_cut:
                Toast.makeText(this, "You will cut something", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_voice:
                Toast.makeText(this, "You will record voice", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
