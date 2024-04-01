//
// Created by jay on 7/20/21.
//

#ifndef FUCHSKEY_H
#define FUCHSKEY_H

#include <iostream>
#include <regex>
#include "FuchsDefine.h"

using std::string;

namespace fuchs {

    /*
     * A FuchsKey has the max length of 63
     * Key max: 31
     * SpaceName max: 31
     * = 62 + ":" = 63
    */
    struct key {

        //Variables
    private:
        string keyName{"undefined"};
        string spacename{"undefined"};

        //Constructors
    public:
        key() = default;
        key(const char* key, const char* spacename) {
            this->keyName = key;
            this->spacename = spacename;
        }
        key(std::string& key, std::string& spacename) {
            this->keyName = key;
            this->spacename = spacename;
        }

        //Functions
    public:
        nd string getKey() const;
        nd string getSpaceName() const;
        nd string asString() const;
        nd bool valid() const;
        nd bool validKey() const;
        nd bool validSpaceName() const;
        nd static bool stringIsValid(const string& str);
        nd static key fromString(const string& str);
    };
}

#endif //FUCHSKEY_H
