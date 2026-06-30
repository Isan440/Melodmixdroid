package me.magnum.melonds.domain.model

data class RendererConfiguration(
    val renderer: VideoRenderer,
    val videoFiltering: VideoFiltering,
    val threadedRendering: Boolean,
    private val internalResolutionScaling: Int,

    val hdTextureEnabled: Boolean,
    val replaceTextureEnabled: Boolean,
    val dumpTextureEnabled: Boolean,
    val autoDumpTextureEnabled: Boolean,
) {

    val resolutionScaling get() = when (renderer) {
        VideoRenderer.SOFTWARE -> 1
        VideoRenderer.OPENGL -> internalResolutionScaling
        VideoRenderer.COMPUTE -> internalResolutionScaling
    }
}
