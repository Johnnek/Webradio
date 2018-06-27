package john.de.webradio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RadioAdapter extends ArrayAdapter<String> {

    private static class ViewHolder {
        TextView radioName;
        ImageView radioImage;
    }

    public RadioAdapter(Context context, ArrayList<String> list) {
        super(context, R.layout.item_channel, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String radioName = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_channel, parent, false);
            viewHolder.radioName = convertView.findViewById(R.id.radio_name);
            viewHolder.radioImage = convertView.findViewById(R.id.radio_image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.radioName.setText(radioName);
        viewHolder.radioImage.setImageResource(RadioStation.getRadioImage(position));

        return convertView;
    }
}