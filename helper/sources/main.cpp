#include <iostream>
#include "headers/FuchsKey.h"
#include "headers/FuchsPermission.h"
#include "headers/FuchsRegistryType.h"

int main() {
    fuchs::key key{"test_item", "zoryha"};
    fuchs::permission permission{key, Type::ITEM};
    std::cout << fuchs::key::fromString("zoryha:scythe_of_the_corrupt").asString() << std::endl;
    return 0;
}
