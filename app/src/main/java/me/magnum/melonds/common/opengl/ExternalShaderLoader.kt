package me.magnum.melonds.common.opengl

import android.content.Context
import java.io.File

object ExternalShaderLoader {

    private const val SHADER_FOLDER = "shaders"

    fun getShaderFolder(context: Context): File {
        val dir = File(context.getExternalFilesDir(null), SHADER_FOLDER)

        if (!dir.exists()) {
            dir.mkdirs()
        }
        
        return dir
}

    fun loadShader(context: Context, fileName: String): String? {
        val shaderFile = File(getShaderFolder(context), fileName)

        if (!shaderFile.exists()) {
        return null
        }

         return shaderFile.readText()
    }
}
