#language: pt
#encoding: UTF-8

Funcionalidade: Criar conta na loja virtual
	Como um visitante da loja virtual
	Eu quero criar uma conta de cliente
	Para que eu possa realizar compras na loja
	
Esquema do Cenário: Criar conta de cliente com sucesso
	Dado Eu estou na página inicial da loja virtual
	E Eu acesso o link de criação de conta
	E Eu informo o meu email e solicito a criação da conta
	E Eu preencho o primeiro nome <primeironome>
	E Eu preencho o sobrenome <sobrenome>
	E Eu preencho a senha <senha>
	E Eu informo minha data de nascimento
	Quando Eu solicito a realização do cadastro
	Então Sistema informa que o a conta foi criada com sucesso
	
	Exemplos:
	| primeironome  | sobrenome  | senha       |
	| "Maria"       | "Souza"    | "Teste@123" |
	| "João"        | "Oliveira" | "Teste@321" |
	| "Carlos"      | "Silva"    | "Teste@456" |