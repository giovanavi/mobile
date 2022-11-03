package com.example.consultasqx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.consultasqx.Util.ConfiguraBD;
import com.example.consultasqx.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText campoNome, campoEmail, campoSenha, campoConfSenha;
    Button botaoCadastrar, botaoVoltarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome =(EditText) findViewById(R.id.editTextNome);
        campoEmail =(EditText) findViewById(R.id.editTextE_mail);
        campoSenha =(EditText) findViewById(R.id.editTextSenha);
        campoConfSenha =(EditText) findViewById(R.id.editTextConfirmarSenha);
        botaoCadastrar =(Button) findViewById(R.id.buttonCadastrar);
        botaoVoltarCadastro =(Button) findViewById(R.id.buttonVoltarCadastro);
        //inicializar();
    }

    /*private void inicializar(){
        campoNome = findViewById(R.id.editTextNome);
        campoEmail = findViewById(R.id.editTextE_mail);
        campoSenha = findViewById(R.id.editTextSenha);
        campoConfSenha = findViewById(R.id.editTextConfirmarSenha);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);
        botaoVoltarCadastro = findViewById(R.id.buttonVoltarCadastro);
    }*/

    public void validarCampos(View v){
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();
        String confSenha = campoConfSenha.getText().toString();

        if(!nome.isEmpty()){
            if(!email.isEmpty()){
                if(!senha.isEmpty()){
                    if(!confSenha.equals(senha) || confSenha.isEmpty()){
                        Toast.makeText(this, "Preencha o campo Confirme sua senha corretamente", Toast.LENGTH_SHORT).show();
                    }else{
                        usuario = new Usuario();
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setSenha(senha);

                        cadastrarUsuario();
                    }
                }else{
                    Toast.makeText(this, "Preencha o campo Senha", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Preencha o campo E-mail", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha o campo Nome", Toast.LENGTH_SHORT).show();
        }
    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguraBD.FirebaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar o usuário", Toast.LENGTH_SHORT).show();
                }else{
                    String excecao = "";

                    try{
                        throw task.getException();
                    }catch(FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte";
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        excecao = "Digite um email válido";
                    }catch(FirebaseAuthUserCollisionException e){
                        excecao = "Esta conta já existe";
                    }catch(Exception e){
                        excecao = "Erro ao cadastrar usuário " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*public void Sign_in(View view){
        Intent intent = new Intent(this, FormSignin.class);
        startActivity(intent);
    }

    public void Sign_up(View view){
        Intent intent = new Intent(this, FormSignupPaciente.class);
        startActivity(intent);
    }*/

}