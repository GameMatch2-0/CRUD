# CRUD de usuário do <br>
Formato do JSON para testar métodos no insomnia: <br>
{ <br>
	"nome": "Adam Sandler", <br>
	"orientacaoSexual": "Pansexual", <br>
	"email": "Adam@sandler.com", <br>
	"dtNascimento": "1997-04-07", <br>
	"celular": 1111111111, <br>
	"senha": "adam123S@ndler", <br>
	"jogosFavoritos": ["Valorant", "LOL", "PUBG", "Minecraft", "Tetris"] <br>
} <br>
 <br>
retorno esperado: <br>
{ <br>
	"nome": "Adam Sandler", <br>
	"orientacaoSexual": "Pansexual", <br>
	"dtNascimento": "07/04/1997", <br>
	"email": "Adam@sandler.com", <br>
	"celular": 1111111111, <br>
	"senha": "adam123S@ndler", <br>
	"dtCadastro": "13/09/2023 21:02:08", <br>
	"jogosFavoritos": [ <br>
		"Valorant", <br>
		"LOL", <br>
		"PUBG", <br>
		"Minecraft", <br>
		"Tetris" <br>
	], <br>
	"deleted": false <br>
} <br>
