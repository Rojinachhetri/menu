package com.example.menus_application;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button contextButton, popupButton, alertButton, btnCustom, btnok, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        contextButton = findViewById(R.id.contextbtn);
        registerForContextMenu(contextButton);

        popupButton = findViewById(R.id.popupbtn);
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, popupButton);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.action_search) {
                            Toast.makeText(MainActivity.this, "Search selected", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (id == R.id.action_save) {
                            Toast.makeText(MainActivity.this, "Save selected", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (id == R.id.action_edit) {
                            Toast.makeText(MainActivity.this, "Edit selected", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (id == R.id.action_delete) {
                            Toast.makeText(MainActivity.this, "Delete selected", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        alertButton = findViewById(R.id.alertbtn); // Initialize alertButton
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                AlertDialog alertDialog = builder.create();
                builder.setTitle("Are you sure?");
                builder.setMessage("Do you want to continue click OK otherwise cancel");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.cancel();
                    }
                });
                builder.show();
            }
        });

        btnCustom = findViewById(R.id.alertbtn);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_layout);
                btnok = dialog.findViewById(R.id.btnpositive);
                btnCancel = dialog.findViewById(R.id.btnnegative);

                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose the colors");
        menu.add("Black");
        menu.add("Red");
        menu.add("Yellow");
        menu.add("White");
        menu.add("Green");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        if (title.equals("Black")) {
            Toast.makeText(MainActivity.this, "Black Clicked", Toast.LENGTH_SHORT).show();
        } else if (title.equals("Red")) {
            Toast.makeText(MainActivity.this, "Red Clicked", Toast.LENGTH_SHORT).show();
        } else if (title.equals("Yellow")) {
            Toast.makeText(MainActivity.this, "yellow Clicked", Toast.LENGTH_SHORT).show();
        } else if (title.equals("White")) {
            Toast.makeText(MainActivity.this, "White Clicked", Toast.LENGTH_SHORT).show();
        } else if (title.equals("Green")) {
            Toast.makeText(MainActivity.this, "Green Clicked", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, title + " Clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
