package br.com.ufersa.arlan.gasp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ufersa.arlan.gasp.R;
import br.com.ufersa.arlan.gasp.beans.Usuario;
import br.com.ufersa.arlan.gasp.cliente_activities.ClienteMainActivity;
import br.com.ufersa.arlan.gasp.database.AppDatabase;
import br.com.ufersa.arlan.gasp.prestador_activities.PrestadorMainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.LOGIN_EMAIL)
    EditText LOGINEMAIL;
    @BindView(R.id.LOGIN_SENHA)
    EditText LOGINSENHA;
    AppDatabase appDatabase;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //prepara o bd
        appDatabase = AppDatabase.getInstance(LoginActivity.this);
        //usuario = new Usuario();
    }

    @OnClick({R.id.LOGIN_ENTRAR, R.id.LOGIN_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.LOGIN_ENTRAR:
                new AsyncRead().execute();

                break;
            case R.id.LOGIN_VOLTAR:
                finish();

                break;
        }
    }


    private class AsyncRead extends AsyncTask<Void, Void, Usuario> {
        // referência https://stackoverflow.com/questions/11833978/asynctask-pass-two-or-more-values-from-doinbackground-to-onpostexecute
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (!(LOGINEMAIL.getText().length() > 0 && LOGINSENHA.getText().length() > 0)) {
                this.cancel(true);
                Toast.makeText(LoginActivity.this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected Usuario doInBackground(Void... voids) {
            return appDatabase.usuarioDao().getUser(LOGINEMAIL.getText().toString(),LOGINSENHA.getText().toString());
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            if (usuario != null) {

                SharedPreferences.Editor editor;
                //inicializa os objetos das preferências
                SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                editor = mSharedPreference.edit();
                editor.putString("usuarioAtivoNome", usuario.getNome());
                editor.putString("usuarioAtivoEmail", usuario.getEmail());
                // o ID do usuário é o cpf ou cnpj. Cada usuário possui apenas um dos dois
                editor.putString("usuarioAtivoID", usuario.getCpf() == null ? usuario.getCnpj() : usuario.getCpf());

                editor.apply();

                // Inicia a activity específica de acordo com o tipo de usuário

                startActivity(usuario.getCpf() == null ? new Intent(LoginActivity.this, PrestadorMainActivity.class) : new Intent(LoginActivity.this, ClienteMainActivity.class));

            }
            else
                Toast.makeText(LoginActivity.this, "Nome de usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}
