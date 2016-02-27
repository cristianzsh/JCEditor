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
	{"leia_inteiro",        ""},
	{"leia_texto",          ""},
	{"leia_inteiros",       ""},
	{"inteiro",             ""},
	{"arredonde",           ""},
	{"texto",               ""},
	{"formato",             ""},
	{"real",                ""},
	{"tamanho",             ""},
	{"posição",             ""},
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