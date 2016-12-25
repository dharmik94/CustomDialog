package com.demo.customdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button simpledialog_btn, simpledialoglist_btn, simpledialogcustomtheme_btn, customdialog_btn, customdialoglist_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        simpledialog_btn = (Button) findViewById(R.id.simpledialog);
        simpledialoglist_btn = (Button) findViewById(R.id.simpledialogwithlist);
        customdialog_btn = (Button) findViewById(R.id.customdialog);
        customdialoglist_btn = (Button) findViewById(R.id.customdialogwithlist);
        simpledialogcustomtheme_btn = (Button) findViewById(R.id.simpledialogwithcustomtheme);

        simpledialog_btn.setOnClickListener(this);
        simpledialoglist_btn.setOnClickListener(this);
        simpledialogcustomtheme_btn.setOnClickListener(this);
        customdialog_btn.setOnClickListener(this);
        customdialoglist_btn.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.simpledialog:
                ShowSimpleDialog(); // Call Simple Dialog Method
                break;
            case R.id.simpledialogwithlist:
                ShowSimpleDialogwithList(); // Call Simple Dialog with List Method
                break;
            case R.id.simpledialogwithcustomtheme:
                ShowSimpleDialogwithcustomtheme(); // Call Simple Dialog with Custom Theme Method
                break;
            case R.id.customdialog:
                ShowCustomDialog(); // Call Custom Dialog Method
                break;
            case R.id.customdialogwithlist:
                ShowCustomDialogwithList(); // Call Custom Dialog with List Method
                break;
        }
    }

    // Simple Dialog
    private void ShowSimpleDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Simple Dialog");
        builder.setMessage(R.string.info_description);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    // Simple Dialog with List
    private void ShowSimpleDialogwithcustomtheme() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.DialogStyle);
        builder.setTitle("Simple Dialog with Custom Theme");
        builder.setMessage(R.string.info_description);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    // Simple Dialog with Custom Theme
    private void ShowSimpleDialogwithList() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Simple Dialog with List");
        //builder.setMessage(R.string.info_description);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Cupcake");
        arrayAdapter.add("Donut");
        arrayAdapter.add("Eclair");
        arrayAdapter.add("Froyo");
        arrayAdapter.add("Gingerbread");

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(MainActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builder.show();
    }

    // Custom Dialog
    private void ShowCustomDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.DialogTheme);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.customdialog_layout, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog alertDialog = dialogBuilder.create();
        Window window = alertDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        Button ok_btn = (Button) dialogView.findViewById(R.id.button);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
            }
        });

        alertDialog.show();
    }

    // Custom Dialog with List
    private void ShowCustomDialogwithList() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.DialogTheme);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.customdialogwithlist_layout, null);
        dialogBuilder.setView(dialogView);

        final ListView listView = (ListView) dialogView.findViewById(R.id.listview);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Cupcake",
                "Donut",
                "Eclair",
                "Froyo",
                "Gingerbread"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });

        final AlertDialog alertDialog = dialogBuilder.create();
        Window window = alertDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER); // set alert dialog in center
       // window.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL); // set alert dialog in Bottom

        // Cancel Button
        Button cancel_btn = (Button) dialogView.findViewById(R.id.buttoncancellist);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
            }
        });

        alertDialog.show();
    }
}
