package me.magnum.melonds.ui.emulator.model

import me.magnum.melonds.domain.model.VideoFiltering

data class RuntimeRendererConfiguration(
    val videoFiltering: VideoFiltering,
    val resolutionScaling: Int,
    val hdTextureEnabled: Boolean,
    val replaceTextureEnabled: Boolean,
    val dumpTextureEnabled: Boolean,
    val autoDumpTextureEnabled: Boolean,
)
