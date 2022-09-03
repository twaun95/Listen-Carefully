package com.twaun95.listencarefully.presentation.ui.sound

import com.twaun95.listencarefully.base.BaseViewModel
import com.twaun95.listencarefully.manager.sound.factory.ExoPlayerFactory

class SoundActivityViewModel(
    private val exoPlayerFactory: ExoPlayerFactory
) : BaseViewModel() {


    val playList = listOf<String>(
        "https://actions.google.com/sounds/v1/animals/dog_barking.ogg",
        "https://actions.google.com/sounds/v1/animals/dog_growling.ogg",
        "https://actions.google.com/sounds/v1/animals/dog_snarling.ogg"
    )


}