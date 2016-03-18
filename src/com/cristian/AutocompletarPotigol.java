package com.cristian;

import org.fife.ui.autocomplete.TemplateCompletion;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

/**
* Funções de autocompletar da linguagem Potigol.
* @author  Leonardo Lucena (leonardo.lucena@ifrn.edu.br)
* @author  Cristian Henrique (cristianmsbr@gmail.com)
* @version 1.0
* @since   Versão 2.0
*/

public class AutocompletarPotigol {
	private DefaultCompletionProvider provedor;
	private String[][] palavrasComExplicacao = {
	{"leia_inteiro",        "<b>leia_inteiro</b><p>lê um número inteiro do teclado</p><code>num = leia_inteiro</code>"},
	{"leia_real",           "<b>leia_real</b><p>lê um número real do teclado</p><code>num = leia_real</code>"},
	{"leia_texto",          "<b>leia_texto</b><p>lê um texto do teclado</p><code>texto = leia_texto"},
	{"inteiro",             "<b>inteiro</b><p>converte para inteiro</p><code>número = 12.23<br>n = número.inteiro   # 12"},
	{"arredonde",           "<b>arredonde</b><p>arredonda o número para o inteiro mais próximo</p><code>n=12.89<br>n.arredonde    # 13</code>"},
	{"texto",               "<b>texto</b><p>converte para texto</p>"},
	{"formato",             "<b>formato</b><p>formata um número para ser exibido como texto</p><code>123.45 formato \"%.1f\"   # 123.4"},
	{"real",                "<b>real</b><p>converte para real</p><code>\"12.3\".real   # 12.3<br>\"12a.3\".real   # 12.0"},
	{"tamanho",             "<b>tamanho</b><p>devolve o tamanho de uma lista ou de um texto</p>"},
	{"posição",             "<b>posição(elemento)</b><p>devolve a posição do elemento na lista ou no texto</p>"},
	{"maiúsculo",           "<b>maiúsculo</b><p>devolve o texto convertido em letras maiúsculas</p><code>m = \"Potigol\" # \"POTIGOL\""},
	{"minúsculo",           "<b>minúsculo</b><p>devolve o texto convertido em letras minúsculas</p>"},
	{"inverta",             "<b>inverta</b><p>devolve uma nova lista ou um novo texto como os elementos invertidos</p>"},
	{"cabeça",              "<b>cabeça</b><p>devolve o primeiro elemento de um texto ou de uma lista</p>"},
	{"último",              "<b>último</b><p>devolve o último elemento de um texto ou de uma lista</p>"},
	{"cauda",               "<b>cauda</b><p>devolve um novo texto ou uma nova lista contendo todos os elementos, exceto o primeiro</p>"},
	{"pegue",               "<b>pegue(n)</b><p>devolve os n primeiros caracteres de um texto ou os n primeiros elementos de uma lista</p>"},
	{"descarte",            "<b>descarte</b><p>descarta os n primeiros caracteres do texto ou os primeiros n elementos da lista, devolve o restante</p>"},
	{"selecione",           "<b>selecione(condição)</b><p>devolve uma lista ou um texto com os elementos que satisfazem a condição</p><code>[1,2,3,4,5,6].selecione(n => n mod 2 == 0)   # [2,4,6]</code>"},
	{"pegue_enquanto",      "<b>pegue_enquanto(condição)</b><p>devolve os primeiros caracteres de um texto ou os primeiros elementos de uma lista até que a condição seja falsa</p>"},
	{"ordene",              "<b>ordene</b><p>devolve uma nova lista com os elementos em ordem crescente</p>"},
	{"remova",              "<b>remova(posição)</b><p>devolve uma nova lista sem o elemento da posição indicada</p>"},
	{"mapeie",              "<b>mapeie(função)</b><p>devolve uma nova lista mapeando os elementos da lista original aplicando a função</p>"},
	{"primeiro",            "<b>primeiro</b><p>devolve o primeiro componente de uma tupla</p><code>(\"potigol\", 2016, verdadeiro).primeiro   # \"potigol\"</code>"},
	{"segundo",             "<b>segundo</b><p>devolve o segundo componente de uma tupla</p><code>(\"potigol\", 2016, verdadeiro).segundo   # 2016</code>"},
	{"terceiro",            "<b>terceiro</b><p>devolve o terceiro componente de uma tupla</p><code>(\"potigol\", 2016, verdadeiro).terceiro   # verdadeiro</code>"},
	{"quarto",              "<b>quarto</b><p>devolve o quarto componente de uma tupla</p>"},
	{"quinto",              "<b>quinto</b><p>devolve o quinto componente de uma tupla</p>"},
	{"sexto",               "<b>sexto</b><p>devolve o sexto componente de uma tupla</p>"},
	{"sétimo",              "<b>sétimo</b><p>devolve o sétimo componente de uma tupla</p>"},
	{"oitavo",              "<b>oitavo</b><p>devolve o oitavo componente de uma tupla</p>"},
	{"nono",                "<b>nono</b><p>devolve o nono componente de uma tupla</p>"},
	{"décimo",              "<b>décimo</b><p>devolve o décimo componente de uma tupla</p>"},
	{"Matriz.imutável",     "<b>Matriz.imutável(linhas, colunas, valor)</b><p>devolve uma matriz do tamanho indicado por linhas e colunas contendo o mesmo valor</p><code>Matriz.imutável(2,2,0)   #  [[0,0],[0,0]]</code>"},
	{"Matriz.mutável",      "<b>Matriz.mutável(linhas, colunas, valor)</b><p>devolve uma matriz mutável do tamanho indicado por linhas e colunas contendo o mesmo valor</p><code>Matriz.mutável(2,2,0)</code>"},
	{"sen",                 "<b>sen(ângulo)</b><p>calcula o seno do ângulo</p>"},
	{"tg",                  "<b>tg(ângulo)</b><p>calcula a tangente do ângulo</p>"},
	{"raiz",                "<b>raiz(n)</b><p>calcula a raiz quadrada de n</p>"},
	{"log",                 "<b>log(n)</b><p>calcula o logaritmo de n</p>"},
	{"log10",               "<b>log10(n)</b><p>calcula o logaritmo de n na base 10</p>"},
	{"aleatório",           "<b>aleatório</b><p>devolve um número aleatório entre 0 e 1</p>"},
	{"então",               "<b>então</b><p></p>"},
	{"fim",                 "<b>fim</b><p></p>"},
	{"senãose",             "<b>senãose</b><p>permite uma outra condição dentro de um se</p>"},
	{"caso",                "<b>caso</b><p></p>"},
	{"de",                  "<b>de</b><p></p>"},
	{"até",                 "<b>até</b><p></p>"},
	{"faça",                "<b>faça</b><p></p>"},
	{"gere",                "<b>gere</b><p></p>"},
	{"passo",               "<b>passo</b><p>define o passo entre os valores de uma faixa</p>"},
	{"em",                  "<b>em</b><p></p>"},
	{"e",                   "<b>e</b><p>e lógico</p>"},
	{"ou",                  "<b>ou</b><p>ou lógico</p>"},
	{"não",                 "<b>não</b><p>não lógico</p>"},
	{"verdadeiro",          "<b>verdadeiro</b><p></p>"},
	{"falso",               "<b>falso</b><p></p>"}
	};

	public CompletionProvider criar() {
		provedor = new DefaultCompletionProvider();
		provedor.setAutoActivationRules(true, ".");

		for (String[] exp : palavrasComExplicacao) {
			String palavra = exp[0];
			String explic = exp[1];
			provedor.addCompletion(new BasicCompletion(provedor, palavra, null, explic));
		}

		addTemplateCompletion("para", "para ${x} de ${y} até ${z} faça\n\t${cursor}\nfim", "gera a estrutura do laço para");
		addTemplateCompletion("paragere", "para ${x} de ${y} até ${z} gere\n\t${cursor}\nfim", "gera a estrutura do laço para gere");
		addTemplateCompletion("função", "(${})\n\t${cursor}\nfim", "gera a estrutura de função em linha");
		addTemplateCompletion("lambda", "(${n}) => ${cursor}", "gera a estrutura de uma expressão lambda");
		addTemplateCompletion("função em linha", "(${}) = ${cursor}", "gera a estrutura de função em linha");
		addTemplateCompletion("variável", "var ${x} := ${cursor}", "gera a estrutura de declaração de uma variável");
		addTemplateCompletion("tipo", "tipo ${t} = ${cursor}", "gera a estrutura de declaração de um tipo");
		addTemplateCompletion("se", "se ${condição} então\n\t${cursor}\nfim", "gera a estrutura do condicional se");
		addTemplateCompletion("sesenão", "se ${condição} então\n\t${cursor}\nsenão\n\nfim", "gera a estrutura do condicional se então senão");
		addTemplateCompletion("escolha", "escolha ${x}\n\tcaso ${y} => ${cursor}\nfim", "gera a estrutura do comando escolha");
		addTemplateCompletion("enquanto", "enquanto ${condição} faça\n\t${cursor}\nfim", "gera a estrutura do comando enquanto");
		addTemplateCompletion("escreva", "escreva \"${}\"${cursor}", "escreve e passe para a próxima linha");
		addTemplateCompletion("imprima", "imprima \"${}\"${cursor}", "escreve e continua na mesma linha");
		addTemplateCompletion("abs", "abs(${n})${cursor}", "<b>abs(n)</b><p>calcula o valor absoluto de n</p>");
		addTemplateCompletion("aleatório", "aleatório(${máximo})${cursor}", "<b>aleatório(máximo)</b><p>devolve um número aleatório entre 1 e máximo</p>");
		addTemplateCompletion("aleatório", "aleatório(${mínimo}, ${máximo})${cursor}", "<b>aleatório(mínimo, máximo)</b><p>devolve um número aleatório entre mínimo e máximo</p>");
		addTemplateCompletion("aleatório", "aleatório(${l})${cursor}", "<b>aleatório(lista)</b><p>devolve um valor escolhido aleatoreamente entre os valores da lista</p>");
		addTemplateCompletion("arcsen", "arcsen(${n})${cursor}", "<b>arcsen(n: Real)</b><p>calcula o arco cujo seno é n</p>");
		addTemplateCompletion("arccos", "arccos(${n})${cursor}", "<b>arccos(n: Real)</b><p>calcula o arco cujo cosseno é n</p>");
		addTemplateCompletion("arctg", "arctg(${n})${cursor}", "<b>arctg(n: Real)</b><p>calcula o arco cuja tangente é n</p>");
		addTemplateCompletion("arredonde", "arredonde(${casas})${cursor}", "<b>arredonde(casas: Inteiro)</b><p>arredonda o número para o quantidade de casas decimais</p><code>n=12.34<br>n.arredonde(1)   # 12.3");
		addTemplateCompletion("contém", "contém(${conteúdo})${cursor}", "<b>contém(conteúdo)</b><p>verifica se conteúdo pertence a uma lista ou a um texto</p>");
		addTemplateCompletion("cos", "cos(${ângulo})${cursor}", "<b>cos(ângulo)</b><p>calcula o cosseno do ângulo</p>");
		addTemplateCompletion("Cubo.imutável", "Cubo.imutável(${x}, ${y}, ${z}, ${valor})${cursor}", "<b>Cubo.imutável(x, y, z, valor)</b><p>devolve um cubo do tamanho indicado por x, y e z contendo o mesmo valor</p><code>Cubo.imutável(2,2,2,0)   #  [[[0,0],[0,0]],[[0,0],[0,0]]]</code>");
		addTemplateCompletion("Cubo.mutável", "Cubo.mutável(${x}, ${y}, ${z}, ${valor})${cursor}", "<b>Cubo.mutável(x, y, z, valor)</b><p>devolve um cubo mutável do tamanho indicado por x, y e z contendo o mesmo valor</p><code>Cubo.mutável(2,2,2,0)</code>");
		addTemplateCompletion("descarte_enquanto", "descarte_enquanto(${condição})${cursor}", "<b>descarte_enquanto(condição)</b><p>descarta os primeiros caracteres do texto ou os primeiros n elementos da lista até que a condição seja falsa, devolve o restante</p>");
		addTemplateCompletion("divida", "divida(\"${delimitador}\")${cursor}", "<b>divida(delimitador = \" \")</b><p>divide um texto em subtextos baseado em um delimitador, devolve uma lista destes subtextos</p>");
		addTemplateCompletion("injete", "injete(${neutro})(${operação})${cursor}", "<b>injete(neutro)(operação)</b><p>devolve o resultado da injeção da operação entre os elementos</p><code>[1,3,5,7].injete(0)((a,b) => a + b)  # 0 + 1 + 3 + 5 + 7</code>");
		addTemplateCompletion("insira", "insira(${pos}, ${valor})${cursor}", "<b>insira(posição, valor)</b><p>devolve uma nova lista acrescida do valor na posição indicada</p>");
		addTemplateCompletion("junte", "junte(\"${delimitador}\")${cursor}", "<b>junte(delimitador = \"\")</b><p>devolve um texto contendo os elementos separados pelo delimitador</p>");
		addTemplateCompletion("junte", "junte(\"${início}\", \"${delimitador}\", \"${fim}\")${cursor}", "<b>junte(início, delimitador, fim)</b><p>devolve um texto contendo o início, os elementos separados pelo delimitador e o fim</p>");
		addTemplateCompletion("leia_inteiros", "leia_inteiros(\"${delimitador}\")${cursor}", "<b>leia_inteiros(delimitador: Texto)</b><p>lê uma lista de números inteiros separados por um delimitador</p><code>números = leia_inteiros(\",\")");
		addTemplateCompletion("leia_inteiros", "leia_inteiros(${x})${cursor}", "<b>leia_inteiros(n: Inteiro)</b><p>lê n números inteiros (um por linha).</p><code>números = leia_inteiros(4)");
		addTemplateCompletion("Lista.imutável", "Lista.imutável(${tam}, ${valor})${cursor}", "<b>Lista.imutável(tamanho, valor)</b><p>devolve uma lista do tamanho indicado contendo o mesmo valor</p><code>Lista.imutável(5,0)   #  [0,0,0,0,0]</code>");
		addTemplateCompletion("Lista.vazia", "Lista.vazia[${Tipo}]${cursor}", "<b>Lista.vazia[Tipo]</b><p>devolve uma lista vazia do tipo indicado</p>");
		addTemplateCompletion("Lista.mutável", "Lista.mutável(${tam}, ${valor})${cursor}", "<b>Lista.mutável(tamanho, valor)</b><p>devolve uma lista mutável do tamanho indicado contendo o mesmo valor</p><code>Lista.mutável(5,0)   #  [0,0,0,0,0].mutável</code>");

		return provedor;
	}

	private void addTemplateCompletion(String palavra, String substituir, String explicacao) {
		provedor.addCompletion(new TemplateCompletion(provedor, palavra, palavra, substituir, null, explicacao));
	}
}