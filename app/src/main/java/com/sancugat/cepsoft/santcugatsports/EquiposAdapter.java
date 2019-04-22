package com.sancugat.cepsoft.santcugatsports;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sancugat.cepsoft.santcugatsports.Entities.Equipo;
import com.sancugat.cepsoft.santcugatsports.Fragments.Equip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.EquipoViewHolder>{

    private Context mCtx;
    private List<Equipo> equipos;
    private View view;

    public EquiposAdapter(Context mCtx, List<Equipo> equipos) {
        this.mCtx = mCtx;
        this.equipos = equipos;
    }

    @NonNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        view = inflater.inflate(R.layout.adapter_equipos, null);
        EquipoViewHolder holder = new EquipoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoViewHolder equipoViewHolder, int i) {
        Equipo equip = equipos.get(i);
        equipoViewHolder.nombre.setText(equip.getNombre());
        equipoViewHolder.datos.setText(equip.getId_categoria_edad() + "/" + equip.getId_sexo());
        equipoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Le pasamos un equipo por parametro al fracment Equip
            }
        });
        if(EntidadManager.getEntidad().getFoto()!=null) {
            Bitmap b = loadImageFromStorage(EntidadManager.getEntidad().getFoto());
            RoundedBitmapDrawable drawable = CreateRoundedImageBitmap(b);
            equipoViewHolder.foto.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    class EquipoViewHolder extends RecyclerView.ViewHolder{
        ImageView foto;
        TextView nombre, datos;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.imageViewFot);
            nombre = itemView.findViewById(R.id.textViewNombre);
            datos = itemView.findViewById(R.id.textViewDatos);

        }
    }
    private Bitmap loadImageFromStorage(String path)
    {
        Bitmap b=null;
        try {
            File f=new File(path, "profile.jpg");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return b;

    }
    private RoundedBitmapDrawable CreateRoundedImageBitmap(Bitmap bitmap)
    {
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(), bitmap);
        roundedBitmapDrawable.setCircular(true);

        return roundedBitmapDrawable;
    }

}
