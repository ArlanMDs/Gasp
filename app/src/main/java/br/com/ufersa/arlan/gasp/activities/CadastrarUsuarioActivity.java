package br.com.ufersa.arlan.gasp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import br.com.ufersa.arlan.gasp.R;
import br.com.ufersa.arlan.gasp.beans.Usuario;
import br.com.ufersa.arlan.gasp.database.AppDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    @BindView(R.id.CADASTRAR_USUARIO_NAME)
    EditText CADASTRARUSUARIONAME;
    @BindView(R.id.CADASTAR_USUARIO_EMAIL)
    EditText CADASTARUSUARIOEMAIL;
    @BindView(R.id.CADASTRAR_USUARIO_SENHA)
    EditText CADASTRARUSUARIOSENHA;
    @BindView(R.id.CADASTRAR_USUARIO_CONFIRMAR_SENHA)
    EditText CADASTRARUSUARIOCONFIRMARSENHA;
    @BindView(R.id.CADASTRAR_USUARIO_CPF)
    EditText CADASTRARUSUARIOCPF; // esse edittext serve tanto para o cpf quanto para o cnpj. depende do spinner
    @BindView(R.id.CADASTRAR_USUARIO_CPF_TEXTVIEW)
    TextView CADASTRARUSUARIOCPFTEXTVIEW;
    @BindView(R.id.CADASTRAR_USUARIO_SPINNER)
    Spinner CADASTRARUSUARIOSPINNER;

    private AppDatabase appDatabase;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        ButterKnife.bind(this);
        //prepara o bd
        appDatabase = AppDatabase.getInstance(CadastrarUsuarioActivity.this);
        usuario = new Usuario();

    }

    @OnClick({R.id.CADASTRAR_USUARIO_CONFIRMAR, R.id.CADASTRAR_USUARIO_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.CADASTRAR_USUARIO_CONFIRMAR:

                if(validaDados()) {
                    // Os dados estão ok. pronto para criar o objeto e inserir no banco

                    if (CADASTRARUSUARIOSPINNER.getSelectedItemPosition() == 0) // é cliente
                        usuario.setCpf(String.valueOf(CADASTRARUSUARIOCPF.getText()));

                    else // insere como prestador
                        usuario.setCnpj(String.valueOf(CADASTRARUSUARIOCPF.getText()));

                    usuario.setEmail(String.valueOf(CADASTARUSUARIOEMAIL.getText()));
                    usuario.setNome(String.valueOf(CADASTRARUSUARIONAME.getText()));
                    usuario.setSenha(String.valueOf(CADASTRARUSUARIOSENHA.getText()));

                    new AsyncInsert().execute();
                }

                break;
            case R.id.CADASTRAR_USUARIO_VOLTAR:

                finish();

                break;
        }
    }

    public boolean validaDados(){
        if(CADASTRARUSUARIONAME.getText().length() < 5){
            Toast.makeText(this, "O nome deve conter ao menos 5 caracteres", Toast.LENGTH_SHORT).show();
            return false;

        }else if(CADASTRARUSUARIOSENHA.getText().length() < 5) {
            Toast.makeText(this, "A senha deve conter ao menos 5 dígitos", Toast.LENGTH_SHORT).show();
            return false;

        }else if(! CADASTRARUSUARIOSENHA.getText().toString().equals(CADASTRARUSUARIOCONFIRMARSENHA.getText().toString())){
            Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            return false;

        }else if(!isValidEmail(CADASTARUSUARIOEMAIL.getText().toString())){
            Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show();
            return false;

        }else if(CADASTRARUSUARIOSPINNER.getSelectedItemPosition() == 0)
            if (CADASTRARUSUARIOCPF.getText().length() != 11) {
                Toast.makeText(this, "O CPF deve conter 11 dígitos", Toast.LENGTH_SHORT).show();
                return false;
            }
        else if(CADASTRARUSUARIOSPINNER.getSelectedItemPosition() == 1)
            if (CADASTRARUSUARIOCPF.getText().length() != 14){
                Toast.makeText(this, "O CNPJ deve conter 14 dígitos", Toast.LENGTH_SHORT).show();
                return false;
            }

        return true;
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @OnItemSelected(R.id.CADASTRAR_USUARIO_SPINNER)
    public void spinnerItemSelected(Spinner spinner, int position) {
        // cliente = 0
        // prestador = 1
        if (position == 0)
            CADASTRARUSUARIOCPFTEXTVIEW.setText("CPF*");
        else if (position == 1)
            CADASTRARUSUARIOCPFTEXTVIEW.setText("CNPJ*");


    }

    //TODO resolver o problema do leak https://stackoverflow.com/questions/44309241/warning-this-asynctask-class-should-be-static-or-leaks-might-occur
    private class AsyncInsert extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // TODO checar se o cpf ou cnpj já está cadastrado

        }

        @Override
        protected Void doInBackground(Void... voids) {

            appDatabase.usuarioDao().insert(usuario);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(CadastrarUsuarioActivity.this, "Usuário cadastrado.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
