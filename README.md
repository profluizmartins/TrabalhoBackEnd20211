# Backend do projeto

## Branch "Grupo-5"

- Módulo responsável por implementar o módulo financeiro

- Somente os usuários Admin e Financeiro

- Implementação em backend geração de faturas na conclusão da venda.

- Deve pegar o valor total da venda (SOMA (Preço Unitário \* Quantidade)) e dividir pela quantidade de prestação. Cada prestação será referente a uma fatura, atualizada a data de vencimento.

- Se Tipo Pagamento for Dinheiro, Cartão de Débito e PIX, aplicar desconto de 5%.

- Telas

               Pagamentos realizados

               Pagamentos Pendentes

               Cadastrar Pagamento (data do pagamento e valor do pagamento)

               Relatório mensal de pagamento, previsão e atrasos.

* Caso o pagamento seja realizado em atraso, adicionar multa de 10% e 0,5% de juros por dia de atraso.
