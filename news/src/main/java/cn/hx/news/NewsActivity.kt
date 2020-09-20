package cn.hx.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.hx.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news.*
import javax.inject.Inject

class NewsActivity : BaseActivity() {

    @Inject
    lateinit var set: Set<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        text.text = set.toString()
    }
}