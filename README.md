# brasil-transparente-api

API REST em Kotlin e Spring Boot responsável por ler e expor dados de gastos públicos federais para navegação hierárquica e análise agregada.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Kotlin 2.3.x
- **JDK**: OpenJDK 25
- **Framework**: Spring Boot 4
- **Banco de Dados**: MySQL
- **Build Tool**: Gradle
- **Documentação de API**: OpenAPI / Swagger UI

## 📦 Instalação e Configuração

### Pré-requisitos
- OpenJDK 25
- MySQL 8+
- Git

### Passos para Novo Desenvolvedor

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/brasil-transparente/brasil-transparente-api.git
   cd brasil-transparente-api
   ```

2. **Crie a base de dados local:**
   - Baixe o arquivo de Dump do banco disponível no [Google Drive](https://drive.google.com/drive/folders/1EvbRIqP9Eg8dZJP6RKSpf7KoippdhC3c?usp=drive_link).
   - Importe o dump em sua instância local do MySQL (ex.: database `gastos`).

3. **Configure as variáveis de ambiente:**
   Defina as variáveis de conexão com o banco de dados (no terminal ou na configuração de execução da IDE):
   ```bash
   export DB_JDBC_URL="jdbc:mysql://localhost:3306/gastos"
   export DB_USER="seu_usuario"
   export DB_PASSWORD="sua_senha"
   ```

4. **Execute a aplicação:**
   ```bash
   ./gradlew bootRun
   ```

5. **Acesse a documentação interativa da API (Swagger UI):**
   ```text
   http://localhost:8080/api/docs
   ```

## 🧪 Testes e Qualidade

Para compilar, rodar os testes unitários e de integração e validar a cobertura de código (JaCoCo):
```bash
./gradlew check
```

## 🤝 Como Contribuir

- 📌 **Comunidade no Discord**: Dúvidas e discussões sobre o projeto acontecem no nosso [Discord](https://discord.gg/sQbf3bSzt4).
- 🐛 **Issues**: Acompanhe e selecione tarefas abertas na aba de Issues e Projects no GitHub.
- 💡 **Sugestões e Bugs**: Relate problemas ou novas ideias via Discord ou abrindo uma Issue.

## ⚖️ Licença

[![AGPL-3.0](https://img.shields.io/badge/License-AGPL_v3-blue.svg)](https://www.gnu.org/licenses/agpl-3.0)

Este projeto está licenciado sob a **GNU Affero General Public License v3.0** (AGPL-3.0). Consulte [LICENSE.md](LICENSE.md) para mais detalhes.
