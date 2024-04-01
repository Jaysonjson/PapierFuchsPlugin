//
// Created by jay on 7/20/21.
//

#include "headers/FuchsRegistryType.h"
#include <bitset>
#include <sstream>

std::string getBinaryFromType(Type type) {
    std::bitset<32> bit{(unsigned long long ) type};
    return bit.to_string();
}

std::string getNameFromType(Type type) {
    std::stringstream stream(getBinaryFromType(type));
    std::string output;
    while(stream.good())
    {
        std::bitset<8> bits;
        stream >> bits;
        char c = char(bits.to_ulong());
        if(c != *"\0") {
            output += c;
        }
    }
    return output;
}

std::string RegistryType::getName() {
    return this->name;
}
