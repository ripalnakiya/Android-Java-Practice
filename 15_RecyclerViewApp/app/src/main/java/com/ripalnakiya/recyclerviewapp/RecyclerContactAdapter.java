package com.ripalnakiya.recyclerviewapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactModel> arrayContacts = new ArrayList<>();
    int lastPosition = -1;

    RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrayContacts) {
        this.context = context;
        this.arrayContacts = arrayContacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel model = (ContactModel) arrayContacts.get(position);

        holder.imageView.setImageResource(arrayContacts.get(position).image);
        holder.textName.setText(arrayContacts.get(position).name);
        holder.textNumber.setText(arrayContacts.get(position).number);

        // Click on a Row to Update its values
        holder.oneRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_upadate_layout);

                TextView textTitle = dialog.findViewById(R.id.textTitle);
                EditText editName = dialog.findViewById(R.id.editName);
                EditText editNumber = dialog.findViewById(R.id.editNumber);
                Button actionButton = dialog.findViewById(R.id.actionButton);

                // Keep the Original contact details in Edit text
                textTitle.setText("Update Contact");
                editName.setText((arrayContacts.get(position)).name);
                editNumber.setText((arrayContacts.get(position)).number);
                actionButton.setText("Update");

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", number = "";

                        if (!(editName.getText().toString().equals("")) && !(editNumber.getText().toString().equals(""))) {
                            // Make changes if details are filled properly
                            name = editName.getText().toString();
                            number = editNumber.getText().toString();

                            arrayContacts.set(position, new ContactModel(R.drawable.person, name, number));
                            notifyItemChanged(position);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Please fill all details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        // Long click on a row to delete its values
        holder.oneRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setIcon(R.drawable.delete);
                alertDialog.setTitle("Delete Contact");
                alertDialog.setMessage("Are you sure you want to delete");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrayContacts.remove(position);
                        notifyItemRemoved(position);
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
                return true;
            }
        });

        // This sets a listener on each Button View
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, arrayContacts.get(position).name, Toast.LENGTH_SHORT).show();
            }
        });

        // This sets Animation to each item view
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return arrayContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textName;
        TextView textNumber;
        Button button;
        LinearLayout oneRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textName = itemView.findViewById(R.id.textName);
            textNumber = itemView.findViewById(R.id.textNumber);
            button = itemView.findViewById(R.id.button);
            oneRow = itemView.findViewById(R.id.oneRow);
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(slideIn);
            lastPosition = position;
        }
    }
}
