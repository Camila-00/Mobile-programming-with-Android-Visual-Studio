package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView pergunta;
    RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4;
    int respostaCerta = R.id.rbResposta1;
    RadioGroup rgRespostas;
    int pontos = 0;

    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("Para comparar cadeias de caracteres na linguagem C utiliza-se a seguinte função/operador: ?", R.id.rbResposta1, "strcmp", "==", "strcpy", "="));
            add(new Questao("Na linguagem C, as strings “%d”, “%f” e “%s” estão usualmente associadas ao uso da função:", R.id.rbResposta2, "getch", "printf", "main", "void"));
            add(new Questao("Assinale a alternativa que percorre o intervalo do índice 0 até o 32 na ordem não decrescente:", R.id.rbResposta3, "for(i = 32; i > 0; i--)", "for(i = 32; i > -1; i--)", "for(i = 0; i < 33; i++)", "for(i = 0; i < 32; i++)"));
            add(new Questao("Assinale a alternativa que apresenta CORRETAMENTE a forma de se declarar uma função na linguagem C", R.id.rbResposta4, "void SOMA(float a, integer b)", "void SOMA(void a, int b)", "SOMA(integer a, int b)", "void SOMA(float a, int b)"));
        }
    };

    private void carregarQuestao(){
        if(questoes.size() > 0) {
            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            rbResposta4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            rgRespostas.setSelected(false);
        }
        else{ //acabaram as questÃµes
            Intent intent = new Intent(this, RespostaActivity.class);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();

        pergunta = (TextView)findViewById(R.id.pergunta);
        rbResposta1 = (RadioButton)findViewById(R.id.rbResposta1);
        rbResposta2 = (RadioButton)findViewById(R.id.rbResposta2);
        rbResposta3 = (RadioButton)findViewById(R.id.rbResposta3);
        rbResposta4 = (RadioButton)findViewById(R.id.rbResposta4);
        rgRespostas = (RadioGroup)findViewById(R.id.rgRespostas);
        carregarQuestao();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        carregarQuestao();
    }

    public void btnResponderOnClick(View v){
        RadioButton rb = (RadioButton)findViewById(rgRespostas.getCheckedRadioButtonId());
        Intent intent = new Intent(this, RespostaActivity.class);
        if(rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
            intent.putExtra("acertou", true);
            pontos++;
        }
        else intent.putExtra("acertou", false);
        intent.putExtra("pontos", pontos);
        startActivity(intent);
        rb.setChecked(false);
    }
}
