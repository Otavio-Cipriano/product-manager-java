API de controle de estoque Inteligente para pequeno varejistas, que permite o cadastro de produtos e fornecedores. Além de dar uma visão geral dos produtos em estoque,  sugestão de reposição inteligente baseado em vendas passadas, sugerir quando repor produtos, assim como integração com fornecedores e buscar preços de produtos em tempo real. 

### 🔹 **Por que esse tema?**
1. **Aplicabilidade real**: Pequenos negócios precisam gerenciar seu estoque e repo-lo.
2. **Pode crescer**: Dá para adicionar uma "Inteligência real", com machie learning ou algo parecido.

### 🔹 **Principais funcionalidades da API**

✅ **Cadastro de Produtos**
- Criar, listar, atualizar e deletar produtos.

✅ **Controle de Estoque**
- Registrar entrada e saída de produtos.
- Alertas automáticos para estoque baixo.

✅ **Sugestão de Reposição Inteligente**
- Baseado em vendas passadas, sugerir quando repor produtos.

✅ **Integração com Fornecedores**
- Buscar preços de produtos em tempo real.

✅ **Autenticação e Segurança**
- Login com JWT para proteger endpoints.

### 🔹 Entidades
- **Produto:** 
```java
public class Product{
	private Long id;
	private String name;
	private Long quantity;
	private BigDecimal price;
	private LocalDateTime createdAt;
	private String category;
	private Supplier supplier;
}
```
- **Venda**
```java
public class Sale{
	private Long id;
	private LocalDateTime createdAt;
	private List<SaleItem> items;
	private BigDecimal valueTotal;
}
```
- **ItemVenda**
```java
public class SaleItem{
	private Long id;
	private LocalDateTime saleAt;
	private Product product;
	private int quantity;
	private BigDecimal unitaryPrice;
}
```
- **User**
```java
public class User{
	private Long id;
	private String name;
	private String email;
	private String password;
	private String role;
}
```

#### Diagrama de classe
```mermaid
classDiagram
    class Produto {
        +Long id
        +String nome
        +String descricao
        +String codigoBarras
        +Integer quantidadeEmEstoque
        +Integer quantidadeMinima
        +BigDecimal precoVenda
        +BigDecimal precoCusto
        +String categoria
    }

    class Venda {
        +Long id
        +LocalDateTime dataVenda
        +List<ItemVenda> itens
        +BigDecimal valorTotal
    }

    class ItemVenda {
        +Long id
        +Venda venda
        +Produto produto
        +Integer quantidade
        +BigDecimal precoUnitario
    }

    class ReposicaoEstoque {
        +Long id
        +Produto produto
        +Integer quantidadeSugerida
        +BigDecimal precoEstimado
        +String status
    }

    class Usuario {
        +Long id
        +String nome
        +String email
        +String senha
        +String perfil
    }

    Produto "1" --> "1..*" ItemVenda
    Venda "1" --> "1..*" ItemVenda
    ReposicaoEstoque "1" --> "1" Produto

```

### 🔹 Fluxograma
```mermaid
graph TD
    A[Início] -->|Usuário cadastra produto| B[Cadastro de Produto]
    B -->|Usuário registra uma venda| E[Registro de Venda]
    E -->|Atualiza estoque| F[Atualização de Estoque]
    F -->|Verifica se estoque está abaixo do mínimo| G{Estoque abaixo do mínimo?}
    
    G -- Sim --> H[Gerar sugestão de reposição]
    H -->|Usuário confirma reposição| J[Reposição de Estoque]
    J -->|Atualiza estoque com novos produtos| K[Estoque Atualizado]
```
### 🔹 Step-by-step

1. [x] **Criar o projeto no Spring Boot usando o Spring Initializr**
2. [x] **Definir as entidades principais (Produto, Estoque, Venda, Fornecedor, Usuário)**
3. [ ] **Implementar os endpoints REST**
4. [ ] **Configurar autenticação e autorização**
5. [ ] **Adicionar lógica para sugestões de reposição**
6. [ ] **Testar e documentar a API**
7. [ ] **Implantar na Railway**
