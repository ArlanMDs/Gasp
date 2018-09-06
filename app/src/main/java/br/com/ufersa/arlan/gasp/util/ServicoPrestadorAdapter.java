package br.com.ufersa.arlan.gasp.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.ufersa.arlan.gasp.R;
import br.com.ufersa.arlan.gasp.beans.Servico;
import br.com.ufersa.arlan.gasp.prestador_activities.ModificarServicoPrestadorActivity;

public class ServicoPrestadorAdapter extends RecyclerView.Adapter<ServicoPrestadorAdapter.ViewHolder>{

    private final List<Servico> servicos;
    private final Context context;

    public ServicoPrestadorAdapter(Context context, List<Servico> servicos) {
        this.servicos = servicos;
        this.context = context;
    }

    @NonNull
    @Override
    public ServicoPrestadorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servico_prestador_row, parent, false);
        return new ServicoPrestadorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicoPrestadorAdapter.ViewHolder holder, final int position) {



        holder.servicoClienteNome.setText(servicos.get(position).getClienteNome());
        holder.servicoStatus.setText(servicos.get(position).getStatus());
        holder.servicoDate.setText(longToDateFormat(servicos.get(position).getDataEntrada()));
        // o listener passa o ID da amostra selecionada para a activity de detalhes
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ModificarServicoPrestadorActivity.class);
                intent.putExtra("servico", servicos.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//app deu crash, o log do erro pediu essa flag.
                context.startActivity(intent);
            }
        });
    }

    private String longToDateFormat(long longValue){

        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(longValue));

    }

    @Override
    public int getItemCount() {
        return servicos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView servicoClienteNome;
        private final TextView servicoDate;
        private final TextView servicoStatus;
        final ConstraintLayout parentLayout;

        ViewHolder(View itemView){
            super(itemView);
            servicoClienteNome = itemView.findViewById(R.id.TEXTVIEW_SERVICO_NOME_CLIENTE);
            parentLayout = itemView.findViewById(R.id.LAYOUT_SERVICO_PRESTADOR_ROW);
            servicoDate = itemView.findViewById(R.id.TEXTVIEW_SERVICO_DATE);
            servicoStatus = itemView.findViewById(R.id.TEXTVIEW_SERVICO_STATUS);

        }
    }
}
