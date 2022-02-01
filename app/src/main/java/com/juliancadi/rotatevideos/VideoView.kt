package com.juliancadi.rotatevideos

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.TextureView
import android.view.ViewGroup
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

@SuppressLint("ViewConstructor")
class VideoView(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val textureView: TextureView,
) : PlayerView(context, attributeSet, defStyleAttr) {

    init {

        apply {
            layoutParams = ViewGroup.LayoutParams(
                600,
                600
            )

            x = 0f
            y = 0f
        }

        player = SimpleExoPlayer.Builder(context).build()

        setUpPlayer()
    }

    private fun setUpPlayer() {
        val mediaItem = MediaItem.Builder()
            .setUri(Uri.parse("asset:///video.mp4"))
            .build()

        val mediaSourceFactory = ProgressiveMediaSource.Factory(
            DefaultDataSourceFactory(
                context,
                "rotated_video_view"
            )
        )
        val mediaSource = mediaSourceFactory.createMediaSource(mediaItem)

        (player as? SimpleExoPlayer)?.apply {
            setMediaSource(mediaSource)
            setVideoTextureView(textureView)
        }

        player?.apply {
            prepare()
            repeatMode = Player.REPEAT_MODE_ALL
            playWhenReady = true
        }
    }
}