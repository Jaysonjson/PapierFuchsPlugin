//
// Created by jay on 7/20/21.
//

#include "headers/FuchsKey.h"

using namespace fuchs;

namespace fuchskey {
    static const string keyPattern{"[a-z0-9/._-]{2,31}"};
    static const string fullPattern{keyPattern + ":" + keyPattern};
    static const std::regex keyRegex{keyPattern};
    static const std::regex fullRegex{fullPattern};
}

string key::getKey() const {
    return this->keyName;
}

string key::getSpaceName() const {
    return this->spacename;
}

string key::asString() const {
    return this->spacename + ":" + this->keyName;
}

bool key::valid() const {
    return validKey() && validSpaceName();
}

bool key::validKey() const {
    return std::regex_match(this->keyName, fuchskey::keyRegex);
}

bool key::validSpaceName() const {
    return std::regex_match(this->spacename, fuchskey::keyRegex);
}

key key::fromString(const string& str) {
    if(stringIsValid(str)) {
        string fuchsKey = str;
        std::replace(fuchsKey.begin(), fuchsKey.end(), ':', ' ');
        string strings[2];
        int i = 0;
        string temp;
        std::stringstream stream(fuchsKey);
        while (stream >> temp) {
            if(i < 2) {
                strings[i] = temp;
                i++;
            }
        }
        return key{strings[1], strings[0]};
    } else {
        std::cout << "Invalid Key: " << str << " || -> fromString; RegEx: " << fuchskey::fullPattern << std::endl;
    }
    return key{};
}

bool key::stringIsValid(const string& str) {
    return std::regex_match(str, fuchskey::fullRegex);
}
