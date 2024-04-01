//
// Created by jay on 7/20/21.
//

#include "headers/FuchsPermission.h"

using namespace fuchs;

string permission::asString() const {
    return this->str;
}

permission permission::fromKey(key& key, Type& type) const {
    return permission(key, type);
}
