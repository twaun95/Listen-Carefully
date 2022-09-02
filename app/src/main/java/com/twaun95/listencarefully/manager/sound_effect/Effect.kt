package com.twaun95.listencarefully.manager.sound_effect

import com.twaun95.listencarefully.R

enum class Effect(val resourceId : Int){
    SPLASH(R.raw.sound_splash),
    CORRECT(R.raw.sound_answer_correct),
    WRONG(R.raw.sound_answer_wrong),
    CORRECT_AFTER_WRONG(R.raw.sound_answer_try),
    BUTTON_SOUND(R.raw.loud_sound_button_click),
    BUTTON_SOUND_RECORD(R.raw.sound_button_click),
    BUTTON_ELSE(R.raw.the_other_buttons),
    CARD(R.raw.cards),
    LIFE_COUNT(R.raw.life_cut),
    TRAINING_START(R.raw.training_starts),
}