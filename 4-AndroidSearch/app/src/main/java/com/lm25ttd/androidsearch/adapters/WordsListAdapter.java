package com.lm25ttd.androidsearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lm25ttd.androidsearch.R;
import com.lm25ttd.androidsearch.utils.WordFilterUtils;

import java.util.ArrayList;
import java.util.List;

public class WordsListAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<String> words;
    private List<String> filteredWords;
    private WordFilter filter;
    private WordFilterUtils filterUtils;

    public WordsListAdapter(Context context, List<String> words){
        this.context = context;
        this.words = words;
        this.filteredWords = words;
        this.filterUtils = new WordFilterUtils();

        getFilter();
    }

    @Override
    public int getCount() {
        return filteredWords.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final String word = (String) getItem(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.word_row, parent, false);
            holder = new ViewHolder();

            holder.wordContent = (TextView) convertView.findViewById(R.id.wordContent);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // bind text with view holder content view for efficient use
        holder.wordContent.setText(word);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new WordFilter();
        }

        return filter;
    }

    private class WordFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint!=null && constraint.length()>0) {
                List<String> tempList = new ArrayList<String>();
                CharSequence searchTerm = constraint.toString().toLowerCase();

                for (String word : words) {
                    CharSequence original = word.toLowerCase();

                    if (filterUtils.isWordJumbled(original, searchTerm)
                            || filterUtils.hasWordOneOrLessTypo(original, searchTerm)) {
                        tempList.add(word);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = words.size();
                filterResults.values = words;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredWords = (List<String>) results.values;
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        TextView wordContent;
    }
}
