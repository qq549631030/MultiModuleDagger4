package cn.hx.multimoduledagger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.hx.news.NewsActivity
import cn.hx.user.UserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_user.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
        btn_news.setOnClickListener {
            startActivity(Intent(this, NewsActivity::class.java))
        }
    }
}