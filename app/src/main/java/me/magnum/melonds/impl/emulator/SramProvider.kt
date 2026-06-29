package me.magnum.melonds.impl.emulator

import android.net.Uri
import me.magnum.melonds.common.uridelegates.UriHandler
import me.magnum.melonds.domain.model.rom.Rom
import me.magnum.melonds.domain.repositories.SettingsRepository

class SramProvider(
    private val settingsRepository: SettingsRepository,
    private val uriHandler: UriHandler,
) {

    @Throws(SramLoadException::class)
    fun getSramForRom(rom: Rom): Uri {
        val rootDirUri = settingsRepository.getSaveFileDirectory(rom)

        val rootDocument = uriHandler.getUriTreeDocument(rootDirUri) ?: throw SramLoadException("Cannot create root document: $rootDirUri")
        val romDocument = uriHandler.getUriDocument(rom.uri)

        val romFileName = romDocument?.name ?: throw SramLoadException("Cannot determine SRAM file name: ${romDocument?.uri}")
        val romName = romFileName.substringBeforeLast('.')
        val sramFileName = romFileName.replaceAfterLast('.', "sav", "$romFileName.sav")

        val romFolder = rootDocument.findFile(romName)
        ?: rootDocument.createDirectory(romName)
        ?: throw SramLoadException("Cannot create ROM folder")
       
        romFolder.findFile("textures")
        ?: romFolder.createDirectory("textures")

        romFolder.findFile("replacements")
        ?: romFolder.createDirectory("replacements")

        romFolder.findFile("dumps")
        ?: romFolder.createDirectory("dumps")
       
        val sramDocument = romFolder.findFile(sramFileName)
        return if (sramDocument != null) {
            sramDocument.uri
        } else {
            val newSramUri = romFolder.createFile("application/*", sramFileName)?.uri
            if (newSramUri == null) {
                // It looks like some devices create the file just fine but return null. As a fallback, check if the SRAM file was actually created or not
                // Reference: https://www.ghisler.ch/board/viewtopic.php?p=370089#p370089
                romFolder.findFile(sramFileName)?.uri ?: throw SramLoadException("Could not create temporary SRAM file at ${rootDocument.uri}")
            } else {
                newSramUri
            }
        }
    }
}
