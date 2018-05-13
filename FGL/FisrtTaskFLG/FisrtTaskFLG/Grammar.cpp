#include "Grammar.h"

#include <vector>
#include <string>
#include <utility>  
#include <map>  

using namespace std;

string update_symbol(string symbol, string postfix) {
	if (symbol == "\"\"" || symbol == "\'\'") {
		return symbol;
	}

	int sym_len = symbol.length();
	if (sym_len == 1) {
		string post = postfix == "1" ? "4" : "5";
		//string post = "";
		return "\'" + symbol + postfix + post + "\'";
	}

	char first_sym = symbol[0];
	char start_c = (first_sym == '\"') ? '\"' : '\'';
	return symbol.substr(0, sym_len - 1) + postfix + start_c;
}

bool is_all_symbols_terminal(string rule, set<string> t_sym) {
	if (rule == "\"\"" || rule == "\'\'") {
		return true;
	}

	bool all_terminal = true;
	while (rule.length() > 0) {
		char first_sym = rule[0];

		if (first_sym == '\"' || first_sym == '\'') {
			char start_c = (first_sym == '\"') ? '\"' : '\'';
			int index = rule.find(start_c, 1);

			string sym = start_c + rule.substr(1, index - 1) + start_c;
			if (t_sym.find(sym) == t_sym.end()) {
				return false;
			}

			rule = rule.substr(index + 1);
		}
		else {
			string sym = string(1, first_sym);
			if (t_sym.find(sym) == t_sym.end()) {
				return false;
			}

			rule = rule.substr(1);
		}
	}

	return true;
}

string update_rule(string rule, string postfix, set<string> t_sym) {
	string updated_rule = "";
	while (rule.length() > 0) {
		char first_sym = rule[0];

		if (first_sym == '\"' || first_sym == '\'') {
			char start_c = (first_sym == '\"') ? '\"' : '\'';
			int index = rule.find(start_c, 1);

			string sym = start_c + rule.substr(1, index - 1) + start_c;
			if (t_sym.find(sym) != t_sym.end()) {
				// то есть это терминальный символ
				updated_rule += sym;
			}
			else {
				updated_rule += update_symbol(sym, postfix);
			}

			rule = rule.substr(index + 1);
		}
		else {
			string sym = string(1, first_sym);
			if (t_sym.find(sym) != t_sym.end()) {
				// то есть это терминальный символ
				updated_rule += sym;
			}
			else {
				updated_rule += update_symbol(sym, postfix);
			}
			
			rule = rule.substr(1);
		}
	}
	return updated_rule;
}

// @todo может переписать используя regex
string concat_sym_to_one(string f_sym, string s_sym) {
	string c_sym = "";

	int f_sym_len = f_sym.length();
	if (f_sym_len == 1) {
		c_sym += f_sym;
	}
	else {
		c_sym += f_sym.substr(1, f_sym_len - 2);
	}

	int s_sym_len = s_sym.length();
	if (s_sym_len == 1) {
		c_sym += s_sym;
	}
	else {
		c_sym += s_sym.substr(1, s_sym_len - 2);
	}
	
	return "\'" + c_sym + "3\'";
}

set<string> get_merge_nonterminal_symbols(set<string> n_sym_first, set<string> n_sym_second) {
	set<string> n_sym_result;
	for (set<string>::iterator it = n_sym_first.begin(); it != n_sym_first.end(); it++) {
		string new_sym = update_symbol(*it, "1");
		n_sym_result.insert(new_sym);
	}
	for (set<string>::iterator it = n_sym_second.begin(); it != n_sym_second.end(); it++) {
		string new_sym = update_symbol(*it, "2");
		n_sym_result.insert(new_sym);
	}
	return n_sym_result;
}

set<string> get_merge_terminal_symbols(set<string> t_sym_first, set<string> t_sym_second) {
	set<string> t_sym_result;
	for (set<string>::iterator it = t_sym_first.begin(); it != t_sym_first.end(); it++) {
		t_sym_result.insert(*it);
	}
	for (set<string>::iterator it = t_sym_second.begin(); it != t_sym_second.end(); it++) {
		t_sym_result.insert(*it);
	}
	return t_sym_result;
}

void Grammar::parse_params(std::vector<std::string> params) {
	int k = 0;

	// множество нетерминальных символов
	int n_size = atoi(params[k++].c_str());
	set<string> nonterminal_symbols;
	while (k < n_size + 1) nonterminal_symbols.insert(params[k++]);
	Grammar::set_nonterminal_symbols(nonterminal_symbols);

	// множество терминальных символов
	int t_size = atoi(params[k++].c_str());
	set<string> terminal_symbols;
	while (k < n_size + t_size + 2) terminal_symbols.insert(params[k++]);
	Grammar::set_terminal_symbols(terminal_symbols);

	// множество правил
	int r_size = atoi(params[k++].c_str());
	set<pair<string, string>> rules;
	while (k < r_size + n_size + t_size + 3) {
		string dirty_rules = params[k++];
		string start_rule = "", end_rule = "";

		char first_sym = dirty_rules[0];
		if (first_sym == '\"' || first_sym == '\'') {
			char start_c = (first_sym == '\"') ? '\"' : '\'';
			int index = dirty_rules.find(start_c, 1);

			start_rule = dirty_rules.substr(0, index + 1);
			end_rule = dirty_rules.substr(index + 2);
		}
		else {
			start_rule = first_sym;
			end_rule = dirty_rules.substr(2);
		}

		rules.insert(make_pair(start_rule, end_rule));
	}
	Grammar::set_rules(rules);

	// стартовый символ
	Grammar::set_initial_symbol(params[k]);
}

void Grammar::set_nonterminal_symbols(std::set<std::string> nonterminal_symbols) {
	Grammar::nonterminal_symbols = nonterminal_symbols;
}

void Grammar::set_terminal_symbols(std::set<std::string> terminal_symbols) {
	Grammar::terminal_symbols = terminal_symbols;
}

void Grammar::set_rules(std::set<std::pair<std::string, std::string>> rules) {
	Grammar::rules = rules;
}

void Grammar::set_initial_symbol(std::string initial_symbol) {
	Grammar::initial_symbol = initial_symbol;
}

std::set<std::string> Grammar::get_nonterminal_symbols() {
	return Grammar::nonterminal_symbols;
}

std::set<std::string> Grammar::get_terminal_symbols() {
	return Grammar::terminal_symbols;
}

std::set<std::pair<std::string, std::string>> Grammar::get_rules() {
	return Grammar::rules;
}

std::string Grammar::get_initial_symbol() {
	return Grammar::initial_symbol;
}

std::string Grammar::to_string() {
	string str_grammar = "";

	// Нетерминальные символы
	set<string> n_sym = Grammar::nonterminal_symbols;
	str_grammar += std::to_string(n_sym.size()) + '\n';

	for (set<string>::iterator it = n_sym.begin(); it != n_sym.end(); it++) {
		str_grammar += *it + '\n';
	}

	// Терминальные символы
	set<string> t_sym = Grammar::terminal_symbols;
	str_grammar += std::to_string(t_sym.size()) + '\n';
	for (set<string>::iterator it = t_sym.begin(); it != t_sym.end(); it++) {
		str_grammar += *it + '\n';
	}

	// Правила
	set<pair<string, string>> r = Grammar::rules;
	str_grammar += std::to_string(r.size()) + '\n';
	for (set<pair<string, string>>::iterator it = r.begin(); it != r.end(); it++) {
		pair<string, string> rule = *it;
		string start_rule = rule.first, end_rule = rule.second;
		str_grammar += start_rule + " " + end_rule + '\n';
	}

	string i_sym = Grammar::initial_symbol;
	str_grammar += i_sym;

	return str_grammar;
}

// Обьединение
Grammar Grammar::merge(Grammar grammar) {
	// Нетерминальные символы
	set<string> n_sym_first = Grammar::nonterminal_symbols;
	set<string> n_sym_second = grammar.get_nonterminal_symbols();
	set<string> n_sym_result = get_merge_nonterminal_symbols(n_sym_first, n_sym_second);

	// Терминальные символы
	set<string> t_sym_first = Grammar::terminal_symbols;
	set<string> t_sym_second = grammar.get_terminal_symbols();
	set<string> t_sym_result = get_merge_terminal_symbols(t_sym_first, t_sym_second);

	// Продукции
	set<pair<string, string>> r_first = Grammar::rules;
	set<pair<string, string>> r_second = grammar.get_rules();
	set<pair<string, string>> r_result;
	for (set<pair<string, string>>::iterator it = r_first.begin(); it != r_first.end(); it++) {
		pair<string, string> rule = *it;
		r_result.insert(make_pair(update_symbol(rule.first, "1"), update_rule(rule.second, "1", t_sym_result)));
	}
	for (set<pair<string, string>>::iterator it = r_second.begin(); it != r_second.end(); it++) {
		pair<string, string> rule = *it;
		r_result.insert(make_pair(update_symbol(rule.first, "2"), update_rule(rule.second, "2", t_sym_result)));
	}

	// Начальный символ
	string i_sym_first = update_symbol(Grammar::initial_symbol, "1");
	string i_sym_second = update_symbol(grammar.get_initial_symbol(), "2");

	// @todo fix
	string i_sym_result = concat_sym_to_one(i_sym_first, i_sym_second);

	n_sym_result.insert(i_sym_result);

	r_result.insert(make_pair(i_sym_result, i_sym_first));
	r_result.insert(make_pair(i_sym_result, i_sym_second));

	Grammar result_grammar;
	result_grammar.set_nonterminal_symbols(n_sym_result);
	result_grammar.set_terminal_symbols(t_sym_result);
	result_grammar.set_rules(r_result);
	result_grammar.set_initial_symbol(i_sym_result);

	return result_grammar;
}

// Конкатенация
Grammar Grammar::concat(Grammar grammar) {
	// Нетерминальные символы
	set<string> n_sym_first = Grammar::nonterminal_symbols;
	set<string> n_sym_second = grammar.get_nonterminal_symbols();
	set<string> n_sym_result = get_merge_nonterminal_symbols(n_sym_first, n_sym_second);

	// Терминальные символы
	set<string> t_sym_first = Grammar::terminal_symbols;
	set<string> t_sym_second = grammar.get_terminal_symbols();
	set<string> t_sym_result = get_merge_terminal_symbols(t_sym_first, t_sym_second);

	// Начальный символ
	string i_sym_first = update_symbol(Grammar::initial_symbol, "1");
	string i_sym_second = update_symbol(grammar.get_initial_symbol(), "2");

	string i_sym_result = i_sym_first;

	// Продукции
	set<pair<string, string>> r_first = Grammar::rules;
	set<pair<string, string>> r_second = grammar.get_rules();
	set<pair<string, string>> r_result;
	for (set<pair<string, string>>::iterator it = r_first.begin(); it != r_first.end(); it++) {
		pair<string, string> rule = *it;

		if (is_all_symbols_terminal(rule.second, t_sym_result)) {
			// это терминальный символ
			r_result.insert(make_pair(update_symbol(rule.first, "1"), update_rule(rule.second, "1", t_sym_result) + i_sym_second));
		}
		else {
			r_result.insert(make_pair(update_symbol(rule.first, "1"), update_rule(rule.second, "1", t_sym_result)));
		}
	}
	for (set<pair<string, string>>::iterator it = r_second.begin(); it != r_second.end(); it++) {
		pair<string, string> rule = *it;
		r_result.insert(make_pair(update_symbol(rule.first, "2"), update_rule(rule.second, "2", t_sym_result)));
	}

	Grammar result_grammar;
	result_grammar.set_nonterminal_symbols(n_sym_result);
	result_grammar.set_terminal_symbols(t_sym_result);
	result_grammar.set_rules(r_result);
	result_grammar.set_initial_symbol(i_sym_result);

	return result_grammar;
}

// Итерация
Grammar Grammar::iteration() {
	// Нетерминальные символы
	set<string> n_sym = Grammar::nonterminal_symbols;
	set<string> n_sym_result;
	for (set<string>::iterator it = n_sym.begin(); it != n_sym.end(); it++) {
		string new_sym = update_symbol(*it, "1");
		n_sym_result.insert(new_sym);
	}

	// Терминальные символы
	set<string> t_sym = Grammar::terminal_symbols;
	set<string> t_sym_result = t_sym;

	// Начальный символ
	// Уже обновленный
	string i_sym = update_symbol(Grammar::initial_symbol, "1");

	// Продукции
	set<pair<string, string>> r = Grammar::rules;
	set<pair<string, string>> r_result;
	for (set<pair<string, string>>::iterator it = r.begin(); it != r.end(); it++) {
		pair<string, string> rule = *it;
		r_result.insert(make_pair(update_symbol(rule.first, "1"), update_rule(rule.second, "1", t_sym_result)));
		if (is_all_symbols_terminal(rule.second, t_sym_result)) {
			// это терминальный символ
			r_result.insert(make_pair(update_symbol(rule.first, "1"), update_rule(rule.second, "1", t_sym_result) + i_sym));
		}
	}

	string i_sym_result = update_symbol(i_sym, "3");
	r_result.insert(make_pair(i_sym_result, "\"\""));
	r_result.insert(make_pair(i_sym_result, i_sym));

	Grammar result_grammar;
	result_grammar.set_nonterminal_symbols(n_sym_result);
	result_grammar.set_terminal_symbols(t_sym_result);
	result_grammar.set_rules(r_result);
	result_grammar.set_initial_symbol(i_sym_result);

	return result_grammar;
}
