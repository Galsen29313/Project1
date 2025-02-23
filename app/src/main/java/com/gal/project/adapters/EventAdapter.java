    package com.gal.project.adapters;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;
    import androidx.recyclerview.widget.RecyclerView;

    import com.gal.project.R;
    import com.gal.project.models.Event;

    import java.util.List;

    public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
        private List<Event> eventList;

        // Constructor to initialize the list
        public EventAdapter(List<Event> eventList) {
            this.eventList = eventList;
        }

        @Override
        public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Inflate the layout for each list item
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_event, parent, false);
            return new EventViewHolder(view);
        }

        @Override
        public void onBindViewHolder(EventViewHolder holder, int position) {
            Event event = eventList.get(position);

            // Bind the data to the views
            holder.tvEventName.setText(event.getName());
            holder.tvEventDate.setText(event.getDate());
            holder.tvEventTime.setText(event.getTime());
            holder.tvEventCat.setText(event.getType());
            holder.tvEventCity.setText(event.getCity());

            if(event.getJoined()==null)
                    holder.tvEventJoined.setText(1+"/"+event.getMaxJoin());
         else    holder.tvEventJoined.setText((event.getJoined().size()+1)+"/"+event.getMaxJoin());

        }

        @Override
        public int getItemCount() {
            return eventList.size();
        }

        // Update the list when filtering
        public void updateList(List<Event> newEventList) {
            eventList = newEventList;
            notifyDataSetChanged();
        }

        // ViewHolder class to hold references to the views in the layout
        public static class EventViewHolder extends RecyclerView.ViewHolder {
            TextView tvEventName, tvEventDate, tvEventTime, tvEventCat, tvEventCity, tvEventJoined;

            public EventViewHolder(View itemView) {
                super(itemView);
                tvEventName = itemView.findViewById(R.id.tvEventName);
                tvEventDate = itemView.findViewById(R.id.tvEventDate);
                tvEventTime = itemView.findViewById(R.id.tvEventTime);
                tvEventCat = itemView.findViewById(R.id.tvEventCat);
                tvEventCity = itemView.findViewById(R.id.tvEventCity);
                tvEventJoined = itemView.findViewById(R.id.tvEventJoinedNe);
            }
        }
    }
