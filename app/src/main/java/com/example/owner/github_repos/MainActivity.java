package com.example.owner.github_repos;

import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    private String URL = "https://api.github.com/users/Square/repos";
    private RadioButton rd1;
    private RadioButton rd2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isConnected()){
            final ListView list = (ListView) findViewById(R.id.list_item);

            new asyncClass(MainActivity.this, new asyncClass.onResponse() {
                @Override
                public void onSuccess(final ArrayList<RepoDetails> RD) {
                    list.setAdapter(new adapter(MainActivity.this, RD));
                    list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                            final Dialog dialog = new Dialog(MainActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialogue_box);
                            dialog.show();
                            rd1 = (RadioButton) dialog.findViewById(R.id.Repository);
                            rd2 = (RadioButton) dialog.findViewById(R.id.owner);
                            btn = (Button) dialog.findViewById(R.id.dialog_button);

                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (rd1.isChecked()) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(RD.get(position).getRepoURL()));
                                        startActivity(intent);
                                        dialog.dismiss();
                                    } else if (rd2.isChecked()) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(RD.get(position).getOwnerUrl()));
                                        startActivity(intent);
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Please Choose one ..!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            return true;
                        }
                    });
                }
            }).execute(URL);
        }else {
            Toast.makeText(this, "No Internet Connection\ncheck your connection", Toast.LENGTH_SHORT).show();
        }


    }

}
