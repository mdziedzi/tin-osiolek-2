package com.marcindziedzic.osiolek2.features.showAllRemoteFiles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.marcindziedzic.osiolek2.R;

import java.util.ArrayList;

public class FileListViewAdapter extends ArrayAdapter<String[]> {

    private final int resource;
    private final ArrayList<String[]> objects;
    private Context context;

    public FileListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String[]>
            objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rowView = layoutInflater.inflate(resource, parent, false);

        TextView fileName = (TextView) rowView.findViewById(R.id.fileName);
        TextView fileSize = (TextView) rowView.findViewById(R.id.fileSize);
        TextView fileOwner = (TextView) rowView.findViewById(R.id.fileOwner);

        fileName.setText(objects.get(position)[0]);
        fileSize.setText(objects.get(position)[1]);
        fileOwner.setText(objects.get(position)[2]);

        return rowView;
    }
}
