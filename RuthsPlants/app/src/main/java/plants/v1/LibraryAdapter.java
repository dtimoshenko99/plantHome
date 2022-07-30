package plants.v1;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;

import com.google.firebase.firestore.DocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryAdapter extends FirestoreRecyclerAdapter<Plant, LibraryAdapter.RecipeHolder> {

    private OnItemClickListener listener;

    public LibraryAdapter(@NonNull FirestoreRecyclerOptions<Plant> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecipeHolder holder, int position, @NonNull Plant model) {
        holder.plantName.setText(model.getRecipeName());
        holder.setRecipeImage(model.getPictureUrl());
        Timestamp ts = model.getTimestamp();
        Long timeD = ts.getSeconds();
        SimpleDateFormat formatter = new SimpleDateFormat(" HH:mm dd-MM-yyyy");
        String dateString = formatter.format(new Date(timeD * 1000L));
        holder.plantDate.setText("Date Posted:"+dateString);
    }

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_library, parent, false);
        return new LibraryAdapter.RecipeHolder(v);
    }

    class RecipeHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView plantName, plantDate;
        public ImageView plantImage;
        public RecipeHolder(@NonNull View itemView){
            super(itemView);
            plantImage = itemView.findViewById(R.id.plant_image);
            plantName = itemView.findViewById(R.id.plant_name);
            plantDate = itemView.findViewById(R.id.plant_date);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void setRecipeImage(String downloadUrl){
            Glide.with(itemView.getContext()).load(downloadUrl).centerCrop().into(plantImage);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem delete = menu.add(Menu.NONE, 1, 1,"Archive Plant");
            MenuItem update = menu.add(Menu.NONE, 2, 2, "Delete Plant");
            delete.setOnMenuItemClickListener(this);
            update.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position = getBindingAdapterPosition();
            if(position != RecyclerView.NO_POSITION && listener != null){
                switch(item.getItemId()){
                    case 1:
                        listener.deleteClick(getSnapshots().getSnapshot(position), position);
                        return true;
                    case 2:
                        listener.updateClick(getSnapshots().getSnapshot(position), position);
                        return true;

                }
            }
            return false;
        }
    }

    public interface OnItemClickListener{
        void deleteClick(DocumentSnapshot documentSnapshot,int position);
        void updateClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(LibraryAdapter.OnItemClickListener listener){
        this.listener = listener;
    }
}
