package com.example.consultasqx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultasqx.Util.ConfiguraBD;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "ViewDatabase";

    private DAOUsuario dao;
    private static final String USUARIO = "Usuario";
    private String nome, cpf, email, telefone, senha;
    private String emailU, senhaU;

    private FirebaseAuth autenticacao;
    private FirebaseAuth mAuth;

    SharedPreferences sp;
    TextView campoNomeProfile;
    TextInputEditText campoNome, campoCpf, campoEmail, campoTelefone, campoSenha;

    static boolean valido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        mAuth = FirebaseAuth.getInstance();

        campoNomeProfile = findViewById(R.id.textView_NomeProfile);
        campoNome = findViewById(R.id.editTextNome);
        campoCpf = findViewById(R.id.editTextCpf);
        campoEmail = findViewById(R.id.editTextEmail);
        campoTelefone = findViewById(R.id.editTextTelefone);
        campoSenha = findViewById(R.id.editTextSenha);

        dao = new DAOUsuario();

        sp = getApplicationContext().getSharedPreferences(USUARIO, Context.MODE_PRIVATE);

        autenticacao = ConfiguraBD.FirebaseAutenticacao();

        nome = sp.getString("nome", "");
        cpf = sp.getString("cpf", "");
        email = sp.getString("email", "");
        telefone = sp.getString("telefone", "");
        senha = sp.getString("senha", "");

        campoNomeProfile.setText(nome);
        campoNome.setText(nome);
        campoCpf.setText(cpf);
        campoEmail.setText(email);
        campoTelefone.setText(telefone);
        campoSenha.setText(senha);

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    public void update(View v) {
        nome = Objects.requireNonNull(campoNome.getText()).toString();
        cpf = Objects.requireNonNull(campoCpf.getText()).toString();
        emailU = Objects.requireNonNull(campoEmail.getText()).toString();
        telefone = Objects.requireNonNull(campoTelefone.getText()).toString();
        senhaU = Objects.requireNonNull(campoSenha.getText()).toString();

        if (verEmail()){
            if (verNome() && verNums() && verTele() && (senha.length() >= 8)) {

                updateEmail();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("nome", nome);
                hashMap.put("cpf", cpf);
                hashMap.put("email", email);
                hashMap.put("telefone", telefone);
                hashMap.put("senha", senha);

                dao.update(FirebaseAuth.getInstance().getCurrentUser().getUid(), hashMap).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Atualizado", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.commit();

                    editor.putString("nome", nome);
                    editor.putString("cpf", cpf);
                    editor.putString("email", email);
                    editor.putString("telefone", telefone);
                    editor.putString("senha", senha);
                    editor.commit();

                    campoNomeProfile.setText(nome);

                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Falha em atualizar dados", Toast.LENGTH_SHORT).show();
                });

            }
        }
    }

    private void updateEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(emailU).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UserProfileActivity.this, "Email atualizado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserProfileActivity.this, "Ocorreu um erro em atualizar o email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateSenha(View view){
        senhaU = Objects.requireNonNull(campoSenha.getText()).toString();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(senhaU).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UserProfileActivity.this, "Senha atualizada com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserProfileActivity.this, "Ocorreu um erro ao atualizar a senha", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void delete(View v) {
        dao.remove(Objects.requireNonNull(autenticacao.getCurrentUser()).getUid()).addOnSuccessListener(suc -> {

            Toast.makeText(this, "Conta removida", Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();

            campoNomeProfile.setText("");
            campoNome.setText("");
            campoCpf.setText("");
            campoEmail.setText("");
            campoTelefone.setText("");
            campoSenha.setText("");

            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("Usuario")
                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                    .setValue(null)
                    .addOnSuccessListener(new OnSuccessListener<Void>(){
                        @Override
                        public void onSuccess(Void unused) {
                            FirebaseAuth.getInstance().getCurrentUser().delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Log.d(TAG, "Deletado com sucesso");
                                                Intent intent= new Intent(UserProfileActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                            }else{
                                                Log.d(TAG, "Erro em deletar");
                                            }
                                        }
                                    });
                        }
                    });

            finish();

        }).addOnFailureListener(er -> {
            Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Falha em deletar conta", Toast.LENGTH_SHORT).show();
        });

    }

    private boolean verEmail() {

        email = Objects.requireNonNull(campoEmail.getText()).toString();
        senha = Objects.requireNonNull(campoSenha.getText()).toString();

        /*if (FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(email)) {
            valido = true;
        } else {*/
            if(conteudoEmail()){
                valido = true;
            }else{
                Toast.makeText(UserProfileActivity.this, "Email inválido", Toast.LENGTH_SHORT).show();
                valido = false;
            }
        //}

        return valido;
    }

    private boolean conteudoEmail(){
        int numTiposContas = 0;

        if(email.contains("@gmail.com")){
            ++numTiposContas;
        }

        if(email.contains("@alu.ufc.br")){
            ++numTiposContas;
        }

        if(email.contains("@hotmail.com")){
            ++numTiposContas;
        }

        if(numTiposContas > 1 || email.contains(" ")){
            return false;
        }else{
            StringBuilder ultimasLetras = new StringBuilder();

            for(int i = email.length() - 12; i < email.length(); ++i){
                char ch = email.charAt(i);
                ultimasLetras.append(ch);
            }

            return ultimasLetras.toString().contains("@gmail.com") || ultimasLetras.toString().contains("@alu.ufc.br") || ultimasLetras.toString().contains("@hotmail.com");
        }
    }

    private boolean verTele() {
        if (telefone.length() != 11) {
            return false;
        } else {
            for (int i = 0; i < telefone.length(); ++i) {
                char ch = telefone.charAt(i);

                if ((i == 0 && ch != '8') || !(ch >= '0' && ch <= '9')) {
                    Toast.makeText(this, "O campo Telefone deve estar de acordo com o modelo DDXXXXXXXXX", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    private boolean verNums() {
        if (cpf.length() != 14) {
            return false;
        } else {
            for (int i = 0; i < cpf.length(); ++i) {
                char ch = cpf.charAt(i);

                if (((i == 3 || i == 7) && ch != '.') || (i == 11 && ch != '-')) {
                    Toast.makeText(this, "Preencha o campo CPF corretamente no formato XXX.XXX.XXX-XX", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (!((ch >= '0' && ch <= '9') || ch == '.' || ch == '-')) {
                    Toast.makeText(this, "Preencha o campo CPF corretamente com números, \".\" e \"-\"", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    private boolean verNome() {
        for(int i = 0; i < nome.length(); ++i){
            char ch = nome.charAt(i);

            if(i < nome.length()-1){
                char ch1 = nome.charAt(i + 1);

                if (ch == ' ' && ch1 == ' ') {
                    return false;
                }
            }
        }

        if (nome.length() == 0) {
            return false;
        } else {
            for (int i = 0; i < nome.length(); ++i) {
                char ch = nome.charAt(i);

                if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= 'á' && ch <= 'ú') || (ch >= 'Á' && ch <= 'Ú') || ch == 'ã' || ch == 'õ' || ch == ' ') || (i == 0 && ch == ' ')) {
                    Toast.makeText(this, "Preencha o campo Nome apropriadamente (Verifique espaços indesejados)", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    public void voltarUserProfile(View v) {
        finish();
    }
}