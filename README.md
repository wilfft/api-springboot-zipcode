# api--springboot-zipcode

 

- Criar API REST para cadastrar as faixas de CEP de cada loja;
  ●
- Criar API REST para retornar qual é a loja física que atende determinado CEP;
  REGRAS
- A tabela de cadastro de CEP deve possuir as seguintes colunas: 
- ID,
  CODIGO_LOJA, 
  FAIXA_INICIO e 
  FAIXA_FIM;
- As faixas de CEP não pode conflitar com as de outras lojas;
- Deve ser possível editar/excluir uma faixa de CEP;
- A aplicação deve ser feita em Springboot + REST + JPA/Hibernate


RESULTADO ESPERADO

| CODIGO_LOJA    | FAIXA_INICIO | FAIXA_FIM |
| -------------- | :----------: | :-------: |
| LOJA_PINHEIROS |   10000000   | 20000000  |
| LOJA_PINHEIROS |   20000001   | 30000000  |
| LOJA_JARDINS   |   30000001   | 40000000  |
| LOJA_JARDINS   |   40000001   | 50000000  |

 

MODO ERRADO

| CODIGO_LOJA    |                         FAIXA_INICIO                         | FAIXA_FIM |
| -------------- | :----------------------------------------------------------: | :-------: |
| LOJA_PINHEIROS |                           10000000                           | 20000000  |
| LOJA_PINHEIROS |                           20000001                           | 30000000  |
| LOJA_JARDINS   | 10000001  (erro! essa faixa de CEP conflita com a faixa de CEP<br/>da loja de Pinheiros) | 40000000  |

 

