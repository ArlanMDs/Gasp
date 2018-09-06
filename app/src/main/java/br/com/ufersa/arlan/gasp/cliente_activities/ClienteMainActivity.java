package br.com.ufersa.arlan.gasp.cliente_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.ufersa.arlan.gasp.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClienteMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.CLIENTE_SERVICOS, R.id.CLIENTE_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.CLIENTE_SERVICOS:

                startActivity( new Intent(ClienteMainActivity.this, ListarServicosClienteActivity.class));


                break;

            case R.id.CLIENTE_VOLTAR:

                finish();
                //TODO fez logout

                break;
        }
    }
}
