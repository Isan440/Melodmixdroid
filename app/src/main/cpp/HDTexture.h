#pragma once

#include <string>

namespace HDTexture
{
    bool Initialize(const std::string& path);
    bool HasTexture(const std::string& hash);
    std::string GetTexturePath(const std::string& hash);
}
