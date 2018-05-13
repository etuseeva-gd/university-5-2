#pragma once

#include <vector>
#include <string>

class Utils {
public:
	static std::vector<std::string> read_file(std::string path);
	static void write_to_file(std::string path, std::string data);
	static std::string system_string_to_string(System::String^ string);
};

