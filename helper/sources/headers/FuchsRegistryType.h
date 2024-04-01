//
// Created by jay on 7/20/21.
//

#ifndef CPP23_FUCHSREGISTRYTYPE_H
#define CPP23_FUCHSREGISTRYTYPE_H

#include <iostream>
#include <utility>

//OLD - ENUMS ARE NOT LIKE IN JAVA :(
enum class Type {
    ITEM = 0x6974656d
};

struct RegistryType {
    //Variables
private:
    std::string name{};

    //Constructors
public:
    explicit RegistryType(std::string name) {
        this->name = std::move(name);
    }

    //Functions
public:
    std::string getName();
};

std::string getNameFromType(Type type);
std::string getBinaryFromType(Type type);
#endif //CPP23_FUCHSREGISTRYTYPE_H
