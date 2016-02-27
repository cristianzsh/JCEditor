package com.cristian;

import org.fife.ui.autocomplete.TemplateCompletion;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

public class AutocompletarPotigol {
	private DefaultCompletionProvider provedor;
	private String[][] palavrasComExplicacao = {
	{"leia_inteiro",        "<b>leia_inteiro</b><p>Lê um número inteiro<br/><br/>Exemplos:<br/><br/>n = leia_inteiro<br/>x, y, z = leia_inteiro</p>"},
	{"leia_texto",          "<b>leia_texto</b><p>Lê um texto<br/><br/>Exemplos:<br/><br/>t = leia_texto<br/>a, b = leia_texto</p>"},
	{"leia_inteiros",       "<b>leia_inteiros</b><p>Lê uma lista de inteiros<br/><br/>Exemplos:<br/><br/>números = leia_inteiros<br/>números = leia_inteiros(5)</p>"},
	{"inteiro",             "<b>inteiro</b><p>Converte para o tipo inteiro<br/><br/>Exemplos:<br/><br/>12345.678.inteiro<br/>\"123\".inteiro</p>"},
	{"arredonde",           "<b>arredonde</b><p>Arredonda o número<br/><br/>Exemplos:<br/><br/>12345.678.arredonde<br/>12345.678.arredonde(2)</p>"},
	{"texto",               "<b>texto</b><p>Converte para texto<br/><br/>Exemplos:<br/><br/>12345.texto</p>"},
	{"formato",             "<b>formato</b><p>Formata uma expressão<br/><br/>Exemplos:<br/><br/>12345 formato \"%8d\"<br/>123.45 formato \"%.1f\"</p>"},
	{"real",                "<b>real</b><p>Converte para um número de ponto flutuante<br/><br/>Exemplos:<br/><br/>\"12.3\".real<br/>\"12a.3\".real</p>"},
	{"tamanho",             "<b>tamanho</b><p>Retorna o tamanho de uma lista ou string<br/><br/>Exemplos:<br/><br/>\"abc\".tamanho<br/>[2, 4, 6, 8, 10].tamanho</p>"},
	{"posição",             "<b>posição</b><p>Retorna a posição onde o item passado como argumento está<br/><br/>Exemplos:<br/><br/>\"abc\".posição('b')<br/>a.posição(6)</p>"},
	{"contém",              ""},
	{"maiúsculo",           ""},
	{"minúsculo",           ""},
	{"inverta",             ""},
	{"divida",              ""},
	{"cabeça",              ""},
	{"último",              ""},
	{"cauda",               ""},
	{"pegue",               ""},
	{"descarte",            ""},
	{"selecione",           ""},
	{"descarte_enquanto",   ""},
	{"pegue_enquanto",      ""},
	{"ordene",              ""},
	{"junte",               ""},
	{"remova",              ""},
	{"insira",              ""},
	{"mapeie",              ""},
	{"injete",              ""},
	{"sen",                 ""},
	{"cos",                 ""},
	{"tg",                  ""},
	{"arcsen",              ""},
	{"arccos",              ""},
	{"arctg",               ""},
	{"abs",                 ""},
	{"raiz",                ""},
	{"log",                 ""},
	{"log10",               ""},
	{"então",               ""},
	{"fim",                 ""},
	{"senãose",             ""},
	{"caso",                ""},
	{"de",                  ""},
	{"até",                 ""},
	{"faça",                ""},
	{"var",                 ""},
	{"passo",               ""},
	{"em",                  ""},
	{"tipo",                ""},
	{"e",                   ""},
	{"ou",                  ""},
	{"não",                 ""},
	{"verdadeiro",          ""},
	{"falso",               ""},
	};

	public CompletionProvider criar() {
		provedor = new DefaultCompletionProvider();
		provedor.setAutoActivationRules(true, "");
		// provedor.setParameterizedCompletionParams('(', ".", ')');

		for (String[] exp : palavrasComExplicacao) {
			String palavra = exp[0];
			String explic = exp[1];
			provedor.addCompletion(new BasicCompletion(provedor, palavra, null, explic));
		}

		addTemplateCompletion("para", "para ${x} de ${y}${cursor} até ${z} faça\n\t${cursor}\nfim", "gera a estrutura do loop para");
		addTemplateCompletion("se", "se (${}) então\n\t${cursor}\nfim", "gera a estrutura do condicional se");
		addTemplateCompletion("escolha", "escolha (${x})\n\t${cursor}\nfim", "gera a estrutura do comando escolha");
		addTemplateCompletion("enquanto", "enquanto (${x}) faça\n\t${cursor}\n\t${x} := ${x} + 1\nfim", "gera a estrutura do comando enquanto");
		addTemplateCompletion("escreva", "escreva \"${cursor}\"", "escreve com quebra de linha");
		addTemplateCompletion("imprima", "imprima \"${cursor}\"", "escreve sem quebra de linha");

		return provedor;
	}

	private void addTemplateCompletion(String palavra, String substituir, String explicacao) {
		provedor.addCompletion(new TemplateCompletion(provedor, palavra, palavra, substituir, null, explicacao));
	}
}