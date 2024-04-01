//
// Created by jay on 7/20/21.
//

#ifndef FUCHSPERMISSION_H
#define FUCHSPERMISSION_H

#include <string>
#include "FuchsDefine.h"
#include "FuchsKey.h"
#include "FuchsRegistryType.h"

using std::string;

namespace fuchs {
    struct permission {

        //Variables
    private:
        string str{};


        //Constructors
    public:

        permission() = default;

        explicit permission(const key& key, const Type& type) {
            this->str = "papierfuchs." + getNameFromType(type) + "." + key.getSpaceName() + "." + key.getKey();
        }

        //Functions
    public:
        nd permission fromKey(key& key, Type& type) const;
        nd string asString() const;
    };
}

#endif //FUCHSPERMISSION_H

