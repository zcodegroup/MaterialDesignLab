package windupurnomo.com.demoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by windupurnomo on 08/09/15.
 */
/* Create RecylcerView Adapter. */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView title;
        public ViewHolder(View v) {
            super(v);
            view = v;
            title = (TextView) v.findViewById(R.id.card_title);
        }
    }

    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new ViewHolder(cardview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText("Card " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
