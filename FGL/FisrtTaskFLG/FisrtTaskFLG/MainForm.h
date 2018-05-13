#pragma once

#include "Grammar.h"
#include "Utils.h"

namespace FisrtTaskFLG {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for MainForm
	/// </summary>
	public ref class MainForm : public System::Windows::Forms::Form
	{
	public:
		MainForm(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~MainForm()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::GroupBox^  groupBoxInput;
	private: System::Windows::Forms::TextBox^  textBoxFirstFile;
	protected:

	private: System::Windows::Forms::Button^  buttonSelectSecondFile;
	private: System::Windows::Forms::Button^  buttonSelectFirstFile;
	private: System::Windows::Forms::Label^  labelSecondGram;
	private: System::Windows::Forms::Label^  labelFirstGram;
	private: System::Windows::Forms::GroupBox^  groupBoxOuput;
	private: System::Windows::Forms::Button^  buttonExit;
	private: System::Windows::Forms::Button^  buttonStart;
	private: System::Windows::Forms::TextBox^  textBoxSecondFile;
	private: System::Windows::Forms::TextBox^  textBoxFolder;
	private: System::Windows::Forms::Button^  buttonSelectFolder;
	private: System::Windows::Forms::TextBox^  textBoxStatus;
	private: System::Windows::Forms::GroupBox^  groupBoxChecks;
	private: System::Windows::Forms::CheckBox^  checkBoxIter;
	private: System::Windows::Forms::CheckBox^  checkBoxConcat;
	private: System::Windows::Forms::CheckBox^  checkBoxMerge;
	private: System::Windows::Forms::OpenFileDialog^  openFileDialog;
	private: System::Windows::Forms::FolderBrowserDialog^  folderBrowserDialog;

	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->groupBoxInput = (gcnew System::Windows::Forms::GroupBox());
			this->textBoxSecondFile = (gcnew System::Windows::Forms::TextBox());
			this->textBoxFirstFile = (gcnew System::Windows::Forms::TextBox());
			this->buttonSelectSecondFile = (gcnew System::Windows::Forms::Button());
			this->buttonSelectFirstFile = (gcnew System::Windows::Forms::Button());
			this->labelSecondGram = (gcnew System::Windows::Forms::Label());
			this->labelFirstGram = (gcnew System::Windows::Forms::Label());
			this->groupBoxOuput = (gcnew System::Windows::Forms::GroupBox());
			this->textBoxFolder = (gcnew System::Windows::Forms::TextBox());
			this->buttonSelectFolder = (gcnew System::Windows::Forms::Button());
			this->buttonExit = (gcnew System::Windows::Forms::Button());
			this->buttonStart = (gcnew System::Windows::Forms::Button());
			this->textBoxStatus = (gcnew System::Windows::Forms::TextBox());
			this->groupBoxChecks = (gcnew System::Windows::Forms::GroupBox());
			this->checkBoxIter = (gcnew System::Windows::Forms::CheckBox());
			this->checkBoxConcat = (gcnew System::Windows::Forms::CheckBox());
			this->checkBoxMerge = (gcnew System::Windows::Forms::CheckBox());
			this->openFileDialog = (gcnew System::Windows::Forms::OpenFileDialog());
			this->folderBrowserDialog = (gcnew System::Windows::Forms::FolderBrowserDialog());
			this->groupBoxInput->SuspendLayout();
			this->groupBoxOuput->SuspendLayout();
			this->groupBoxChecks->SuspendLayout();
			this->SuspendLayout();
			// 
			// groupBoxInput
			// 
			this->groupBoxInput->Controls->Add(this->textBoxSecondFile);
			this->groupBoxInput->Controls->Add(this->textBoxFirstFile);
			this->groupBoxInput->Controls->Add(this->buttonSelectSecondFile);
			this->groupBoxInput->Controls->Add(this->buttonSelectFirstFile);
			this->groupBoxInput->Controls->Add(this->labelSecondGram);
			this->groupBoxInput->Controls->Add(this->labelFirstGram);
			this->groupBoxInput->Location = System::Drawing::Point(13, 13);
			this->groupBoxInput->Name = L"groupBoxInput";
			this->groupBoxInput->Size = System::Drawing::Size(492, 148);
			this->groupBoxInput->TabIndex = 0;
			this->groupBoxInput->TabStop = false;
			this->groupBoxInput->Text = L"Входные данные (праволинейные грамматики)";
			// 
			// textBoxSecondFile
			// 
			this->textBoxSecondFile->Location = System::Drawing::Point(120, 98);
			this->textBoxSecondFile->Multiline = true;
			this->textBoxSecondFile->Name = L"textBoxSecondFile";
			this->textBoxSecondFile->Size = System::Drawing::Size(366, 25);
			this->textBoxSecondFile->TabIndex = 5;
			this->textBoxSecondFile->TextChanged += gcnew System::EventHandler(this, &MainForm::textBoxSecondFile_TextChanged);
			// 
			// textBoxFirstFile
			// 
			this->textBoxFirstFile->Location = System::Drawing::Point(120, 42);
			this->textBoxFirstFile->Multiline = true;
			this->textBoxFirstFile->Name = L"textBoxFirstFile";
			this->textBoxFirstFile->Size = System::Drawing::Size(366, 25);
			this->textBoxFirstFile->TabIndex = 4;
			this->textBoxFirstFile->TextChanged += gcnew System::EventHandler(this, &MainForm::textBoxFirstFile_TextChanged);
			// 
			// buttonSelectSecondFile
			// 
			this->buttonSelectSecondFile->Location = System::Drawing::Point(8, 98);
			this->buttonSelectSecondFile->Name = L"buttonSelectSecondFile";
			this->buttonSelectSecondFile->Size = System::Drawing::Size(108, 25);
			this->buttonSelectSecondFile->TabIndex = 3;
			this->buttonSelectSecondFile->Text = L"Выбрать файл";
			this->buttonSelectSecondFile->UseVisualStyleBackColor = true;
			this->buttonSelectSecondFile->Click += gcnew System::EventHandler(this, &MainForm::buttonSelectSecondFile_Click);
			// 
			// buttonSelectFirstFile
			// 
			this->buttonSelectFirstFile->Location = System::Drawing::Point(6, 42);
			this->buttonSelectFirstFile->Name = L"buttonSelectFirstFile";
			this->buttonSelectFirstFile->Size = System::Drawing::Size(110, 25);
			this->buttonSelectFirstFile->TabIndex = 2;
			this->buttonSelectFirstFile->Text = L"Выбрать файл";
			this->buttonSelectFirstFile->UseVisualStyleBackColor = true;
			this->buttonSelectFirstFile->Click += gcnew System::EventHandler(this, &MainForm::buttonSelectFirstFile_Click);
			// 
			// labelSecondGram
			// 
			this->labelSecondGram->AutoSize = true;
			this->labelSecondGram->Location = System::Drawing::Point(6, 80);
			this->labelSecondGram->Name = L"labelSecondGram";
			this->labelSecondGram->Size = System::Drawing::Size(111, 13);
			this->labelSecondGram->TabIndex = 1;
			this->labelSecondGram->Text = L"Вторая грамматика:";
			// 
			// labelFirstGram
			// 
			this->labelFirstGram->AutoSize = true;
			this->labelFirstGram->Location = System::Drawing::Point(6, 23);
			this->labelFirstGram->Name = L"labelFirstGram";
			this->labelFirstGram->Size = System::Drawing::Size(113, 13);
			this->labelFirstGram->TabIndex = 0;
			this->labelFirstGram->Text = L"Первая грамматика:";
			// 
			// groupBoxOuput
			// 
			this->groupBoxOuput->Controls->Add(this->textBoxFolder);
			this->groupBoxOuput->Controls->Add(this->buttonSelectFolder);
			this->groupBoxOuput->Location = System::Drawing::Point(13, 161);
			this->groupBoxOuput->Name = L"groupBoxOuput";
			this->groupBoxOuput->Size = System::Drawing::Size(492, 73);
			this->groupBoxOuput->TabIndex = 1;
			this->groupBoxOuput->TabStop = false;
			this->groupBoxOuput->Text = L"Выходные данные";
			// 
			// textBoxFolder
			// 
			this->textBoxFolder->Location = System::Drawing::Point(118, 28);
			this->textBoxFolder->Multiline = true;
			this->textBoxFolder->Name = L"textBoxFolder";
			this->textBoxFolder->Size = System::Drawing::Size(368, 25);
			this->textBoxFolder->TabIndex = 7;
			this->textBoxFolder->TextChanged += gcnew System::EventHandler(this, &MainForm::textBoxFolder_TextChanged);
			// 
			// buttonSelectFolder
			// 
			this->buttonSelectFolder->Location = System::Drawing::Point(6, 28);
			this->buttonSelectFolder->Name = L"buttonSelectFolder";
			this->buttonSelectFolder->Size = System::Drawing::Size(108, 25);
			this->buttonSelectFolder->TabIndex = 6;
			this->buttonSelectFolder->Text = L"Выбрать папку";
			this->buttonSelectFolder->UseVisualStyleBackColor = true;
			this->buttonSelectFolder->Click += gcnew System::EventHandler(this, &MainForm::buttonSelectFolder_Click);
			// 
			// buttonExit
			// 
			this->buttonExit->Location = System::Drawing::Point(511, 18);
			this->buttonExit->Name = L"buttonExit";
			this->buttonExit->Size = System::Drawing::Size(111, 24);
			this->buttonExit->TabIndex = 2;
			this->buttonExit->Text = L"Выход";
			this->buttonExit->UseVisualStyleBackColor = true;
			this->buttonExit->Click += gcnew System::EventHandler(this, &MainForm::buttonExit_Click);
			// 
			// buttonStart
			// 
			this->buttonStart->BackColor = System::Drawing::SystemColors::ControlLight;
			this->buttonStart->Enabled = false;
			this->buttonStart->Location = System::Drawing::Point(511, 48);
			this->buttonStart->Name = L"buttonStart";
			this->buttonStart->Size = System::Drawing::Size(111, 38);
			this->buttonStart->TabIndex = 3;
			this->buttonStart->Text = L"Запуск";
			this->buttonStart->UseVisualStyleBackColor = false;
			this->buttonStart->Click += gcnew System::EventHandler(this, &MainForm::buttonStart_Click);
			// 
			// textBoxStatus
			// 
			this->textBoxStatus->Enabled = false;
			this->textBoxStatus->Location = System::Drawing::Point(12, 240);
			this->textBoxStatus->Multiline = true;
			this->textBoxStatus->Name = L"textBoxStatus";
			this->textBoxStatus->ReadOnly = true;
			this->textBoxStatus->Size = System::Drawing::Size(493, 45);
			this->textBoxStatus->TabIndex = 4;
			// 
			// groupBoxChecks
			// 
			this->groupBoxChecks->Controls->Add(this->checkBoxIter);
			this->groupBoxChecks->Controls->Add(this->checkBoxConcat);
			this->groupBoxChecks->Controls->Add(this->checkBoxMerge);
			this->groupBoxChecks->Location = System::Drawing::Point(512, 98);
			this->groupBoxChecks->Name = L"groupBoxChecks";
			this->groupBoxChecks->Size = System::Drawing::Size(110, 98);
			this->groupBoxChecks->TabIndex = 5;
			this->groupBoxChecks->TabStop = false;
			this->groupBoxChecks->Text = L"Операции";
			// 
			// checkBoxIter
			// 
			this->checkBoxIter->AutoSize = true;
			this->checkBoxIter->Checked = true;
			this->checkBoxIter->CheckState = System::Windows::Forms::CheckState::Checked;
			this->checkBoxIter->Location = System::Drawing::Point(6, 69);
			this->checkBoxIter->Name = L"checkBoxIter";
			this->checkBoxIter->Size = System::Drawing::Size(75, 17);
			this->checkBoxIter->TabIndex = 2;
			this->checkBoxIter->Text = L"Итерация";
			this->checkBoxIter->UseVisualStyleBackColor = true;
			this->checkBoxIter->CheckedChanged += gcnew System::EventHandler(this, &MainForm::checkBoxIter_CheckedChanged);
			// 
			// checkBoxConcat
			// 
			this->checkBoxConcat->AutoSize = true;
			this->checkBoxConcat->Checked = true;
			this->checkBoxConcat->CheckState = System::Windows::Forms::CheckState::Checked;
			this->checkBoxConcat->Location = System::Drawing::Point(6, 46);
			this->checkBoxConcat->Name = L"checkBoxConcat";
			this->checkBoxConcat->Size = System::Drawing::Size(98, 17);
			this->checkBoxConcat->TabIndex = 1;
			this->checkBoxConcat->Text = L"Конкатенация";
			this->checkBoxConcat->UseVisualStyleBackColor = true;
			this->checkBoxConcat->CheckedChanged += gcnew System::EventHandler(this, &MainForm::checkBoxConcat_CheckedChanged);
			// 
			// checkBoxMerge
			// 
			this->checkBoxMerge->AutoSize = true;
			this->checkBoxMerge->Checked = true;
			this->checkBoxMerge->CheckState = System::Windows::Forms::CheckState::Checked;
			this->checkBoxMerge->Location = System::Drawing::Point(6, 23);
			this->checkBoxMerge->Name = L"checkBoxMerge";
			this->checkBoxMerge->Size = System::Drawing::Size(94, 17);
			this->checkBoxMerge->TabIndex = 0;
			this->checkBoxMerge->Text = L"Обьединение";
			this->checkBoxMerge->UseVisualStyleBackColor = true;
			this->checkBoxMerge->CheckedChanged += gcnew System::EventHandler(this, &MainForm::checkBoxMerge_CheckedChanged);
			// 
			// MainForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(635, 312);
			this->Controls->Add(this->groupBoxChecks);
			this->Controls->Add(this->textBoxStatus);
			this->Controls->Add(this->buttonStart);
			this->Controls->Add(this->buttonExit);
			this->Controls->Add(this->groupBoxOuput);
			this->Controls->Add(this->groupBoxInput);
			this->Name = L"MainForm";
			this->Text = L"MainForm";
			this->groupBoxInput->ResumeLayout(false);
			this->groupBoxInput->PerformLayout();
			this->groupBoxOuput->ResumeLayout(false);
			this->groupBoxOuput->PerformLayout();
			this->groupBoxChecks->ResumeLayout(false);
			this->groupBoxChecks->PerformLayout();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion

		//Выход
	private: System::Void buttonExit_Click(System::Object^  sender, System::EventArgs^  e) {
		Close();
	}
			 // Выбор файла для первой грамматики
	private: System::Void buttonSelectFirstFile_Click(System::Object^  sender, System::EventArgs^  e) {
		if (openFileDialog->ShowDialog() == System::Windows::Forms::DialogResult::Cancel) {
			return;
		}
		textBoxFirstFile->Text = openFileDialog->InitialDirectory + openFileDialog->FileName;
	}
			 // Выбор файла для второй грамматики
	private: System::Void buttonSelectSecondFile_Click(System::Object^  sender, System::EventArgs^  e) {
		if (openFileDialog->ShowDialog() == System::Windows::Forms::DialogResult::Cancel) {
			return;
		}
		textBoxSecondFile->Text = openFileDialog->InitialDirectory + openFileDialog->FileName;
	}
			 // Выбор папки для выходных данных
	private: System::Void buttonSelectFolder_Click(System::Object^  sender, System::EventArgs^  e) {
		if (folderBrowserDialog->ShowDialog() == System::Windows::Forms::DialogResult::Cancel) {
			return;
		}
		textBoxFolder->Text = folderBrowserDialog->SelectedPath;
	}
			 //Когда меняется путь к 1 файлу
	private: System::Void textBoxFirstFile_TextChanged(System::Object^  sender, System::EventArgs^  e) {
		updateStartBtnEnabled();
	}
			 // Когда меняется путь ко 2 файлу
	private: System::Void textBoxSecondFile_TextChanged(System::Object^  sender, System::EventArgs^  e) {
		updateStartBtnEnabled();
	}
			 // Когда менятеся путь к папке
	private: System::Void textBoxFolder_TextChanged(System::Object^  sender, System::EventArgs^  e) {
		updateStartBtnEnabled();
	}
			 //Нажимаешь checkbox
	private: System::Void checkBoxMerge_CheckedChanged(System::Object^  sender, System::EventArgs^  e) {
		updateStartBtnEnabled();
	}
			 //Нажимаешь checkbox
	private: System::Void checkBoxConcat_CheckedChanged(System::Object^  sender, System::EventArgs^  e) {
		updateStartBtnEnabled();
	}
			 //Нажимаешь checkbox
	private: System::Void checkBoxIter_CheckedChanged(System::Object^  sender, System::EventArgs^  e) {
		updateStartBtnEnabled();
	}
			 // Проверка на то что нужно кнопку дизейблить
	private: System::Void updateStartBtnEnabled() {
		String^ f_file_text = textBoxFirstFile->Text;
		String^ s_file_text = textBoxSecondFile->Text;
		String^ folder_file_text = textBoxFolder->Text;

		if (f_file_text->Length > 0 && s_file_text->Length > 0 && folder_file_text->Length > 0) {
			buttonStart->Enabled = true;

			Boolean is_merge = checkBoxMerge->Checked;
			Boolean is_concat = checkBoxConcat->Checked;
			Boolean is_iter = checkBoxIter->Checked;
			if (is_merge.Equals(true) || is_concat.Equals(true) || is_iter.Equals(true)) {
				buttonStart->Enabled = true;
			}
			else {
				buttonStart->Enabled = false;
			}
		}
		else {
			buttonStart->Enabled = false;
		}
	}
			 // Запуск программы
	private: System::Void buttonStart_Click(System::Object^  sender, System::EventArgs^  e) {
		//textBoxFirstFile->Text = "C:\\Users\\lenok\\Desktop\\примеры\\грамматика1.txt";
		//textBoxSecondFile->Text = "C:\\Users\\lenok\\Desktop\\примеры\\грамматика2.txt";
		//folderBrowserDialog->SelectedPath = "C:\\Users\\lenok\\Desktop\\примеры";

		textBoxStatus->Text = "";

		Boolean is_merge = checkBoxMerge->Checked;
		Boolean is_concat = checkBoxConcat->Checked;
		Boolean is_iter = checkBoxIter->Checked;

		std::string path_f_grammar = Utils::system_string_to_string(textBoxFirstFile->Text);
		std::string path_s_grammar = Utils::system_string_to_string(textBoxSecondFile->Text);

		std::vector<std::string> f_grammar_lines = Utils::read_file(path_f_grammar);
		std::vector<std::string> s_grammar_lines = Utils::read_file(path_s_grammar);

		if (f_grammar_lines.size() == 0 || s_grammar_lines.size() == 0) {
			textBoxStatus->Text = "Выходные данные некорректны! ";
			return;
		}

		Grammar f_grammar, s_grammar;
		f_grammar.parse_params(f_grammar_lines);
		s_grammar.parse_params(s_grammar_lines);

		std::string folder_path = Utils::system_string_to_string(folderBrowserDialog->SelectedPath);
		if (is_merge.Equals(true)) {
			std::string m_grammar_path = "\\обьединение_грамматик.txt";
			Grammar m_grammar = f_grammar.merge(s_grammar);
			Utils::write_to_file(folder_path + m_grammar_path, m_grammar.to_string());
			textBoxStatus->AppendText("Обьединение грамматик получено. ");
		}

		if (is_concat.Equals(true)) {
			std::string c_grammar_path = "\\конкатенация_грамматик.txt";
			Grammar c_grammar = f_grammar.concat(s_grammar);
			Utils::write_to_file(folder_path + c_grammar_path, c_grammar.to_string());
			textBoxStatus->AppendText("Конкатенация грамматик получена. ");
		}

		if (is_iter.Equals(true)) {
			std::string i_grammar_path = "\\итерация_грамматик.txt";
			Grammar i_grammar = f_grammar.iteration();
			Utils::write_to_file(folder_path + i_grammar_path, i_grammar.to_string());
			textBoxStatus->AppendText("Итерация первой грамматики получена. ");
		}
	}
	};
}
