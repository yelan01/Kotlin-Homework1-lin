package com.example.lab3

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step1 定義元件變數，並通過 findViewById 取得元件
        val edName = findViewById<EditText>(R.id.edName)
        val tvText = findViewById<TextView>(R.id.tvText)
        val btnMora = findViewById<Button>(R.id.btnMora)
        val S = findViewById<RadioButton>(R.id.btnScissor)
        val stone = findViewById<RadioButton>(R.id.btnStone)
        val paper = findViewById<RadioButton>(R.id.btnPaper)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvWinner = findViewById<TextView>(R.id.tvWinner)
        val tvMyMora = findViewById<TextView>(R.id.tvMyMora)
        val tvTargetMora = findViewById<TextView>(R.id.tvTargetMora)


        // Step2 設定 btnMora 的點擊事件
        btnMora.setOnClickListener {
            // Step3 如果 edName 為空，則顯示提示文字
            if (edName.text.isEmpty()) {
                tvText.text = "請輸入玩家姓名"
                return@setOnClickListener
            }

            val targetMora = (0..2).random()
            var 電腦出拳 = ""

            if (targetMora == 0) {
                tvTargetMora.setText("電腦出拳\n剪刀");
                電腦出拳 = "剪刀";

            } else if (targetMora == 1) {
                tvTargetMora.setText("電腦出拳\n石頭");
                電腦出拳 = "石頭";
            } else {
                tvTargetMora.setText("電腦出拳\n布");
                電腦出拳 = "布";
            }
            tvName.setText(String.format("名字\n%s", edName.text.toString()));
            if (S.isChecked()) {
                tvMyMora.setText("我方出拳\n剪刀");
            } else if (stone.isChecked()) {
                tvMyMora.setText("我方出拳\n石頭");
            } else if (paper.isChecked()) {
                tvMyMora.setText("我方出拳\n布");
            }

            if ((S.isChecked() && 電腦出拳 == "布") ||
                (stone.isChecked() && 電腦出拳 == "剪刀") ||
                (paper.isChecked() && 電腦出拳 == "石頭")
            ) {
                tvWinner.setText("勝利者\n" + edName.text.toString());
                tvText.setText("恭喜您獲勝了！！！");
            } else if ((S.isChecked() && 電腦出拳 == "石頭") ||
                (stone.isChecked() && 電腦出拳 == "布") ||
                (paper.isChecked() && 電腦出拳 == "剪刀")
            ) {
                tvWinner.setText("勝利者\n電腦");
                tvText.setText("可惜，電腦獲勝了！");
            } else {
                tvWinner.setText("勝利者\n平手");
                tvText.setText("平局，請再試一次！");
            }

        }
    }
}