package plants.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView library = findViewById(R.id.libraryButton);
        ImageView archive = findViewById(R.id.archiveButton);

        library.setOnClickListener(view -> {
            Intent library1 = new Intent(MainMenuActivity.this, LibraryActivity.class);
            startActivity(library1);
        });

        archive.setOnClickListener(view -> {
            Intent archive1 = new Intent(MainMenuActivity.this, ArchiveActivity.class);
            startActivity(archive1);
        });
    }
}