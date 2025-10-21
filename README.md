# 🚀 Spring AI Ollama - Module Management with AI

Application Spring Boot intégrant Ollama AI (Llama3) pour la gestion intelligente de modules pédagogiques avec des fonctionnalités d'IA : résumés automatiques, génération de quiz, et assistant Q&A.

## 📋 Description

Ce projet est une API REST développée avec Spring Boot qui permet de gérer des modules pédagogiques et d'utiliser l'intelligence artificielle (via Ollama et Llama3) pour :
- Générer des résumés pédagogiques
- Créer des quiz automatiquement
- Répondre à des questions sur les modules

## 🛠️ Technologies utilisées

- **Spring Boot 3.5.6** - Framework Java
- **Spring AI 1.0.3** - Intégration IA
- **Ollama** - Service IA local
- **Llama3** - Modèle de langage
- **Spring Data JPA** - Persistence des données
- **H2 Database** - Base de données en mémoire
- **Lombok** - Réduction du code boilerplate
- **Maven** - Gestion des dépendances

## 📦 Prérequis

- Java 17 ou supérieur
- Maven 3.6+
- Ollama installé et en cours d'exécution
- Modèle Llama3 téléchargé dans Ollama

### Installation d'Ollama

```bash
# macOS
brew install ollama

# Démarrer Ollama
ollama serve

# Télécharger Llama3
ollama pull llama3
```

## 🚀 Installation et démarrage

1. **Cloner le projet**
```bash
git clone https://github.com/houda-aitmouch/spring-ai-ollama.git
cd spring-ai-ollama
```

2. **Compiler le projet**
```bash
./mvnw clean install
```

3. **Démarrer l'application**
```bash
./mvnw spring-boot:run
```

L'application sera accessible sur `http://localhost:8080`

## 📚 API Endpoints

### Gestion des Modules

#### Lister tous les modules
```bash
GET /modules/all
curl http://localhost:8080/modules/all
```

#### Récupérer un module par ID
```bash
GET /modules/{id}
curl http://localhost:8080/modules/1
```

#### Ajouter un nouveau module
```bash
POST /modules
curl -X POST http://localhost:8080/modules \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "nom": "Spring Boot", "description": "Framework Java pour créer des applications web modernes"}'
```

### 🤖 Fonctionnalités IA (Ollama + Llama3)

#### Générer un résumé pédagogique
```bash
GET /modules/{id}/summary
curl http://localhost:8080/modules/1/summary
```

#### Générer un quiz
```bash
GET /modules/{id}/quiz
curl http://localhost:8080/modules/1/quiz
```

#### Générer un quiz avec difficulté
```bash
GET /modules/{id}/quizD?difficulty={niveau}
curl http://localhost:8080/modules/1/quizD?difficulty=Moyen
```
Niveaux disponibles : `Facile`, `Moyen`, `Difficile`

#### Poser une question sur un module
```bash
POST /modules/{id}/ask
curl -X POST http://localhost:8080/modules/1/ask \
  -H "Content-Type: application/json" \
  -d '"Quels sont les principaux avantages de Spring Boot?"'
```

### Autres endpoints

#### Page d'accueil
```bash
GET /
curl http://localhost:8080/
```

#### Santé de l'application
```bash
GET /health
curl http://localhost:8080/health
```

## 🏗️ Structure du projet

```
spring-ai-ollama/
├── src/
│   ├── main/
│   │   ├── java/com/ensias/springaiollama/
│   │   │   ├── AIController.java          # REST Controller pour les modules
│   │   │   ├── HomeController.java        # Page d'accueil et health check
│   │   │   ├── LlamaAiService.java        # Service d'intégration Ollama/Llama3
│   │   │   ├── Module.java                # Entité JPA Module
│   │   │   ├── ModuleRepository.java      # Repository Spring Data JPA
│   │   │   └── SpringAiOllamaApplication.java
│   │   └── resources/
│   │       ├── application.properties     # Configuration Spring Boot
│   │       └── application.yml            # Configuration YAML
│   └── test/
│       └── java/com/ensias/springaiollama/
│           └── SpringAiOllamaApplicationTests.java
├── pom.xml                                # Configuration Maven
└── README.md
```

## ⚙️ Configuration

### application.yml

```yaml
spring:
  application:
    name: spring-ai-ollama
  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        options:
          model: llama3
          temperature: 0.4
```

### Base de données H2

La base de données H2 est configurée en mémoire. Pour accéder à la console H2 :
- URL : `http://localhost:8080/h2-console`
- JDBC URL : `jdbc:h2:mem:testdb`
- Username : `sa`
- Password : *(vide)*

## 🧪 Exemple d'utilisation complet

```bash
# 1. Ajouter un module
curl -X POST http://localhost:8080/modules \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "nom": "Java Spring", "description": "Framework pour développer des applications Java modernes et robustes"}'

# 2. Vérifier que le module est ajouté
curl http://localhost:8080/modules/all

# 3. Générer un résumé avec l'IA
curl http://localhost:8080/modules/1/summary

# 4. Générer un quiz
curl http://localhost:8080/modules/1/quiz

# 5. Poser une question
curl -X POST http://localhost:8080/modules/1/ask \
  -H "Content-Type: application/json" \
  -d '"Quels sont les avantages de Spring Boot?"'
```

## 🐛 Résolution des problèmes

### Ollama ne répond pas
```bash
# Vérifier qu'Ollama est en cours d'exécution
curl http://localhost:11434/api/tags

# Si non, démarrer Ollama
ollama serve
```

### Port 8080 déjà utilisé
```bash
# Trouver le processus utilisant le port 8080
lsof -ti:8080

# Tuer le processus
kill -9 $(lsof -ti:8080)
```

### Modèle Llama3 non trouvé
```bash
# Lister les modèles disponibles
ollama list

# Télécharger Llama3 si absent
ollama pull llama3
```

## 👨‍💻 Auteur

**Houda Ait Mouch**
- GitHub: [@houda-aitmouch](https://github.com/houda-aitmouch)
- Projet: [spring-ai-ollama](https://github.com/houda-aitmouch/spring-ai-ollama)

## 📄 Licence

Ce projet est sous licence Apache 2.0. Voir le fichier LICENSE pour plus de détails.

## 🤝 Contributions

Les contributions sont les bienvenues ! N'hésitez pas à ouvrir une issue ou une pull request.

1. Fork le projet
2. Créer une branche (`git checkout -b feature/AmazingFeature`)
3. Commit vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## 📞 Support

Pour toute question ou problème, veuillez ouvrir une issue sur GitHub.

---

⭐ Si ce projet vous a été utile, n'hésitez pas à lui donner une étoile !

