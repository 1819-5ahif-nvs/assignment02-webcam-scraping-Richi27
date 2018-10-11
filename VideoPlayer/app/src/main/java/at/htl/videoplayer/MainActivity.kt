package at.htl.videoplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.R.raw
import android.net.Uri
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    lateinit var vid: VideoView
    lateinit var path: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vid = videoView
        thread { path = getVideoLink() }

    }

    fun getVideoLink():String{
        var doc: Document? = null
        var vid: Element? = null
        var source: String = ""
        try {
            doc = Jsoup.connect("https://webtv.feratel.com/webtv/?cam=5132&design=v3&c0=0&c2=1&lg=en&s=0")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").get()
            vid = doc!!.getElementById("fer_video")
            source = vid!!.select("source").first().attr("src")
        } catch (e: IOException) {

        }
        return source
    }

    fun playVideo(v: View) {

        val m = MediaController(this)
        vid.setMediaController(m)



        val u = Uri.parse(path)

        vid.setVideoURI(u)

        vid.start()

    }
}
