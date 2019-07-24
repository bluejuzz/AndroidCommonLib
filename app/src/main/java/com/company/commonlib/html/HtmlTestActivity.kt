package com.company.commonlib.html

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.company.commonlib.R
import kotlinx.android.synthetic.main.activity_html_test.*

class HtmlTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html_test)
        var tt :String = ""
        tt.trim()
        html_tv_test.text = Html.fromHtml("<html>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<h4>一个无序列表：</h4>\n" +
                "<ul>\n" +
                "  <li>咖啡</li>\n" +
                "  <li>茶</li>\n" +
                "  <li>牛奶</li>\n" +
                "</ul>\n" +
                "<ol>" +
                "  <li><h4>  咖啡</h4></li>" +
                "  <li><h4>  牛奶</h4></li>" +
                "  <li><h4>  茶</h4></li>" +
                "</ol>" +
                "\n" +
                "</body>\n" +
                "</html>")
    }

    companion object {
        fun starter(context: Context) {
            context.startActivity(Intent(context, HtmlTestActivity::class.java))
        }
    }

}
