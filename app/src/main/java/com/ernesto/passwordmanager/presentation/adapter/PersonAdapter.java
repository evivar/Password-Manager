package com.ernesto.passwordmanager.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ernesto.passwordmanager.R;
import com.ernesto.passwordmanager.domain.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder>{

    private List<Person> people = new ArrayList<>();

    private OnItemClickListener listener;

    private int personId;

    private Context context;

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        context = parent.getContext();
        return new PersonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        Person currentPerson = people.get(position);
        holder.textViewName.setText(currentPerson.getName());
        String imgName = currentPerson.getImage().trim().replaceAll("\\s+", "").toLowerCase().trim();
        int imgId = holder.itemView.getContext().getResources().getIdentifier(
                imgName,
                "drawable",
                context.getPackageName()
        );
        holder.imageViewUser.setImageDrawable(ContextCompat.getDrawable(holder.mContext, imgId));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public void setPeople(List<Person> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    public Person getPersonAt(int position){
        return people.get(position);
    }

    class PersonHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;

        private TextView textViewNPassword;

        private ImageView imageViewUser;

        private Context mContext;

        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            textViewName = itemView.findViewById(R.id.NameTxt_Person);
            imageViewUser = itemView.findViewById(R.id.personImg_Person);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(listener != null && pos != RecyclerView.NO_POSITION){
                        listener.onItemClick(people.get(pos));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Person person);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
