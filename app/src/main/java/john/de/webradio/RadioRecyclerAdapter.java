package john.de.webradio;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RadioRecyclerAdapter extends
        RecyclerView.Adapter<RadioRecyclerAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView radioName;
        public ImageView radioImage;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            radioName = itemView.findViewById(R.id.radio_name_ctv);
            radioImage = itemView.findViewById(R.id.radio_image_ctv);
        }
    }

    private List<RadioChannel> radioChannels;
    private Context context;

    public RadioRecyclerAdapter(Context context, ArrayList<RadioChannel> radioChannels) {
        this.radioChannels = radioChannels;
        this.context = context;
    }

    @Override
    public RadioRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
//        View contactView = inflater.inflate(R.layout.item_channel_recycler, parent, false);
        View channelView = inflater.inflate(R.layout.item_channel_cardview, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(channelView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RadioRecyclerAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        RadioChannel channel = radioChannels.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.radioName;
        textView.setText(channel.getName());

        ImageView imageView = viewHolder.radioImage;
        imageView.setImageResource(channel.getImage());

        viewHolder.itemView.setOnClickListener(v -> showStreamActivity(position));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return radioChannels.size();
    }

    private void showStreamActivity(int position) {
        Intent intent = new Intent(context.getApplicationContext(), ChannelActivity.class);
        intent.putExtra("POSITION", position);
        context.startActivity(intent);
    }

}
