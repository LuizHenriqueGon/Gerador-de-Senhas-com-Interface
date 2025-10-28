# 🔐 Gerador de Senhas Seguras em Java

<img width="588" height="412" alt="Captura de tela 2025-10-28 120653" src="https://github.com/user-attachments/assets/a39a1f29-9af8-48f7-bb8e-932603bb24b5" />

*Recomendo tirar um print-screen do programa rodando e substituir o link acima!*

## 📝 Sobre o Projeto

Este é um aplicativo de desktop simples, mas robusto, para gerar senhas aleatórias e seguras. O usuário pode customizar o nível de complexidade da senha, escolhendo o tamanho e os tipos de caracteres a serem incluídos.

Este projeto foi desenvolvido como parte do meu portfólio de estudante de **Análise e Desenvolvimento de Sistemas**, com o objetivo de praticar a criação de interfaces gráficas (GUI) em Java e aplicar conceitos básicos de cibersegurança.

## 🚀 Funcionalidades

* **Tamanho Customizável:** Permite ao usuário definir o tamanho da senha (entre 8 e 128 caracteres).
* **Seleção de Caracteres:** Caixas de seleção para incluir:
    * Letras Maiúsculas (A-Z)
    * Letras Minúsculas (a-z)
    * Números (0-9)
    * Símbolos Especiais (!@#$%)
* **Geração Segura:** Utiliza a classe `java.security.SecureRandom` para garantir uma geração de caracteres criptograficamente forte.
* **Copiar Fácil:** Um botão "Copiar" que envia a senha gerada diretamente para a área de transferência do usuário.

## 💻 Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

* **Linguagem:** Java 11+
* **Interface Gráfica (GUI):** Java Swing
* **Segurança:** `java.security.SecureRandom`
* **Gestão de UI:** `javax.swing.JFrame`, `JPanel`, `JButton`, `JCheckBox`, `JSpinner`
* **Clipboard API:** `java.awt.datatransfer`

