#include "HDTexture.h"

#include <filesystem>

namespace fs = std::filesystem;

static std::string g_TexturePath;

namespace HDTexture
{
    bool Initialize(const std::string& path)
    {
        g_TexturePath = path;

        if (!fs::exists(g_TexturePath))
            fs::create_directories(g_TexturePath);

        return true;
    }

    bool HasTexture(const std::string& hash)
    {
        return fs::exists(g_TexturePath + "/" + hash + ".png");
    }

    std::string GetTexturePath(const std::string& hash)
    {
        return g_TexturePath + "/" + hash + ".png";
    }
}
