package br.com.ufersa.arlan.gasp.prestador_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.ufersa.arlan.gasp.R;
import br.com.ufersa.arlan.gasp.beans.Servico;

public class ModificarServicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_servico);

        initiate();
        getIncomingIntent();
    }

    private void initiate() {
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("servico")) {

            Servico servico = getIntent().getParcelableExtra("servico");
            updateUI(servico);
        }
    }

    private void updateUI(Servico servico) {

    }
}
