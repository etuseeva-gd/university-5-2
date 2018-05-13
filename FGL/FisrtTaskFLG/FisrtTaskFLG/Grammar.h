#pragma once

#include <set>
#include <string>
#include <vector>

class Grammar {
public:
	void parse_params(std::vector<std::string> params);
	void set_nonterminal_symbols(std::set<std::string> nonterminal_symbols);
	void set_terminal_symbols(std::set<std::string> terminal_symbols);
	void set_rules(std::set<std::pair<std::string, std::string>> rules);
	void set_initial_symbol(std::string initial_symbol);
	std::set<std::string> get_nonterminal_symbols();
	std::set<std::string> get_terminal_symbols();
	std::set<std::pair<std::string, std::string>> get_rules();
	std::string get_initial_symbol();
	Grammar merge(Grammar grammar);
	Grammar concat(Grammar grammar);
	Grammar iteration();
	std::string to_string();
private:
	std::set<std::string> nonterminal_symbols;
	std::set<std::string> terminal_symbols;
	std::set<std::pair<std::string, std::string>> rules;
	std::string initial_symbol;
};

