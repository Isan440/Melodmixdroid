package me.magnum.melonds.impl

import android.content.Context
import androidx.documentfile.provider.DocumentFile
import android.net.Uri
import me.magnum.melonds.domain.repositories.SettingsRepository

class DataFolderManager(
    private val context: Context,
    private val settingsRepository: SettingsRepository,
) {

    fun root(): DocumentFile? {
        val uri = settingsRepository.getDataFolder() ?: return null
        return DocumentFile.fromTreeUri(context, uri)
    }

    fun getFile(
        name: String,
        mime: String = "application/octet-stream"
    ): DocumentFile? {

        val root = root() ?: return null

        return root.findFile(name)
            ?: root.createFile(mime, name)
    }

    fun getFolder(name: String): DocumentFile? {

        val root = root() ?: return null

        return root.findFile(name)
            ?: root.createDirectory(name)
    }
}
