#include "Utils.h"

#include <vector>
#include <string>
#include <iostream>
#include <fstream>

using namespace std;

vector<string> Utils::read_file(string path) {
	ifstream in_file;

	in_file.open(path);
	if (!in_file) {
		return {};
	}

	vector<string> file;
	string line;
	while (getline(in_file, line))
	{
		file.push_back(line);
	}

	in_file.close();
	return file;
}

void Utils::write_to_file(string path, string data) {
	ofstream out_file;
	out_file.open(path);
	out_file << data;
	out_file.close();
}

string Utils::system_string_to_string(System::String^ string) {
	using namespace System::Runtime::InteropServices;
	char *v = (char*)(Marshal::StringToHGlobalAnsi(string)).ToPointer();
	std::string result = std::string(v);
	Marshal::FreeHGlobal(System::IntPtr((void*)v));
	return result;
}
