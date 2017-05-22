# JCEditor

Editor de texto criado em Java

O JCE possui suporte a diversas linguagens como Java, C++, Python, Scala e integração com a linguagem [Potigol](http://potigol.github.io/), desenvolvida no IFRN.

[![Gitter](https://badges.gitter.im/cristian-henrique/JCEditor.svg)](https://gitter.im/cristian-henrique/JCEditor?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

## Configuração no Linux
  <p align="center"><img src="screenshots/JCE_Linux.png"/><p>
  - Baixe os arquivos JCEditor.jar e configPotigol.zip e os coloque no mesmo diretório;
  - Entre no Terminal e digite: ````java -jar JCEditor.jar````;

   Se tudo ocorrer normalmente, você verá uma mensagem informando que o Potigol está configurado.
   
  - Em um Terminal, entre na pasta ConfigJCE/.potigol (localizada em sua pasta de usuário) e digite:
  
    ``chmod 777 ExecPotigol.sh``

## (Opcional) Criando um atalho
  - Crie um documento vazio e digite:
  
``[Desktop Entry]
  Name=JCEditor
  Type=Application
  Exec=java -jar /caminho_para_a_pasta_do_JCE/JCEditor.jar
  Icon=/caminho_para_o_ícone/jceIcone.png``
  
  (Informar um ícone é opcional)
  - Salve o arquivo criado com o nome desejado e a extensão ``.desktop``

## Configuração no Mac
  <p align="center"><img src="screenshots/JCE_Mac.png"/></p>
  - Baixe os arquivos JCEditor.jar e configPotigol.zip e os coloque no mesmo diretório;
  - Apenas clique duas vezes no arquivo JCEditor.jar e espere a mensagem informando que o Potigol está configurado aparecer.

## Configuração no Windows
  <p align="center"><img src="screenshots/JCE_Windows.png"/><p>
  - Baixe os arquivos JCEditor.jar e configPotigol.zip e os coloque no mesmo diretório;
  - Dê um duplo clique no arquivo JCEditor.jar e, se tudo ocorrer normalmente, você verá uma mensagem informando que o Potigol foi configurado.
